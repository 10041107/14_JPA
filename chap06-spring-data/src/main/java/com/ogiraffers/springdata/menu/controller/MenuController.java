package com.ogiraffers.springdata.menu.controller;

import com.ogiraffers.springdata.menu.dto.MenuDTO;
import com.ogiraffers.springdata.menu.entity.Menu;
import com.ogiraffers.springdata.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Object> insertMenu(@RequestBody MenuDTO menu){
        Object result = menuService.insertMenu(menu);

        if(!(result instanceof Menu)){
            return ResponseEntity.status(404).body("등록 실패");
        }

        Menu response = (Menu) result;

        return ResponseEntity.ok(response);
    }
}