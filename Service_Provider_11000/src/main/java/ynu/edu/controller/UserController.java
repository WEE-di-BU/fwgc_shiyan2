package ynu.edu.controller;

import org.springframework.web.bind.annotation.*;
import ynu.edu.entity.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    // 使用Map模拟简单存储
    private final Map<Integer, User> userMap = new HashMap<>();
    private int nextId = 100;

    // 初始化一个测试用户
    public UserController() {
        userMap.put(100, new User(100, "测试用户 from 11000", "test123"));
    }

    // 1. GET - 获取用户
    @GetMapping("/getUserById/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) {
        // 返回用户或创建默认用户
        return userMap.getOrDefault(userId,
                new User(userId, "默认用户" + userId, "pass" + userId));
    }

    // 2. POST - 创建用户
    @PostMapping("/createUser/{userId}")
    public User createUser(@PathVariable("userId") Integer userId) {
        // 创建新用户
        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setUserName("新用户 from 11000");
        newUser.setPassword("pass" + userId);

        userMap.put(newUser.getUserId(), newUser);
        return newUser;
    }

    // 3. PUT - 更新用户
    @PutMapping("/updateUser/{userId}")
    public User updateUser(@PathVariable("userId") Integer userId,
                           @RequestBody User updatedUser) {
        // 更新用户信息
        if (userMap.containsKey(userId)) {
            updatedUser.setUserId(userId); // 确保ID一致
            userMap.put(userId, updatedUser);
            return updatedUser;
        }
        return new User(userId, "用户不存在", "");
    }

    // 4. DELETE - 删除用户
    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        if (userMap.containsKey(userId)) {
            userMap.remove(userId);
            return "用户ID " + userId + " 已删除";
        }
        return "用户ID " + userId + " 不存在";
    }
}