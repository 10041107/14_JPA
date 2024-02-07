package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
/*
양방향 매핑에서 어느 한 쪽이 연관 관계의 주인이 되면, 주인이 아닌 쪽에서는 속성을 지정해주어야 한다.
이때, 연관 관계의 주인이 아닌 객체 MappedBy를 써서 연관 관계 주인인 객체의 필드명을 매핑 시켜 놓으면 양방향 관계를 적용할 수 있다

 */
@Entity(name = "bidirection_category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    // 직접적으로 참조하는 상대편 클래스의 이름을 작성함. 참조 *받는* 쪽에만 mappedBy를 작성.
    // 일반적으로 조회만 함. 영속성을 따라가지 않음 (카테고리를 변경한다 => 카테고리는 수정됨. 자식은 수정되지 않음)
    // 영속성을 따라간다 = 원본을 수정하면 함께 수정됨
    private List<Menu> menuList = new ArrayList<>();

    /*
    @OneToMany(mappedBy = "categoryAndMenu", .....)
    mappedBy: 양방향 매핑을 할때 사용됨.
    one to many등의 한방향 매핑시 사용하면 stackoverflow오류 발생
    (재귀함수 무한 호출하게됨)
     */

    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode, List<Menu> menuList) {
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

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
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

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        List<Menu> list = new ArrayList<>();
        for (Menu m: menuList){
            m.setCategoryAndMenu(this);
            list.add(m);
        }
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
