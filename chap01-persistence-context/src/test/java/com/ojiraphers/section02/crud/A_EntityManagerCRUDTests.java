package com.ojiraphers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");

    }

    @BeforeEach
    public void initManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    /*
     AfterAll 어노테이션이 붙은 CloseFactory() 메소드가 static이 아니라는 것을 지적하고 있습니다.s
    JUnit5에서는 @BeforeAll과 @AfterAll 어노테이션이 달린 메소드는 반드시 static이어야 합니다.
    이는 해당 메소드들이 테스트 클래스의 모든 인스턴스에 대해 단 한 번만 실행되어야 하기 때문입니다.
    따라서, CloseFactory() 메소드를 static으로 선언해주면 문제를 해결할 수 있습니다. 아래와 같이 수정해보세요.
    */

    @AfterAll
    public static void CloseFactory(){
        entityManagerFactory.close();
    }


    @AfterEach
    void closeManager(){
        entityManager.close();
    }

    @Test
    public void 메뉴코드로_메뉴_조회_테스트(){
        //given
        int menuCode = 2;

        //when
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        //then
        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
        System.out.println("foundMEnu : " + foundMenu);
    }

    @Test
    public void 새로운_메뉴_추가_테스트(){
        //given
        Menu menu = new Menu();
        menu.setMenuName("jpa테스트 메뉴");
        menu.setMenuPrice(10000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("y");

        //when
        EntityTransaction entityTransaction = entityManager.getTransaction(); // 데이터베이스의 상태 변경을 위한 명령 = transaction
        entityTransaction.begin();
        try {
            entityManager.persist(menu);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Assertions.assertTrue(entityManager.contains(menu));
    }

    @Test
    public void 메뉴_이름_수정_테스트(){
        //given
        Menu menu = entityManager.find(Menu.class, 2);
        System.out.println("menu = " + menu);

        String menuNameToChange = "김치스무디";
        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            menu.setMenuName(menuNameToChange);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Assertions.assertEquals(menuNameToChange, entityManager.find(Menu.class,2).getMenuName());

    }

    @Test
    public void 메뉴_삭제하기_테스트(){
        //given
        Menu menuToRemove = entityManager.find(Menu.class,1);

        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            entityManager.remove(menuToRemove);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Menu remoedMenu = entityManager.find(Menu.class,1);
        Assertions.assertNull(remoedMenu);

    }

}
