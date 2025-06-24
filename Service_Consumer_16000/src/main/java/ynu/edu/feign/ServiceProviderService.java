package ynu.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ynu.edu.entity.User;

@FeignClient("provider-service")
public interface ServiceProviderService {
    // 1. GET - 查询用户
    @GetMapping("/user/getUserById/{userId}")
    User getUserById(@PathVariable("userId") Integer userId);

    // 2. POST - 创建用户
    @PostMapping("/user/createUser/{userId}")
    User createUser(@PathVariable("userId") Integer userId);

    // 3. PUT - 更新用户
    @PutMapping("/user/updateUser/{userId}")
    User updateUser(@PathVariable("userId") Integer userId,
                    @RequestBody User updatedUser);

    // 4. DELETE - 删除用户
    @DeleteMapping("/user/deleteUser/{userId}")
    String deleteUser(@PathVariable("userId") Integer userId);
}
