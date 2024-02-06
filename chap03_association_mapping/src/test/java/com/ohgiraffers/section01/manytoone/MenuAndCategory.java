package com.ohgiraffers.section01.manytoone;

import jakarta.persistence.*;

/*
@joinColumn은 다대일 연관 관계에서 사용되는 어노테이션이다.
@JodinColumn에서 사용할 수 있는 속성들은 다음과 같다.

name : 참조하는 테이블의 컬럼명을 지정한다
referencedColumnName : 참조하는 컬럼명을 지정한다
nullabld : 참조하는 테이블의 컬럼에 null값을 허용할지 여부를 지정한다
unique : 참조하는 테이블의 컬럼에 유일성 제약 조건을 추가할지 여부를 지정한다.
insertable:새로운 엔터티가 저장될때, 이 참조 컬럼이 sql insert문에 포함될지 여부를 지정한다.
updatabld : 엔티티가 업데이트 될때, 이 참조 컬럼이 sql update문에 포함될지 여부를 지정한다.
table : 참조하는 테이블의 이름을 지정한다.
foreigkey : 참조하는 테이블에 생성될 외래 키에 대한 추가 정보를 지정한다.

@ManyTOOne은 다대일 연관관계에서 사용되는 어노테이션이다.
@ManyTOOne에서 사용할 수 있는 속성들은 다음과 같다.

cascade : 연관딘 엔티티의 대한 영속성 전이를 설정한다.
fetch : 연관된 엔터티를 로딩하는 전략을 설정한다.
optional : 연관된 엔터티가 필수인지 선택적인지 설정한다
 */


@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class MenuAndCategory {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;


    /*

    CascadeType.PERSIST
    PERSIST : 엔티티를 영속화할 때, 연관된 하위 엔티티도 함꼐 유지한다.
    MERGE : 엔티티 상태를 병합하라때 연관된 하위 엔티티도 함께 병합한다.
    REMOVE : 엔티티를 제거할때 연관된 하위 엔티티도 함께 제거한다
    DETACH : 영속성 컨텍스트 엔티티 제거, 부모 엔티티를 DETACH()수행하면 하위 엔티티도DETACH상태가 되어 변경사항을 반영하지 않는다.
    REFRESH : 상위 엔티티를 새로고침할떄 연관된 하위 엔티티도 모두 새로고침한다.
     */
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "category_code")  //연관관계 맺으면 join붙여줘야한다
//    private Category category;

    @JoinColumn(name = "category_code")
    @ManyToOne(cascade = CascadeType.PERSIST) //테스트 1
//    @ManyToOne(cascade = CascadeType.REMOVE) // 테스트2
//    @ManyToOne(cascade = CascadeType.REFRESH) // 테스트3
//    @ManyToOne // 테스트 3
//    @ManyToOne(cascade = CascadeType.MERGE) // 테스트4
//    @ManyToOne(cascade = CascadeType.DETACH) // 테스트 5
    private Category category;

    @Column (name = "orderable_status")
    private String orderableStatus;

    public MenuAndCategory() {
    }

    public MenuAndCategory(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
