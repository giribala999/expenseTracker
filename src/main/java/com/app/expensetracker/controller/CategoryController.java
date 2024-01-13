package com.app.expensetracker.controller;
import com.app.expensetracker.entity.Category;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/controller")
public class  CategoryController {
    UUID uuid;
    private final List<String> users = new ArrayList<>(); //user ids
    HashMap<String,Category> map = new HashMap<>();
    private final List<Category> categories = new ArrayList<>();
    @GetMapping("/category")
    public List<Category> getCategory() {
        System.out.println("GETMAPPING 1  "+categories);
        return categories;
    }

    @GetMapping("/category/{cat_id}")
    public Category getCategory(@PathVariable String cat_id) {
        System.out.println("GETMAPPING   cat_id:  "+cat_id+"---- category  :"+map.get(cat_id));
        if (map.containsKey(cat_id)) {
            return map.get(cat_id);
        }
        else {
            throw new UserNotFoundException("category id : '" +cat_id+"' not found");
        }

    }

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category category){
//        create category id;
//        create Category obj called cat;
//            cat.id = category_id;
//            cat.name = category.name;
//
//
//                CategoryTable[category_id] = cat


        uuid= UUID.randomUUID();
        String cat_id=uuid.toString();
//        for(int i =0; i<2;i++){
//            users.add(category.getUsers());
//        }
        category= new Category(cat_id,category.getCategoryName(),category.getUsers());
        categories.add(category);

        map.put(cat_id,category);
        return category;

    }

    @PutMapping("/category/{cat_id}")
    public Category updateCategory(@PathVariable String cat_id , @RequestBody Category category) {
        categories.remove(getCategory(cat_id));
        category= new Category(cat_id,category.getCategoryName(),category.getUsers());
        categories.add(category);

        map.put(cat_id,category);

        return category;
    }

    @DeleteMapping("/category/{cat_id}")
    public String deleteCategory(@PathVariable String cat_id ) {
        categories.remove(getCategory(cat_id));
        map.remove(cat_id);
        return "category deleted";
    }

}
