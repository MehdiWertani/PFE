package io.autotest.autotest.controller;

import io.autotest.autotest.common.MailMessage;
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

    private final IUserService iUserService;

    @Autowired
    public UserController(@Qualifier("UserServiceImpl") IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return (iUserService.save(user));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody User user) {
        iUserService.delete(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        iUserService.deleteById(id);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return (iUserService.update(user));
    }

    @PutMapping("/addRole")
    public User save(@RequestBody User user, @RequestBody Role role) {
        return (iUserService.updateRole(user, role));
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return iUserService.getAll();
    }

    @PostMapping("/send_mail")
    public void sendEmails(@RequestBody MailMessage message) {
        iUserService.notifyUsersByMail(message);
    }
}
