package com.app.expensetracker.controller;

import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * TransactionController handles HTTP requests for transaction-related operations.
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction_list")
    public String getAllTransactions(Model model) // Retrieves and displays the list of all transactions.
    {
        model.addAttribute("transaction_list", transactionService.getAllTransactions());
        return "transaction";
    }

    @GetMapping("/create_form")
    public String createTransactionForm(Model model)  //Displays the transaction creation form.
    {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "transaction_create";
    }

    @PostMapping("/create")
    public  String createTransaction( @Valid TransactionCreateRequest transactionCreateRequest,Model model) throws Exception //Creates a new transaction.
    {
        model.addAttribute("transaction", transactionService.createTransaction(transactionCreateRequest));
        return "success";
    }

    @GetMapping("/update_form/{trans_id}")
    public String editTransactionForm(@PathVariable String trans_id, Model model) //Displays the transaction update form.
    {
        model.addAttribute("transaction", transactionService.getTransactionById(trans_id));
        return "transaction_edit";
    }


    @PostMapping("/update/{trans_id}")
    public String updateTransaction(@PathVariable("trans_id") String trans_id, @ModelAttribute("transaction")Transaction transaction,Model model) throws Exception //Updates an existing transaction.
    {
        Transaction existingTransaction = transactionService.getTransactionById(trans_id);
        existingTransaction.setTrans_id(trans_id);
        existingTransaction.setTransactionName(transaction.getTransactionName());

        existingTransaction.setLenderFirstName(transaction.getLenderFirstName());
        existingTransaction.setLenderLastName(transaction.getLenderLastName());

        existingTransaction.setBorrowerFirstName(transaction.getBorrowerFirstName());
        existingTransaction.setBorrowerLastName(transaction.getBorrowerLastName());

        existingTransaction.setPrice(transaction.getPrice());
        existingTransaction.setCategoryName(transaction.getCategoryName());

        transactionService.updateTransaction(existingTransaction);

        return "update";
    }

    @GetMapping("/get/{trans_id}")
    public String getTransactionById(@PathVariable String trans_id, Model model) //Retrieves and displays a transaction by ID.
    {
        model.addAttribute("transaction",  transactionService.getTransactionById(trans_id));
        return "transaction_details";

    }

    @GetMapping("/delete/{trans_id}")
    public String deleteTransactionById(@PathVariable String trans_id) // Deletes a transaction by ID.
    {
        transactionService.deleteTransactionById(trans_id);
        return "delete";
    }


}