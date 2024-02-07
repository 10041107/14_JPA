package com.ogiraffers.springdata.order.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_order")

public class MenuOrder {

    @Id
    @Column(name = "order_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCode;

    @Column(name = "menu_code", nullable = false)
    private Integer menu;

    //메뉴를 생성하면서 주문번호를 생성하게함
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;


    //결제 성공하고 새로 요청했을때 주문서가 새로 생성되는게 아닌
    // 성공했을때, 실패했을때 이력을 하나의 주문서로 처리하기 위함
    // => "주문하기"를 누르면 주문서가 생성됨.
    // =>잔액부족으로 취소됨 : 주문이 취소되는게 아니고 다시 결제하라고 *보류됨* => 주문서가 아직 유효함
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "menuOrder")
    private List<Payments> payments;

    public MenuOrder() {
    }

    public MenuOrder(int orderCode, Integer menu, Date orderDate, List<Payments> payments) {
        this.orderCode = orderCode;
        this.menu = menu;
        this.orderDate = orderDate;
        this.payments = payments;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Date orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "MenuOrder{" +
                "orderCode=" + orderCode +
                ", menu=" + menu +
                ", orderDate=" + orderDate +
                ", payments=" + payments +
                '}';
    }
}
