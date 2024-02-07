package com.ogiraffers.springdata.category.infra;

import com.ogiraffers.springdata.category.entity.Category;
import org.springframework.stereotype.Service;

public interface CategoryFind {
    Integer getCategory(int code);
}