package nl.hsleiden.IPRWC_Webshop_Backend.service;

import nl.hsleiden.IPRWC_Webshop_Backend.dao.RoleDao;
import nl.hsleiden.IPRWC_Webshop_Backend.dao.UserDao;
import nl.hsleiden.IPRWC_Webshop_Backend.model.Role;
import nl.hsleiden.IPRWC_Webshop_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
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
        this.roleDao.create(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        this.roleDao.create(userRole);

        User adminUser = new User();
        adminUser.setEmail("admin@pass");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        this.userDao.create(adminUser);

        User normalUser = new User();
        normalUser.setEmail("user@pass");
        normalUser.setPassword(getEncodedPassword("user@pass"));
        Set<Role> normalRoles = new HashSet<>();
        normalRoles.add(userRole);
        normalUser.setRole(normalRoles);
        this.userDao.create(normalUser);
    }

    public User register(User user) {
        if(emailAlreadyExists(user.getEmail())) {
            return null;
        }

        Role role = this.roleDao.get("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return this.userDao.create(user);
    }

    public String getEncodedPassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    private boolean emailAlreadyExists(String email) {
        return this.userDao.exists(email);
    }
}
