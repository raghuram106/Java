package com.sts.jpaSortingPagining.repository;


import com.sts.jpaSortingPagining.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
