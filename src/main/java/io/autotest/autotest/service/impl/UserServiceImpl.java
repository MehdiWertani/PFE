package io.autotest.autotest.service.impl;

import io.autotest.autotest.dao.IUserDao;
import io.autotest.autotest.entities.Role;
import io.autotest.autotest.entities.User;
import io.autotest.autotest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {
IUserDao iUserDao;
    @Autowired
   public UserServiceImpl(IUserDao iUserDao){
    this.iUserDao=iUserDao;
}
    @Override
    public User save(User user) {
        return (iUserDao.save(user));

    }

    @Override
    public void delete(User user) {
iUserDao.delete(user);
    }

    @Override
    public User update(User user) {
        return (iUserDao.save(user));
    }

    @Override
    public User updateRole(User user,Role role) {
        user.getRoles().add(role);
        return (user);

    }

    @Override
    public List<User> getAll() {
        return iUserDao.findAll();
    }

}
