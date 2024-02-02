package com.ojiraphers.section03.percistencecontext;

import com.sun.security.jgss.GSSUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.lang.annotation.Target;


public class A_EntityLifeCycleTests {

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

    /*
     AfterAll 어노테이션이 붙은 CloseFactory() 메소드가 static이 아니라는 것을 지적하고 있습니다.s
    JUnit5에서는 @BeforeAll과 @AfterAll 어노테이션이 달린 메소드는 반드시 static이어야 합니다.
    이는 해당 메소드들이 테스트 클래스의 모든 인스턴스에 대해 단 한 번만 실행되어야 하기 때문입니다.
    따라서, CloseFactory() 메소드를 static으로 선언해주면 문제를 해결할 수 있습니다. 아래와 같이 수정해보세요.
    */

    @AfterAll
    public static void CloseFactory() {
        entityManagerFactory.close();
    }


    @AfterEach
    void closeManager() {
        entityManager.close();
    }

    /*
    영속성 컨텍스트는 엔터티 매니저가 엔터티 객체를 저장하는 공간으로 엔터티 객체를 보관하고 관리한다.
    엔터티 매니저가 생성 될 때 하나의 영속성 컨텍스트가 만들어진다.

   엔터티의 생명 주기
   비영속, 영속, 준영속, 삭제 상태
     */
    @Test
    public void 비영속_테스트(){
        //given
        Menu foundMenu = entityManager.find(Menu.class,11);

        //객체만 생성하면 영속성 컨텍스트나 데이터베이스와 관련 없는 비영속 상태이다
        Menu newMenu = new Menu();
        newMenu.setMenuName(foundMenu.getMenuName());
        newMenu.setMenuCode(foundMenu.getMenuCode());
        newMenu.setMenuPrice(foundMenu.getMenuPrice());
        newMenu.setCategoryCode(foundMenu.getCategoryCode());
        newMenu.setOrderableStatus(foundMenu.getOrderableStatus());

        Menu copyMenu = foundMenu;

        //when
        boolean isTrue = (foundMenu == newMenu);

        //then
        Assertions.assertFalse(isTrue);


    }

    @Test
    void 연속성_연속_조회_테스트(){
        /*
        엔터티 매니저가 연속성 컨텍스트에 엔터티 객체를 저장(persist)하면 영속성 컨텍스트가 엔터티 객체를 관리하게 되고
        이를 영속 상태라고 한다. Find(),jpgl을 사용한 조회도 영속 상태가 된다.
         */
        //given
        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 11);

        //when
        boolean isTrue = (foundMenu1 == foundMenu2);

        //then
        Assertions.assertTrue(isTrue);

    }

    @Test
    void 영속성_객체_추가_테스트(){
        //menu entity 의 @GeneratedValue(strategy=GenerationType.IDENTITY)설정을 잠시 수석하고 테스트 수행

        //given
        Menu menuToReist = new Menu();
        menuToReist.setMenuCode(400);
        menuToReist.setMenuName("수박죽");
        menuToReist.setMenuPrice(10000);
        menuToReist.setCategoryCode(1);
        menuToReist.setOrderableStatus("Y");

        //when
        entityManager.persist(menuToReist); // 영속화 (=영속성 컨텍스트에 등록시킴 = 영속성 컨테스트에 500번대 객체가 없기 때문에 조회됨)
        Menu foundMenu = entityManager.find(Menu.class,500);
        boolean isTrue = (menuToReist == foundMenu);

        Assertions.assertTrue(isTrue);
    }


    @Test
    void 준영속성_detach_테스트(){
        //given
        Menu foundMenu = entityManager.find(Menu.class, 11);
        Menu foundMenu1 = entityManager.find(Menu.class, 12);

        /*
        영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태가 된다면 준영속 상태가 된다.
        그중 Detach는 특정 엔터티만 준영속 상태로 만든다.
         */
        //when
        entityManager.detach(foundMenu1); // 비영속화 (영속화는 안되어있지만 객체만 남아있는 상태)

        foundMenu.setMenuPrice(5000);
        foundMenu1.setMenuPrice(5000);

        Assertions.assertEquals(5000,entityManager.find(Menu.class,11).getMenuPrice());
        Assertions.assertEquals(5000,entityManager.find(Menu.class,12).getMenuPrice());

    }

    @Test
    void 준영속성_clear_테스트(){
        //given
        Menu foundMenu = entityManager.find(Menu.class, 11);
        Menu foundMenu1 = entityManager.find(Menu.class, 12);

        //when
        entityManager.clear();

        foundMenu.setMenuPrice(5000);
        foundMenu1.setMenuPrice(5000);

        Assertions.assertEquals(5000,entityManager.find(Menu.class,11));
        Assertions.assertEquals(5000,entityManager.find(Menu.class,12));

    }

    @Test
    void 준영속성_close_테스트(){
        //given
        Menu foundMenu = entityManager.find(Menu.class, 11);
        Menu foundMenu1 = entityManager.find(Menu.class, 12);

        //when
        entityManager.close();

        foundMenu.setMenuPrice(5000);
        foundMenu1.setMenuPrice(5000);

        //영속성 컨텍스트를 닫았기 때문에 다시 만들기 전에는 사용할 수 없다.
        Assertions.assertEquals(5000,entityManager.find(Menu.class,11));
        Assertions.assertEquals(5000,entityManager.find(Menu.class,12));

    }

    @Test
    void 준영속성_remove_테스트() {
//    remove : 엔터티를 영속성 컨텍스트 및 데이터베이스에서 삭제한다.
//    단, 트랜잭션을 제어하지 않으면 영구 반영되지는 않는다.
//    트랜젝션을 제어하는 순간 영속성 컨텍스트에서 관리하는 엔터티 객체가 데이터베이스에 반영되게 한다.( 이를 flush라고 함)
//    flush:영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화하는 작업(등록, 수정, 삭제한 엔터티를 데이터베이스에 반영)

            //given
            Menu foundmenu = entityManager.find(Menu.class, 2);

            //when
            entityManager.remove(foundmenu);
            Menu refoundmenu = entityManager.find(Menu.class, 2);

            //then
            Assertions.assertEquals(2, foundmenu.getMenuCode());
            Assertions.assertEquals(null, refoundmenu);
        }

        /*

가상의 공간을 사용한다는 점에서 ReactDOM과 JPA는 공통점이 있을 수 있지만,
그들의 목적과 동작 원리는 여전히 상당히 다릅니다.

가상의 공간 (Virtual Space):

ReactDOM은 가상 DOM을 사용합니다. 가상 DOM은 실제 DOM의 가벼운 복제본으로,
React가 상태 변경 등의 업데이트를 더 효율적으로 처리할 수 있도록 도와줍니다.
JPA는 객체와 데이터베이스 간의 매핑을 위한 가상의 객체 공간을 사용합니다.

자바 객체는 데이터베이스의 테이블과 매핑되어 가상의 객체 공간에서 조작되며,
업데이트가 발생하면 실제 데이터베이스에 반영됩니다.

목적의 차이:
ReactDOM은 사용자 인터페이스를 빠르게 업데이트하고 효과적으로 표시하는 데 중점을 둡니다.
JPA는 데이터베이스와 자바 객체 간의 매핑을 통해 데이터를 효과적으로 조작하고 관리하는 데 중점을 둡니다.

동작 원리의 차이:
ReactDOM은 가상 DOM을 사용하여 변화를 추적하고 필요한 경우에만 실제 DOM에 적용함으로써 성능을 향상시킵니다.
JPA는 객체와 데이터베이스 간의 매핑 설정을 기반으로 SQL 쿼리를 생성하고,
엔터티 매니저를 통해 데이터베이스 조작을 수행합니다.
         */

    /*

JPA(Java Persistence API)에서의 "세션"은 일반적으로
영속성 컨텍스트(Persistence Context)를 나타냅니다.
영속성 컨텍스트는 엔터티 객체를 관리하고 데이터베이스와의 상호 작용을 추상화하는데 사용됩니다.

세션이 업데이트되는 기준은 다음과 같습니다:

트랜잭션의 커밋:

영속성 컨텍스트는 트랜잭션 범위 내에서 관리됩니다.
랜잭션이 커밋되면, 영속성 컨텍스트의 변경 내용이 데이터베이스에 반영됩니다.
이때, 엔터티의 상태가 업데이트됩니다.

flush() 메서드 호출:
명시적으로 flush() 메서드를 호출하거나, JPA 구현체가 자동으로 flush하는 시점에서 변경 내용이 데이터베이스로 전송됩니다.
flush()를 호출하면 영속성 컨텍스트의 변경 내용이 데이터베이스에 동기화되지만,
트랜잭션이 커밋되지는 않습니다.

쿼리 수행 전:
JPA 구현체는 쿼리를 수행하기 전에 자동으로 flush를 수행하여
영속성 컨텍스트의 변경 내용을 데이터베이스에 반영합니다.
이는 데이터베이스와의 일관성을 유지하기 위한 조치입니다.

엔터티의 상태 변화:
엔터티의 상태가 변경되면(예: 필드 값이 수정되거나 엔터티가 새로 생성되면),
영속성 컨텍스트는 해당 변경을 추적하고,
이후 적절한 시점에 데이터베이스에 반영됩니다.

Dirty Checking:
JPA는 Dirty Checking 메커니즘을 사용하여 영속성 컨텍스트에서 엔터티의 변경을 감지합니다.
변경된 엔터티는 필요한 시점에 데이터베이스에 업데이트됩니다.

세션이 업데이트되는 구체적인 시점은 JPA 구현체에 따라 다를 수 있습니다.
주로 트랜잭션의 커밋이나 flush 시점에서 이루어지며,
애플리케이션 코드에서 명시적으로 flush를 호출하는 경우에도 업데이트가 발생할 수 있습니다.

     */

    /*
    병합(merge): 파라미터로 넘어온 준영속 엔터티 객체의 식별 값으로 1차 캐시에서 엔터티 객체를 조회한다.
    만약 1차 캐시에 엔터티가 없으면 데이터베이스에서 엔터티를 조회하고 1차 캐시에 저장한다.
    조회한 영속 엔터티 객체에 준영속 상태의 엔터티 객체의 값을 병합한 뒤 영속 엔터티 객체를 반환한다.
    혹은 조회할 수 없는 데이터의 경우 새로 생성해서 병합한다. (save or update)
         */

    @Test
    void 병합_merge_수정_테스트(){
        //given
        Menu menuToDetach = entityManager.find(Menu.class, 3);
        entityManager.detach(menuToDetach);

        //when
        menuToDetach.setMenuName("수박죽");
        Menu refoundMenu = entityManager.find(Menu.class,3);

        //준영속 엔터티와 상속 엔터티의 해시코드는 다른 상태.
        System.out.println(menuToDetach.hashCode());
        System.out.println(refoundMenu.hashCode());

        entityManager.merge(menuToDetach);

        //then
        Menu mergedMenu = entityManager.find(Menu.class, 3);
        Assertions.assertEquals("수박죽", mergedMenu.getMenuName());
    }

    @Test
    void 병합_merge_삽입_테스트(){
        //given
        Menu menuToDetech = entityManager.find(Menu.class,3);
        entityManager.detach(menuToDetech);

        //when
        menuToDetech.setMenuCode(999);
        menuToDetech.setMenuName("수박죽");
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.merge(menuToDetech);
        transaction.commit();

        //then
        Menu mergedMenu = entityManager.find(Menu.class, 999);
        Assertions.assertEquals("수박죽", mergedMenu.getMenuName());
    }




}
