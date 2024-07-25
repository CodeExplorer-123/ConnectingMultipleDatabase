package com.example.spring_boot_mds.order.repository;

import com.example.spring_boot_mds.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDAO extends CrudRepository<Order ,Integer> {
}
