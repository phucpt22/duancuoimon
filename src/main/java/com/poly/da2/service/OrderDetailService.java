package com.poly.da2.service;


import com.poly.da2.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
	List<OrderDetail> findAll();
	List<OrderDetail> getOrderDetailByOrdOrderById(Integer orderId);

}
