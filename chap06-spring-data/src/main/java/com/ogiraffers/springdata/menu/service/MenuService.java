package com.ogiraffers.springdata.menu.service;

import com.ogiraffers.springdata.category.entity.Category;
import com.ogiraffers.springdata.category.infra.CategoryFind;
import com.ogiraffers.springdata.category.service.CategoryService;
import com.ogiraffers.springdata.menu.dto.MenuDTO;
import com.ogiraffers.springdata.menu.entity.Menu;
import com.ogiraffers.springdata.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoryFind categoryFind;

    public Object insertMenu(MenuDTO menuDTO) {

        // 메뉴이름이 존재하는가
        Menu menu = menuRepository.findByMenuName(menuDTO.getMenuName());

        if (!Objects.isNull(menu)) {
            return new String(menuDTO.getMenuName() + "의 메뉴가 존재한다");
        }
        //가격 정보 확인
        if ((menuDTO.getMenuPrice() < 0)) {
            return new String(menuDTO.getMenuPrice() + "이걸 팔아서 장사를 어떻게 해");
        }
        //카테고리 필드
        Integer CategoryCode = categoryFind.getCategory(menuDTO.getCategoryCode());

        if (Objects.isNull(CategoryCode)) {

            return new String(menuDTO.getCategoryCode() + "는 존재하지 않습니다");
        }


        Menu newMenu = new Menu();
        newMenu.setMenuName(menuDTO.getMenuName());
        newMenu.setMenuPrice(menuDTO.getMenuPrice());
        newMenu.setCategory(menuDTO.getCategoryCode());

        Menu result = menuRepository.save(newMenu);

        return result.toString();  // 'Menu' 클래스에 'toString()' 메소드를 오버라이드한 경우
        // 또는
        // return result.getMenuName();  // 'Menu' 객체의 메뉴 이름을 반환하려는 경우
    }

    public Integer findMenuCode(Integer menuCode){
        Menu findmenu = menuRepository.findByMenuCode(menuCode);

        if(Objects.isNull(findmenu)){
            return null;
        }

        return findmenu.getMenuCode();
    }


}
