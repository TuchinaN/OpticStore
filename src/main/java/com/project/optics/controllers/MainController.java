package com.project.optics.controllers;

import com.project.optics.models.*;
import com.project.optics.repositories.ProductRepository;
import com.project.optics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private FavoritesService favService;

    @GetMapping("/")
    public String mainPage(
            @AuthenticationPrincipal User user,
            Authentication authentication,
            @RequestParam(name = "typeId", required = false) Integer typeId,
            Model model) {

        String auth = user.getAuthorities().toString();

        model.addAttribute("authority", auth);
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("typeId", typeId);
        model.addAttribute("title", "Каталог");
        Iterable<Product> product = productRepository.findAll();
        model.addAttribute("product", product);

        if (typeId == null) {
            model.addAttribute("product", product);
        }
        else{
            model.addAttribute("product", productService.getAllItemsByTypeId(typeId));
        }
        return "index";
    }

    @Controller
    public class ItemsController {
        @GetMapping("/add")
        public String itemAddForm(Model model){
            return "itemAdd";
        }

        @PostMapping("/add")
        public String itemSave(@RequestParam String item_name,
                               @RequestParam String cover_link,
                               @RequestParam int price,
                               @RequestParam int typeId, Model model){
            Product item = new Product(
                    item_name,
                    cover_link,
                    price,
                    typeId);

            productRepository.save(item);
            return "redirect:/";
        }
    }

    private int getUserId(Authentication authentication) {
        if (authentication == null)
            return 0;
        else
            return ((User)userService.loadUserByUsername(authentication.getName())).getId();
    }

    @PostMapping("/page/{id}")
    public String addItemToCart(
            @RequestParam (required = false) String itemParameters,
            Authentication authentication,
            @PathVariable(value = "id") int itemId,
            Model model
    ) {
            int userId = getUserId(authentication);
            Cart cart = cartService.getPurchaseByUserIdAndItemId(userId, itemId);
            if (cart == null){
                Cart newCart = new Cart();
                newCart.setUserId(userId);
                newCart.setItemId(itemId);
                newCart.setItemCount(1);
                newCart.setItemParameters(itemParameters);
                cartService.savePurchase(newCart);
                return "redirect:/";
            }
            else{
                cart.setItemCount(cart.getItemCount() + 1);
                cartService.savePurchase(cart);
                return "redirect:/";
            }
    }

    @PostMapping("/favorites/{id}")
    public String addItemToFav(
            Authentication authentication,
            @PathVariable(value = "id") int itemId,
            Model model
    ) {
        int userId = getUserId(authentication);
        Favorites favs = favService.getAddByUserIdAndItemId(userId, itemId);
        if (favs == null){
            Favorites newFavorites = new Favorites();
            newFavorites.setUserId(userId);
            newFavorites.setItemId(itemId);
            favService.saveAdd(newFavorites);
            return "redirect:/";
        }
        else{
            return "redirect:/";
        }
    }
}
