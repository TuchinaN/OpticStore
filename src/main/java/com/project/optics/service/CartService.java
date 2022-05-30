package com.project.optics.service;

import com.project.optics.models.Cart;;
import com.project.optics.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private CartRepository cartRepo;

    @Autowired
    public CartService(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public List<Cart> getPurchasesByUserId(int userId) {
        return cartRepo.findAllByUserId(userId);
    }
    public Cart getPurchaseByUserIdAndItemId(int userId, int itemId){
        return cartRepo.findByUserIdAndItemId(userId, itemId);
    }
    public Cart getPurchaseById (int id) {
        return cartRepo.findById(id);
    }
    public void savePurchase(Cart purchase) {
        cartRepo.save(purchase);
    }
    public void deletePurchaseById(int id) {
        cartRepo.deleteById(id);
    }
    public void deletePurchase(Cart purchase){
        cartRepo.delete(purchase);
    }
    public void deleteAllByUserId(int userId)
    {
        cartRepo.deleteAllByUserId(userId);
    }
}
