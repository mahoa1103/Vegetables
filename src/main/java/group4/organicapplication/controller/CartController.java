package group4.organicapplication.controller;


import group4.organicapplication.model.CartItem;
import group4.organicapplication.model.User;
import group4.organicapplication.service.CartService;
import group4.organicapplication.service.ProductService;
import group4.organicapplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@SessionAttributes({"loggedInUser", "cartItems"})
public class CartController {
    @Autowired
    private CartService cartService;
    @ModelAttribute("cartItems")
    public CartService cartService() {
        return cartService;
    }
    @Autowired
    private UserService userService;

    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem, HttpSession session) {

        List<CartItem> cartItems = cartService.getCartItems();
        for(CartItem cartItem1 : cartItems) {
            if(cartItem1.getProductId().equals(cartItem.getProductId())) {
                if(cartItem1.getQuantity() + cartItem.getQuantity() > cartItem1.getLimitQuantity()) {
                    return ResponseEntity.status(400).body("Hết hàng");
                }
            }
        }
        cartService.addToCart(cartItem);
        //session.setMaxInactiveInterval(5);

        return ResponseEntity.ok().build();

    }

    @PostMapping("cart/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody Map<String, String> requestBody) {
        String productId = requestBody.get("productId");
        cartService.removeFromCart(productId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("cart/update")
    public ResponseEntity<String> updateCartItem(@RequestBody Map<String, String> requestBody) {
        String productId = requestBody.get("productId");
        String quantity = requestBody.get("quantity");
        cartService.updateCartItem(productId, Integer.parseInt(quantity));
        return ResponseEntity.ok().build();
    }

    @GetMapping("cart/get-cart-items")
    public ResponseEntity<List<CartItem>> getCartItems() {

        List<CartItem> cartItems = cartService.getCartItems();
        return ResponseEntity.status(HttpStatus.OK).body(cartItems);
    }

    @ModelAttribute("loggedInUser")
    public User loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    public User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

}
