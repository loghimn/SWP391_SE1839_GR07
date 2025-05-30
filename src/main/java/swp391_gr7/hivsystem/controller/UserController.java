package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.UserCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserUpdateRequest;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }
    @GetMapping("/{userId}")
        public User getUser(@PathVariable String userId) {
        return userService.findUserByUserId(userId);
    }
    @PutMapping("/{userId}")
        public User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}

