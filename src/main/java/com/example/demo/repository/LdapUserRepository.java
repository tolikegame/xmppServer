package com.example.demo.repository;

import com.example.demo.entity.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;

import java.util.List;

public interface LdapUserRepository extends LdapRepository<LdapUser> {
    List<LdapUser> findAll();
}
