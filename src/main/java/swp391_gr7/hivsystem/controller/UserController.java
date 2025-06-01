package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //http://localhost:8080/user/create
    @PostMapping("/create")
    public User createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }
    //Get user by id
    //http://localhost:8080/user/userId  id tu phat sinh
    @GetMapping("/{userId}")
        public User getUser(@PathVariable int userId) {
        return userService.findUserByUserId(userId);
    }
    //Update user by user id
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/updtae/{userId}")
        public User updateUser(@PathVariable int userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }
    //Delete user by id
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}

