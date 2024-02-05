package com.ohgiraffers.section01.entity;


import jakarta.persistence.*;

/*
@Entity어노테이션은 jpa에서 사용되는 클래스임을 표시한다.
이 어노테이션을 사용하면 해당 클래스가 데이터베이스의 테이블과 매핑된다.

@Entity어노테이션은 클래스 선언 뒤에 위치해야 한다.
또한, "name"속성을 사용하여 엔티티 클래스와 매핑될 테이블의 이름을 지정할 수 있다.
생략하면 자동으로 클래스 이름을 엔터티명으로 사용한다.

- 프로젝트 내에 다른 패키지에도 동일한 엔터티가 존재하는 경우 해당 엔터티를 식별하기 위한 중복되지 않는 name을 지정해주어야 한다.
- 기본 생성자는 필수로 작성해야 한다.
- final클래스, enum, interface, inner class에서는 사용할 수 없다.
- 저장할 필드에 final을 사용하면 안된다.

 */
@Entity(name = "menber_section01")
@Table(name = "tbl_member_section01")public class Member {

    @Id
    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    @Transient // 테이블 생성 시 무시된다.
    private String nickName;

    @Column(name = "phone", columnDefinition = "varchar(200) default '010-0000-0000'")
    private String phone;

    @Column(name = "email", unique = true)
    private  String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "enroll_date")
    @Temporal(TemporalType.TIMESTAMP) // dateTime
//    @Temporal(TemporalType.TIME) //time
//    @Temporal(TemporalType.DATE) // date
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
}
