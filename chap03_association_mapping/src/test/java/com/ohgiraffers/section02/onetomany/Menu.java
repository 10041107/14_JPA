package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.*;

@Entity(name = "menu")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "orderable_status")
    private String orderableStatus;

    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "category_code")
    private CategoryAndMenu categoryAndMenu;


    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, String orderableStatus, CategoryAndMenu categoryAndMenu) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.orderableStatus = orderableStatus;
        this.categoryAndMenu = categoryAndMenu;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    public CategoryAndMenu getCategoryAndMenu() {
        return categoryAndMenu;
    }

    public void setCategoryAndMenu(CategoryAndMenu categoryAndMenu) {
        this.categoryAndMenu = categoryAndMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", orderableStatus='" + orderableStatus + '\'' +
                ", categoryAndMenu=" + categoryAndMenu +
                '}';
    }
}
