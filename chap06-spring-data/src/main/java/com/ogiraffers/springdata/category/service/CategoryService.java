package com.ogiraffers.springdata.category.service;

import com.ogiraffers.springdata.category.entity.Category;
import com.ogiraffers.springdata.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Integer findByCategory(int categoryCode){
//        if((categoryCode <0)){
//            return null;
//        }
//        CategoryDTO category = categoryRepository.findByCategoryCode(categoryCode);
// 어차피 없는 값을 조회하면 null로 나오기 때문에 필요 없음

        Category category = categoryRepository.findByCategoryCode(categoryCode);

        if(Objects.isNull(category)){
            return null;
        }

        return category.getCategoryCode();


    }


    public Category insertCategory(String categoryName) {
        //이름 중복 여부
        Category foundCategory = categoryRepository.findByCategoryName(categoryName);

        if(!Objects.isNull(foundCategory)){
            return null;
        }

        Category insertCategory = new Category();
        insertCategory.setCategoryName(categoryName);

        Category result = categoryRepository.save(insertCategory);

        return  result;

    }
}
