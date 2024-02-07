package com.ogiraffers.springdata.order.repository;

import com.ogiraffers.springdata.order.entity.MenuOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MenuOrder, Integer> {

}
