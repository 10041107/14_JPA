package com.ohgiraffers.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Queue;

public class SimpleJPQLTests {
    /*
    jmpl(java persistence query language)
    jpql은 엔티티 객체를 중심으로 개발할 수 있는 객체 지향 쿼리이다.
    sql보다 간결하며 특정 dbms에 의존하지 않는다.
    방언을 통해 해당 DBMSD에 맞는 sql을 실행하게 된다.
    jpql은 find()*메소드를 통한 조회와 다르게 항상 데이터베이스의
     */

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
    jpql의 기본 문법
    SELECT, UPDATE, DELETE등의 키워드 사용은 SQL과 동일하다.
    INSERT, PERSIST메소드를 사용하면 된다.
    키워드는 대소문자를 구분하지 않지만, 엔티티와 속성은 대소문자를 구분함에 유의한다.
    엔티티 별칭을 필수로 사용해야 하며 별칭 없이 작성하면 에러가 발생한다.

    JPQL 사용 방법은 다음과 같다.
    1. 작성한 JPQL(문장열)을 EntityManager.createQuery()"메소드를 통해 쿼리 객체로 만든다.
    2. 쿼리 객체는 "TypedQuery", "Query"두가지가 있다.
    - TypedQuery :변환할 타입을 명확하게 지정하는 방식일 때 사용하며 쿼리 객체의 메소드 실행 결과로 지정한 타입이 반환된다.
    - query: 변형할 타입을 명확하게 지정할 수 없을때 사용하며 쿼리 객체 메소드의 실행 결과로 object또는 object[]가 반환 된다.
    3. 쿼리 객체에서 제공하는 메소드 getSingleResult()또는 getResultList()를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.
    - getSingleREsult() : 결과가 정확히 한 행일 경우 사용하며 없거나 많으면 예외 발생
    - getResultList() : 결과가 2행 이상일 경우 사용하며 컬렉션을 반환한다. 결과가 없으면 빈 컬렉션을 반환.
     */

    @Test
    public void Query를_이용한_단일_메뉴_조회_테스트 (){
        //when
        String jpql = "SELECT menuName from menu_section01 as a where menuCode = 7";
        Query query = entityManager.createQuery(jpql);

        Object resultMenuName = query.getSingleResult();

        Assertions.assertTrue(resultMenuName instanceof  String);
        Assertions.assertEquals("민트미역국", resultMenuName);
    }

//    @Test
//    public void Type_Query_를_이용한_단일행_조회_테스트 () {
//        //when
//        String jpql = "SELECT m from menu_section01 as m where m.menuCode = 7";
//        Query query = entityManager.createQuery(jpql, Menu.class);
//
//        Menu resultMenuName = query.getSingleResult();
//
//        Assertions.assertEquals(resultMenuName instanceof Query);
//        System.out.println(resultMenuName);
//    }

    @Test
    public void Type_Query_를_이용한_다중행_조회_테스트 () {
        //when
        String jpql = "SELECT m from menu_section01 as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> foundMenu = query.getResultList();

        Assertions.assertNotNull(foundMenu);
        System.out.println(foundMenu);
    }

    @Test
    public void Query_를_이용한_다중행_조회_테스트 () {
        //when
        String jpql = "SELECT m from menu_section01 as m";
        Query query = entityManager.createQuery(jpql);

        List<Object> foundMenuList = query.getResultList();

        Assertions.assertNotNull(foundMenuList);
        foundMenuList.forEach(System.out::println);
    }

    @Test
    void sistinct를_활용한_중복제거_여러_행_조회_테스트(){
        //when
        String jpql = "SELECT DISTINCE m.categoryCode from menu_section01 m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql,Integer.class);

        List<Integer> categoryList = query.getResultList();

        Assertions.assertNotNull(categoryList);
        categoryList.forEach(System.out::println);
    }

    @Test
    void in연산자를_활용한_조회_테스트(){
        //when
        String jpql = "SELECT m from menu_section01 m where m.categoryCode IN (6,10)";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();


        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @Test
    void like_연산자를_활용한_조회_테스트(){
        //when
        String jpql = "SELECT m from menu_section01 m where m.menuName like '%마늘%'";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();


        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }





}
