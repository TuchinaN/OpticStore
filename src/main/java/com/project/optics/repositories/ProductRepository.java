package com.project.optics.repositories;

import com.project.optics.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByTypeId(int typeId);
    List <Product> findAll();
    Product findById(int id);
    Long deleteById(int id);
}
