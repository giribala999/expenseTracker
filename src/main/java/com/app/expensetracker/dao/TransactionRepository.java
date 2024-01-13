package com.app.expensetracker.dao;

import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
