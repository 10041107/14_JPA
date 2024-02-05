package com.ohgiraffers.section03.primaryKey.subsection01.identity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

public class PrimaryKeyMappingTests {

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

    Primary key에는 @ID어노테이션과 @GeneratedValue어노테이션을 사용한다.
    @Id 어노테이션은 JPA(Java Persistence API)에서 엔티티 클래스의 Primary Key를 나타내는 필드에 사용됩니다.
    @GeneratedValue 어노테이션은 이 Primary Key의 값을 자동으로 생성하기 위해 사용됩니다.

    아래는 @GeneratedValue 어노테이션의 속성에 대한 설명입니다:

    strategy: 자동 생성 전략을 지정합니다.
    GenerationType.IDENTITY: 기본 키 생성을 데이터베이스에 위임합니다 (예: MySQL의 AUTO_INCREMENT).
    GenerationType.SEQUENCE: 데이터베이스 시퀀스 객체를 사용합니다 (예: Oracle의 SEQUENCE).
    GenerationType.TABLE: 키 생성 테이블을 사용합니다.
    GenerationType.AUTO: 데이터베이스에 따라 자동으로 선택됩니다 (예: MySQL이면 IDENTITY, Oracle이면 SEQUENCE로 선택).
    generator: strategy 값이 GenerationType.TABLE로 지정된 경우 사용할 테이블의 이름을 지정합니다.
    initialValue: strategy 값이 GenerationType.SEQUENCE로 지정된 경우 시퀀스의 초기값을 지정합니다.
    allocationSize: strategy 값이 GenerationType.SEQUENCE로 지정된 경우 시퀀스의 증가치를 지정합니다.
     */

    /* IDENTITY 전략 */
    @Test
    public void 식별자_매핑_테스트() {

        // given
        Member member = new Member();
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-5678");
        member.setAddress("서울시 종로구");
        member.setEnrollDate(new Date());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        Member member2 = new Member();
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-1234-5678");
        member2.setAddress("서울시 종로구");
        member2.setEnrollDate(new Date());
        member2.setMemberRole("ROLE_MEMBER");
        member2.setStatus("Y");

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityManager.persist(member2);
        entityTransaction.commit();

        //then
        String jpql = "SELECT A.memberNo FROM menber_section03_subsection01 A";
        List<Integer> memberNoList = entityManager.createQuery(jpql, Integer.class).getResultList();

        memberNoList.forEach(System.out::println);

    }






}
