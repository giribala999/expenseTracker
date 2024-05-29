package com.app.expensetracker.controller;
import com.app.expensetracker.dto.CategoryCreateRequest;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * CategoryController handles HTTP requests for category-related operations.
 */

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category_list")
    public String getAllCategories(Model model) //Retrieves and displays the list of all categories.
    {
        model.addAttribute("category_list",categoryService.getAllCategories());
        return "category";
    }

    @GetMapping("/create_form")
    public String createCategoryForm(Model model) //Displays the category creation form.
    {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category_create";
    }

    @PostMapping("/create")
    public  String createCategory(@Valid CategoryCreateRequest categoryCreateRequest, Model model)throws Exception //Creates a new category.
    {
        model.addAttribute("category", categoryService.createCategory(categoryCreateRequest));
        return "success";
    }

    @GetMapping("/update_form/{catId}")
    public String editCategoryForm(@PathVariable String catId, Model model) //Displays the category update form.
    {
        model.addAttribute("category", categoryService.getCategoryById(catId));
        return "category_edit";
    }

    @PostMapping("/update/{catId}")
    public String updateCategory(@PathVariable("catId") String catId, @ModelAttribute("category")Category category,Model model) throws Exception //Updates an existing category.
    {
        Category existingCategory = categoryService.getCategoryById(catId); // get category from database by id
        existingCategory.setCat_id(catId);
        existingCategory.setCategoryName(category.getCategoryName());

        categoryService.updateCategory(existingCategory); // save updated category object

        return "update";
    }

    @GetMapping("/get/{catId}")
    public String getCategoryById(@PathVariable String catId, Model model) //Retrieves and displays a category by ID.
    {
        model.addAttribute("category", categoryService.getCategoryById(catId));
        return "category_details";
    }

    @GetMapping("/delete/{catId}")
    public String deleteCategoryById(@PathVariable String catId) //Deletes a category by ID.
    {
        categoryService.deleteCategoryById(catId);
        return "delete";
    }

}