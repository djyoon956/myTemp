package com.model;

import java.util.List;

// 주문서 클래스
// 하나의 주문은 여러개의 상품을 가질 수 있다.
public class OrderCommand {
	private List<OrderItem> orderItem;

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
}
/*
 * 주문 발생 OrderCommand command = new OrderCommand();
 * 
 * List<OrderItem> list = new ArrayList<>(); list.add(new
 * OrderItem(1,10,"파손 주의"); list.add(new OrderItem(2,1,"리모콘 별도"); list.add(new
 * OrderItem(3,5,"사이즈 교환 불가");
 * 
 * commad.setOrderItem(list);
 */
