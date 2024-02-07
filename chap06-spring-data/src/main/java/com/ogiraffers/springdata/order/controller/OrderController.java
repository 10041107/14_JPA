package com.ogiraffers.springdata.order.controller;

import com.ogiraffers.springdata.order.dto.OrderAndPaymentsVo;
import com.ogiraffers.springdata.order.entity.MenuOrder;
import com.ogiraffers.springdata.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity order(@RequestBody HashMap<String,Integer> menuCode){
        //유효성 검사 추가
        MenuOrder result = orderService.order(menuCode.get("menuCode"));

        if(Objects.isNull(result)){
            return ResponseEntity.status(400).body("주문실패");
        }
        OrderAndPaymentsVo orderAndPaymentsVo = new OrderAndPaymentsVo();
        orderAndPaymentsVo.setPayments(result.getPayments());
        orderAndPaymentsVo.setMenuCode(result.getMenu());
        orderAndPaymentsVo.setOrderDate(result.getOrderDate());

        return ResponseEntity.ok(orderAndPaymentsVo);


    }
}
