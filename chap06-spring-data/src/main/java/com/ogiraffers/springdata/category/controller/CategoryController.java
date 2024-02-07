package com.ogiraffers.springdata.category.controller;

import com.ogiraffers.springdata.category.dto.CategoryDTO;
import com.ogiraffers.springdata.category.entity.Category;
import com.ogiraffers.springdata.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity insertCategory(@RequestBody HashMap<String,String> categoryName) {

        //카테고리 dto유효성 검사
        if (Objects.isNull(categoryName)) {
            return ResponseEntity.status(404).body("이름은 필수");
        }

        Category result = categoryService.insertCategory(categoryName.get("categoryName"));

        if(Objects.isNull(result)){
            return ResponseEntity.status(500).body("서버에서 오류가 발생했습니다");
        }

        return ResponseEntity.ok(result);
    }
}
