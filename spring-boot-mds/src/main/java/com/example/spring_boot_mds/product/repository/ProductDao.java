package com.example.spring_boot_mds.product.repository;

import com.example.spring_boot_mds.product.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product,Integer> {
}
