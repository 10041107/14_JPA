package com.ojiraphers.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerLifeCycleTests {

/*
엔터티 매니저 팩토리(entityManagerFactory)란?
엔터티 매니저를 생성할 수 있는 기능을 제공하는 팩토리 클래스이다.
Therad-safe하기 때문에 여러 스레드가 동시에 접근해도 안전하므로 서로 다른 스레드 간 공유해서 재사용한다.
Thread-safe한 기능을 요청 스코프마다 생성하기에는 비용(시간, 메모리)부담이 크므로
Application스코프와 동일하게 싱글톤으로 생성해서 관리하는 것이 효율적이다.
따라서 데이터베이스를 사용하는 어플리케이션당 한개의 EntityManagerFactory를 생성한다.

엔터티 매니저(EntityManager)란?
엔터티 매니저는 앤터티를 저장하는 메모리 상의 데이터베이스를 관리하는 인스턴스이다.
엔터티를 저장하고, 수정, 삭제, 조회하는 등의 엔터티와 관련된 모든 일을 한다.
엔터티 매니저는 Thread-safe하지 않기 때문에 동시성 문제가 발생할 수 있다.

따라서 스레드 간 공유하지 않고, Web의 경우 일반적으로 request scope와 일치시킨다.

영속성 컨텍스트(persistnece context)란?
엔터티 매니저를 통해 엔터티를 저장하거나 조회하면 엔터티 매니저는 영속성 컨텍스트에 엔터티를 보관하고 관리한다.
영속성 컨텍스트는 엔터티를 key-value방식으로 저장하는 저장소이다.
영속성 컨텍스트는 엔터티 매니저를 생성할 때 하나 만들어진다.
그리고 엔터티 매니절르 통해서 영속성 컨텍스트에 접근할 수 있고, 또 관리할 수 있다.
 */

private static EntityManagerFactory entityManagerFactory;
private EntityManager entityManager;
//생성 요청시마다 발생해야 하기 때문에static이 아님

//영속성 컨텍스트 생성
@BeforeAll
    public static void initFactory(){
    entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
}

//영속성 컨텍스트에 접근할 수 있는 유닛 생성
@BeforeEach
    public void initManager(){
    entityManager = entityManagerFactory.createEntityManager();
}

@Test
    public void 엔터티_매니저_팩토리와_엔터티_매니저_생명주기_확인1(){
    System.out.println("entityManagerFactory.hashCode : " + entityManagerFactory.hashCode());
    System.out.println("entityManager.hashCode : " + entityManager.hashCode());
}

@AfterAll
    public static void closeFactory(){
    entityManagerFactory.close();
}

@AfterEach
    public void clostManager(){
    entityManager.close();
}


}
