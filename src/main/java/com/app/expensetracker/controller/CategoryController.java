package com.app.expensetracker.controller;
import com.app.expensetracker.dto.CategoryCreateRequest;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category_list")
    public String getAllCategories(Model model) {

        model.addAttribute("category_list",categoryService.getAllCategories());
        return "category";
    }

    @GetMapping("/create_form")
    public String createCategoryForm(Model model) {

        Category category = new Category();
        model.addAttribute("category", category);
        return "category_create";

    }
    @PostMapping("/create")
    public  String createCategory(@Valid CategoryCreateRequest categoryCreateRequest,Model model)throws Exception{

        model.addAttribute("category", categoryService.createCategory(categoryCreateRequest));
        model.addAttribute("message", "You have registered successfully.");
        return "redirect:/category/category_list";
    }

    @GetMapping("/update_form/{catId}")
    public String editCategoryForm(@PathVariable String catId, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(catId));
        return "category_edit";
    }
    @PostMapping("/update/{catId}")
    public String updateCategory(@PathVariable("catId") String catId, @ModelAttribute("category")Category category,Model model) throws Exception {
        Category existingCategory = categoryService.getCategoryById(catId);
        existingCategory.setId(catId);
        existingCategory.setCategoryName(category.getCategoryName());

        categoryService.updateCategory(existingCategory);
        model.addAttribute("message", "You have registered successfully.");
        return "redirect:/category/category_list";
    }
    @GetMapping("/get/{catId}")
    public String getCategoryById(@PathVariable String catId, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(catId));
        return "category_details";
    }

    @GetMapping("/delete/{catId}")
    public String deleteCategoryById(@PathVariable String catId) {
        categoryService.deleteCategoryById(catId);
        return "redirect:/category/category_list";
    }

}