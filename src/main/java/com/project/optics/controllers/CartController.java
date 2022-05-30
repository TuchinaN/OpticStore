package com.project.optics.controllers;

import com.project.optics.models.Cart;
import com.project.optics.models.User;
import com.project.optics.service.CartService;
import com.project.optics.service.ProductService;
import com.project.optics.service.TypeService;
import com.project.optics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    private int getCartPrice(List<Cart> purchases) {
        int total = 0;
        for (Cart cart: purchases){
            total += productService.getItemById(cart.getItemId()).getPrice() * cart.getItemCount();
        }
        return total;
    }

    @GetMapping("/cart")
    public String cart(
            Model model,
            @AuthenticationPrincipal User user
    ){
        int userId = user.getId();
        model.addAttribute("cartPrice", getCartPrice(cartService.getPurchasesByUserId(userId)));
        model.addAttribute("types", typeService.getAllTypes());
        List<Cart> purchases = cartService.getPurchasesByUserId(userId);
        model.addAttribute("cart", purchases);
        model.addAttribute("productService", productService);
        return "cart";
    }

    @PostMapping("/cartDeletePurchase")
    public String deletePurchase(@RequestParam(value = "delButton") int purchaseId){
        cartService.deletePurchaseById(purchaseId);
        return "redirect:/cart";
    }

    @PostMapping("/cartIncrPurchase")
    public String increasePurchase(@RequestParam(value = "incrButton") int purchaseId){
        Cart purchase = cartService.getPurchaseById(purchaseId);
        purchase.setItemCount(purchase.getItemCount() + 1);
        cartService.savePurchase(purchase);
        return "redirect:/cart";
    }

    @PostMapping("/cartDecrPurchase")
    public String decreasePurchase(@RequestParam(value = "decrButton") int purchaseId){
        Cart purchase = cartService.getPurchaseById(purchaseId);
        purchase.setItemCount(purchase.getItemCount() - 1);
        cartService.savePurchase(purchase);
        if (purchase.getItemCount() <= 0){
            cartService.deletePurchase(purchase);
        }
        return "redirect:/cart";
    }
}
