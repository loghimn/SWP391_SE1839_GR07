package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.UserCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserUpdateRequest;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.service.UserService;



@RestController
//CRUD
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //Create user
    @PostMapping("/create")
    public User createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }
    //Get user by id
    @GetMapping("/{userId}")
        public User getUser(@PathVariable String userId) {
        return userService.findUserByUserId(userId);
    }
    //Update user by user id
    @PutMapping("/{userId}")
        public User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }
    //Delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}

