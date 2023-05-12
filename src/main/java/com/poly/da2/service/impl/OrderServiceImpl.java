package com.poly.da2.service.impl;

import com.poly.da2.repository.AccountRepository;
import com.poly.da2.repository.OrderRepository;
import com.poly.da2.repository.OrderDetailRepository;
import com.poly.da2.entities.Order;
import com.poly.da2.entities.OrderDetail;
import com.poly.da2.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
    AccountRepository accountRepository;
	
	@Override
	public Order create(JsonNode orderData) {
		
		ObjectMapper mapper=new ObjectMapper();
		Order order=mapper.convertValue(orderData, Order.class);
		orderRepository.save(order);
		
		TypeReference<List<OrderDetail>> type=new TypeReference<List<OrderDetail>>(){};
		List<OrderDetail> details=mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d->d.setOrder(order)).collect(Collectors.toList());
		orderDetailRepository.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Integer id) {
		return orderRepository.findById(id).get();
	}

//	@Override
//	public List<Order> findByUsername(String username) {
//		return orderDAO.findByUsername(username);
//	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	
	
}
