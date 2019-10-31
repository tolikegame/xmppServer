package com.example.demo.service.impl;

import com.example.demo.Blowfish;
import com.example.demo.entity.LdapUser;
import com.example.demo.entity.Ofuser;
import com.example.demo.repository.LdapUserRepository;
import com.example.demo.repository.OfuserRepository;
import com.example.demo.response.LdapResponse;
import com.example.demo.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class LdapServiceImpl implements LdapService {
    
    @Autowired
    LdapUserRepository ldapUserRepository;
    @Autowired
    OfuserRepository ofuserRepository;

    @Override
    public LdapResponse saveLdap() {
        LdapResponse result = new LdapResponse();
        int count=0;
        List<LdapUser> ldapUsers = ldapUserRepository.findAll();
        List<String> ofusers = ofuserRepository.findAllUsername();

        //存入openfireDB
        String openfireKey = "6ZN90hunsMxtdpP"; //key存於DB的ofpropertyTable
        Ofuser ofuserSave = new Ofuser();
        for(LdapUser ldapUser : ldapUsers){
            String ldap = ldapUser.getAccount().toLowerCase();
            if(ofusers.contains(ldap)){
                continue;
            }

            //Base64
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] textByte = new byte[0];
            try {
                textByte = ldap.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String encodeLdap = encoder.encodeToString(textByte);

            ofuserSave.setUsername(ldap);
            Blowfish blowFish = new Blowfish(openfireKey);    //根据加密key初始化
            String encodedStr = blowFish.encryptString(encodeLdap);  //加密

            ofuserSave.setEncryptedpassword(encodedStr);
            ofuserSave.setName(ldapUser.getName());
            Date date = new Date();
            Long dateLong = date.getTime();
            ofuserSave.setCreateTime(String.format("%015d",dateLong));
            ofuserSave.setUpdateTime("0");
            ofuserRepository.save(ofuserSave);
            count++;
        }
        result.setStatus(true);
        result.setCount(count);
        return result;
    }
}
