package com.ogiraffers.springdata.menu.repository;

import com.ogiraffers.springdata.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByMenuName(String name);

    Menu findByMenuCode(Integer menuCode);


}
