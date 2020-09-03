package io.autotest.autotest.controller;

import io.autotest.autotest.entities.Role;
import io.autotest.autotest.entities.User;
import io.autotest.autotest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    IUserService iUserService ;
    @Autowired
    public UserController(@Qualifier("UserServiceImpl")IUserService iUserService){
        this.iUserService=iUserService;
    }
    @PostMapping("/save")
    public User save(@RequestBody   User user){
       return (iUserService.save(user));
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody User user){
        iUserService.delete(user);
    }
    @PutMapping("/update")
    public User update(@RequestBody User user){
        return (iUserService.update(user));
    }
    @PutMapping("/addRole")
    public User save(@RequestBody User user, @RequestBody Role role){
        return (iUserService.updateRole(user, role));
    }
    @GetMapping("/getAll")
    public List<User> getAll(){
        return iUserService.getAll();
    }
}
