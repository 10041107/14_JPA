package com.ogiraffers.springdata.category.repository;

import com.ogiraffers.springdata.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByCategoryCode(int code);

    Category findByCategoryName(String name);


}
