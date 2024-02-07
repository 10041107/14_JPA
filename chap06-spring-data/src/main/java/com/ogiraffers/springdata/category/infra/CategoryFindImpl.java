package com.ogiraffers.springdata.category.infra;

import com.ogiraffers.springdata.category.entity.Category;
import com.ogiraffers.springdata.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryFindImpl implements CategoryFind {

    @Autowired
    CategoryService categoryService;

    @Override
    public Integer getCategory(int code){
        return categoryService.findByCategory(code);
    }
}