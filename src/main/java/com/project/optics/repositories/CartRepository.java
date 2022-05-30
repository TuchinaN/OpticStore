package com.project.optics.repositories;

import com.project.optics.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserIdAndItemId(int userId, int itemId);
    List<Cart> findAllByUserId(int userId);
    Cart findById(int id);
    Long deleteById(int id);

    @Transactional
    Long deleteAllByUserId(int userId);
}
