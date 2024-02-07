package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class BIDIrectionTests {

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

    /*
    데이터베이스의 테이블은 외래키 하나로 양방향 조회가 가능하지만 객체는 서로 다른 두 단반향 참조를 합쳐서 양방향이라고 한다.
    따라서 두개의 연관 관계중 연관 관계의 주인을 정하고, 주인이 아닌 연관 관계를 하나 더 추가하는 방식으로 작성하게 된다.
    양방향 연관 관계는 항상 설정하는 것이 아니라 반대 방향으로도 접근하여 객체 그래프 탐색을 할 일이 많은 경우에만 사용된다.

    양방향 연관 관계시 연관 관꼐의 주인(owner)이라는 이름으로 인해 오해가 생길 수 있다.
    비즈니스 로직 상 더 중요하다고 연관 관계의 주인이 되는 것이다.
    비즈니스 중요도를 배제하고 단순히 외래키 관리자의 의미를 부여해야 한다.
    연관 관계의 주인은 외래키를 가지고 있는 엔티티이다.

    메뉴 엔티티는 카테고리 엔티티의 카테고리 코드를 참조하고 있다.
    즉 외래키를 가지고 있는 메뉴 엔티티가 연관관계의 주인이다.

    =========================
    데이터베이스에서는
    하나의 테이블이 다른 테이블을 참조할 때 외래키 하나로 양방향 조회가 가능합니다. (fk)
    하지만 객체지향 프로그래밍에서는
    서로 다른 두 객체가 서로를 참조할 때를 양방향 연관 관계라고 합니다.
    이때, 두 객체 중 한 객체가 다른 객체를 참조하는 관계를 주인으로 정하고,
    주인이 아닌 객체는 추가적인 참조를 설정하는 방식으로
    양방향 연관 관계를 구성합니다.

    비즈니스 로직 상에서 중요한 객체가 주인이 되는 것이 아니라,
    외래키를 가지고 있는 엔티티가 주인이 됩니다.
    예를 들어, 메뉴 엔티티가 카테고리 엔티티의 카테고리 코드를 참조한다면,
    외래키를 가지고 있는 메뉴 엔티티가 연관 관계의 주인이 됩니다.
     */
    @Test
    void  양방향_연관관계_매핑_조회_테스트(){
        int menuCode = 10;
        int categoryCode = 10;

        //진짜 연관 관계는 처음 조회부터 조인한 결과를 인출해온다.
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        //가짜 연관관계는 해당 엔티티를 조회하고 필요시 연관관계 엔티티를  조회하는 쿼리를 다시 실행하게 된다.
        Category foundCategory = entityManager.find(Category.class, categoryCode);

        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
        Assertions.assertEquals(categoryCode, foundCategory.getCategoryCode());

        System.out.println(foundCategory);
        System.out.println(foundMenu);

        //category에 포함된 메뉴 목록 출력 구문을 작성하고 나면 실제 사용에 필요해지기 떄문에 가짜 연관관계에 해당하는
        // 엔티티도 다시 조회하는 쿼리가 한번더 동작한다.
        foundCategory.getMenuList().forEach(System.out::println);
    }
    
    /*
    연관관계ㅔ의 주인에는 값을 입력하고, 주인이 아닌 곳에는 값을 입력하지 않았을 때
    외래키 컬럼이 not null제약조건이 설정되어 있다면 에러가 발생한다.
    양방향 연관관계를 설정하고 흔히하는 실수이므로 반드시 menu엔터티에 카테고리 정보를 추가한다.
    
     */
    @Test
    void 양방향_연관관계_주인_객체를_이용한_삽입_테스트(){
        //given
        Menu menu = new Menu();
        menu.setMenuName("연관관계 주인");
        menu.setOrderableStatus("y");
        menu.setMenuPrice(10000);
        
        menu.setCategoryAndMenu(entityManager.find(Category.class, 4));
        
        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(menu);
        transaction.commit();
        
        Menu foundMenu = entityManager.find(Menu.class, menu.getMenuCode());
        Assertions.assertEquals(menu.getMenuCode(), foundMenu.getMenuCode());

        System.out.println(foundMenu);
    }

    @Test
    public void 양방향_연관관계_주인이_아닌_객체를_이용한_삽입_테스트() {

        //given
        Category category = new Category();
        category.setCategoryName("양방향카테고리");
        category.setRefCategoryCode(null);

        Menu insertMenu = new Menu();
        insertMenu.setMenuName("연관관계 주인");
        insertMenu.setMenuPrice(10000);
        insertMenu.setOrderableStatus("y");
        insertMenu.setCategoryAndMenu(category);

        List<Menu> list = new ArrayList<>();
        list.add(insertMenu);

        category.setMenuList(list);


        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(category);
        entityTransaction.commit();

        //then
        Category foundCategory = entityManager.find(Category.class, category.getCategoryCode());
        Assertions.assertEquals(category.getCategoryCode(), foundCategory.getCategoryCode());
        System.out.println(foundCategory);

    }




}
