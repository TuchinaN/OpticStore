package com.project.optics.service;


import com.project.optics.models.Product;
import com.project.optics.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> getAllItemsById(){
        return productRepository.findAll();
    }
    public List<Product> getAllItemsByTypeId(int typeId) {
        return productRepository.findAllByTypeId(typeId);
    }
    public Product getItemById(int id){
        return productRepository.findById(id);
    }
    public void saveItem(Product items){
        productRepository.save(items);
    }
    public void deleteItemById(int id){
        productRepository.deleteById(id);
    }
}