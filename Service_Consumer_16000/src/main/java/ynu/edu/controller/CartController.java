package ynu.edu.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ynu.edu.entity.Cart;
import ynu.edu.entity.User;
import ynu.edu.feign.ServiceProviderService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private ServiceProviderService serviceProviderService;

    private static final String USER_SERVICE_URL = "http://localhost:11000/user";

    // 1. GET - 获取用户购物车信息
    @GetMapping("/getCartById/{userId}")
    public Cart getCart(@PathVariable("userId") Integer userId) {
        // 调用用户服务获取用户信息
        User user = serviceProviderService.getUserById(userId);
        // 创建购物车
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setGoodList(Arrays.asList("电池", "无人机", "笔记本电脑"));
        return cart;
    }

    // 2. POST - 创建用户并返回默认购物车
    @PostMapping("/createUser/{userId}")
    public Cart createUser(@PathVariable("userId") Integer userId) {
        // 调用用户服务创建用户
        User createdUser = serviceProviderService.createUser(userId);
        // 创建默认购物车
        Cart cart = new Cart();
        cart.setUser(createdUser);
        cart.setGoodList(Arrays.asList("欢迎礼品"));
        return cart;
    }

    // 3. PUT - 更新用户信息并返回更新后的购物车
    @PutMapping("/updateUser/{userId}")
    public Cart updateUser(@PathVariable("userId") Integer userId,
                           @RequestBody User updatedUser) {
        // 调用用户服务更新用户
        serviceProviderService.updateUser(userId, updatedUser);
        // 获取更新后的用户信息
        User user = serviceProviderService.getUserById(userId);
        // 返回购物车
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setGoodList(Arrays.asList("已更新的商品"));
        return cart;
    }

    // 4. DELETE - 删除用户并返回确认消息
    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        // 调用用户服务删除用户
        serviceProviderService.deleteUser(userId);
        return "用户ID " + userId + " 已被删除，相关购物车已清空";
    }
}
