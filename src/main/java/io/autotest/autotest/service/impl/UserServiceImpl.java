package io.autotest.autotest.service.impl;

import io.autotest.autotest.common.MailMessage;
import io.autotest.autotest.dao.IUserDao;
import io.autotest.autotest.entities.Role;
import io.autotest.autotest.entities.User;
import io.autotest.autotest.service.IUserService;
import io.autotest.autotest.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Set;

@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {


    private final IUserDao iUserDao;
    private final MailService mailService;

    @Autowired
    public UserServiceImpl(IUserDao iUserDao,
                           MailService mailService) {
        this.iUserDao = iUserDao;
        this.mailService = mailService;
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
    public User updateRole(User user, Role role) {
        user.getRoles().add(role);
        return (user);
    }

    @Override
    public List<User> getAll() {
        return iUserDao.findAll();
    }

    @Override
    public Set<String> findUserEmails() {
        return iUserDao.findUserEmails();
    }

    @Override
    public void notifyUsersByMail(MailMessage message) {
        String[] userEmails = findUserEmails()
                .stream()
                .filter(mail -> mail != null && !mail.isEmpty())
                .toArray(String[]::new);
        try {
            mailService.sendEmail(message.getSender(), message.getSubject(), message.getContent(), message.getAttachment(), userEmails);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
