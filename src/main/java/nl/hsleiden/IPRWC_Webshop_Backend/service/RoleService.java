package nl.hsleiden.IPRWC_Webshop_Backend.service;

import nl.hsleiden.IPRWC_Webshop_Backend.dao.RoleDao;
import nl.hsleiden.IPRWC_Webshop_Backend.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role create(Role role) {
        return roleDao.create(role);
    }
}
