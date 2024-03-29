package com.ohgiraffers.section06.compositekey.subsection02.idclass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class IdClassTests {
    private static EntityManagerFactory entityManagerFactory;  // 싱글톤 리소스를 줄이기 위해
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
 *  복합키가 존재하는 테이블 매핑의 경우 별도의 방법이 필요하다.
 *  1. @Embeddable : @Embeddable 클래스에 복합 키를 정의하고 엔티티에 @EmbeddedId를 이용해 복합 키 클래스를 매핑한다.
 *  2. @IdClass : 복합 키를 필드로 정의한 클래스를 이용해 엔티티 클래스에 복합키에 해당하는 필드에 @Id를 매핑한다.
 *
 *  두 방식 모두 복합키 클래스는 영속성 컨텍스트가 관리하지 않는다는 특징이 있으며, 큰 기능적 차이도 존재하지 않는다.
 * */

    @Test
    public void 아이디_클래스_사용한_복합키_테이블_매핑_테스트() {
        //given

        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setPhone("010-1234-5678");
        member.setAddress("서울시 종로구");

        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();

        //then
        Member foundMember = entityManager.find(Member.class, new MemberPk(1,"user01"));
        Assertions.assertEquals(member, foundMember);
    }


}
