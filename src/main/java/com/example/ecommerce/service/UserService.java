package com.example.ecommerce.service;

import com.example.ecommerce.dao.RoleDao;
import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);
//        Add new admin
        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserAddress("Vietnam");
        adminUser.setUserEmail("admin@gmail.com");
        adminUser.setUserPhone("12345678910");
//        Add new user
        User defaultUser = new User();
        defaultUser.setUserName("gokashinra");
        defaultUser.setUserPassword(getEncodedPassword("anhminhdx1"));
        defaultUser.setUserFirstName("Minh");
        defaultUser.setUserLastName("Nguyen");
        defaultUser.setUserAddress("VinhPhuc");
        defaultUser.setUserEmail("anhminh345dx@gmail.com");
        defaultUser.setUserPhone("12345678910");


        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        defaultUser.setRole(userRoles);
        userDao.save(defaultUser);

    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User createNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userDao.save(user);
    }
}
