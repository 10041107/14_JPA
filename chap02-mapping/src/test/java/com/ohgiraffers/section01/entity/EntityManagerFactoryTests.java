package com.ohgiraffers.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;


public class EntityManagerFactoryTests {

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

    @Test
    public void 테이블_만들기_테스트(){
        Member member = new Member();

        member.setMemberNo(1);
        member.setMemberId("setID");
        member.setMemberPwd("1234");
        member.setAddress("관악구 신림동");
        member.setEmail("1234@gmail.com");
        member.setEnrollDate(String.valueOf(new Date()));
        member.setNickName("gorilla");
        member.setStatus("y");

        entityManager.persist(member);
        Member foundMember = entityManager.find(Member.class, member.getMemberNo());
        Assertions.assertEquals(member.getMemberNo(), foundMember.getMemberNo());

        /*
        commit하지 않았기 때문에 dml은 rollback되어 있지만, ddl은 AutoCommit구문이기 때문에 테이블은 생성되어 있다.
        생성되는 컬럼의 순서는 pk가 우선이며, 일반 컬럼은 유니코드 오름차순으로 생성된다.
         */

    }



}
