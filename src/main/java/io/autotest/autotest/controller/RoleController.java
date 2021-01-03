package io.autotest.autotest.controller;

import io.autotest.autotest.dao.IRoleDao;
import io.autotest.autotest.entities.ERole;
import io.autotest.autotest.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleDao iRoleDao;

    @GetMapping("/get")
    public Role getrolebyid(@RequestParam Long id) {
        return (iRoleDao.getOne(id));

    }

    @GetMapping("/all")
    public List<String> getAllRoles() {
        return Stream.of(ERole.values()).map(Enum::name).collect(Collectors.toList());
    }
}
