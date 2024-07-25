package com.example.spring_boot_mds;

import com.example.spring_boot_mds.order.entity.Order;
import com.example.spring_boot_mds.order.repository.OrderDAO;
import com.example.spring_boot_mds.product.entity.Product;
import com.example.spring_boot_mds.product.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SpringBootMdsApplication implements CommandLineRunner {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderDAO orderDAO;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMdsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product(2,"bike",400000);
		productDao.save(product);

		orderDAO.save(new Order(1, LocalDate.now()));
	}
}
