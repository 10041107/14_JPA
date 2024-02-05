package com.ohgiraffers.section03.primaryKey.subsection01.identity;


import jakarta.persistence.*;

import java.util.Date;

/*
컬럼 매핑 시 @Column어노테이션에 사용 가능한 속성들
1. name: 매핑할 테이블의 컬럼 이름
2. inserttable:엔터티 저장시 필드 저장 여부 (default: true)
3. updateTable: 엔터티 수정 시 필드 수정 여부(default:true)
4. table : 엔터티와 매핑될 테이블 이름, 하나의 엔터티를 두개 이상의 테이블에 매핑할때 사용 (@SecondaryTable사용)
5. nullable : null값 허용 여부 설정, not null제약조건에 해당함(true기본값)
6. unique: 컬럼에 유일성 제약 조건
(두 개 이상 컬럼에 unique제약조건을 설정하기 위해서 클래스 레벨에서 @Tabld의 uniqueConstraints속성에 실행)
7. columnDefinition: 직접 컬럼의 ddl을 지정
8. length: 문자열 길이, String type에서만 사용(default:255)

 */
@Entity(name = "menber_section03_subsection01")
@Table(name = "tbl_menber_section03_subsection01")
public class Member {

    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private  String email;

    @Column(name = "address")
    private String address;

    @Column(name = "enroll_date")
    private String enrollDate;

    @Column(name = "memberRole")
    private String memberRole;

    @Column(name = "status")
    private String status;


    public Member() {
    }

    public Member(int memberNo, String memberId, String memberPwd, String nickName, String phone, String email, String address, String enrollDate, String memberRole, String status) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getMemverRole() {
        return memberRole;
    }

    public void setMemverRole(String memverRole) {
        this.memberRole = memverRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", enrollDate='" + enrollDate + '\'' +
                ", memverRole='" + memberRole + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setEnrollDate(Date date) {
    }

    public void setNickname(String 홍길동) {
    }

    public void setMemberRole(String roleMember) {
    }
}
