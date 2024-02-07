package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "category_section02")
@Table(name = "tbl_category")
public class CategoryAndMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private String refCategoryCode;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_code")
    private List<Menu> menuList;


    /*
    @OneToMany(mappedBy = "categoryAndMenu", .....)
    mappedBy: 양방향 매핑을 할때 사용됨.
    one to many등의 한방향 매핑시 사용하면 stackoverflow오류 발생
    (재귀함수 무한 호출하게됨)
     */

    public CategoryAndMenu() {
    }

    public CategoryAndMenu(int categoryCode, String categoryName, String refCategoryCode, List<Menu> menuList) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
        this.menuList = menuList;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(String refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

//    public List<Menu> getMenuList() {
//        return menuList;
//    }
//
//    public void setMenuList(List<Menu> menuList) {
//        this.menuList = menuList;
//    }

//    public List<Menu> getmenuList(){return menuList;}
//
//    public void setMenuList(List<Menu> menuList){
//        List<Menu> newList = new ArrayList<>();
//        for (Menu m: menuList){
//            m.setCategory(this.categoryCode);
//            newList.add(m);
//        }
//        this.menuList = newList;
//    }


    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menuList=" + menuList +
                '}';
    }
}
