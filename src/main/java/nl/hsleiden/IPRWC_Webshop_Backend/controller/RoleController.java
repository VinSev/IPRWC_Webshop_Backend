package nl.hsleiden.IPRWC_Webshop_Backend.controller;

import nl.hsleiden.IPRWC_Webshop_Backend.model.Role;
import nl.hsleiden.IPRWC_Webshop_Backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }
}
