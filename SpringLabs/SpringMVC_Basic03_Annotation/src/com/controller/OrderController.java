package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.OrderCommand;
import com.model.OrderItem;

/*
 	요청 주소 하나 : (method = GET, POST)
 	화면 : order.do (GET)   >> 화면
 	처리 : order.do (POST) >> 처리
 */

@Controller
@RequestMapping("/order/order.do")
public class OrderController {

	@RequestMapping(method = RequestMethod.GET)
	public String form() { // order.do (GET)
		return "order/OrderForm";
	}

	// submit(OrderCommand orderCommand)
	// OrderCommand orderCommand = new OrderCommand();
	// setter 주입
	// List<OrderItem> list = new ...
	// list.add(new OrderItem()) ... orderItem[0].itemid , orderItem[0].number ,
	// orderItem[0].remark
	// orderCommand.setOrderItem()
	@RequestMapping(method = RequestMethod.POST)
	public String submit(OrderCommand orderCommand) {
		// System.out.println("orderCommand 주소 : " + orderCommand);
		// System.out.println("orderCommand Item 크기 : " +
		// orderCommand.getOrderItem().size());
		List<OrderItem> items = orderCommand.getOrderItem();

		for (OrderItem item : items) {
			System.out.println(item.toString());
		}
		return "order/OrderCommited";
	}
}
