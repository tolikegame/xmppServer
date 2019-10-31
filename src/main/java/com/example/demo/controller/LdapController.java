package com.example.demo.controller;

import com.example.demo.response.LdapResponse;
import com.example.demo.service.impl.LdapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ldap")
public class LdapController {
    @Autowired
    LdapServiceImpl ldapService;

    @PostMapping("/saveLdap")
    public LdapResponse saveLdap(){
        return ldapService.saveLdap();
    }
}
