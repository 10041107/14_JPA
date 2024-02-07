package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class OneToManyAssocationTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

//    @Test
//    void 일대다_연관관계_객체_그래프_탐색을_이용한_조회_테스트(){
//        int categoryCode = 10;
//
//        Category categoryAndMenu = entityManager.find(Category.class, categoryCode);
//
//        //then
//        Assertions.assertNotNull(categoryAndMenu);
//
//        System.out.println(categoryAndMenu);
//    }
//
//    @Test
//    void 일대다_연관관계_객체_삽입_테스트(){
//        Category categoryAndMenu = new Category();
//        categoryAndMenu.setCategoryCode(902);
//        categoryAndMenu.setCategoryName("일대다 카테고리");
//        categoryAndMenu.setRefCategoryCode(null);
//
//        List<Menu> menuList = new ArrayList<>();
//        Menu menu = new Menu();
//        menu.setMenuCode(777);
//        menu.setMenuName("일대다 아이스크림");
//        menu.setMenuPrice(8700);
//        menu.setOrderableStatus("y");
//        menu.setCategoryAndMenu(categoryAndMenu);
//        /*
//
//         */
//
//        menuList.add(menu);
//
//        categoryAndMenu.setMenuList(menuList);
//
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(categoryAndMenu);
//        transaction.commit();
//        Category foundCategoryMenu = entityManager.find(Category.class, 902);
//        System.out.println(foundCategoryMenu);
//
//    }
//



}
