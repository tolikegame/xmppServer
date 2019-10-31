package com.example.demo.controller;

import com.example.demo.model.MsgModel;
import com.example.demo.service.impl.XmppServiceImpl;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/xmpp")
public class XmppController {

    @Autowired
    XmppServiceImpl ofUserService;

    @PostMapping("/broadcast")
    public boolean broadcast(@RequestBody MsgModel xmppModel) throws InterruptedException, IOException, SmackException, XMPPException {
        return ofUserService.broadcast(xmppModel);
    }

    @PostMapping("/message")
    public String sendMsg(@RequestBody MsgModel xmppModel) throws InterruptedException, IOException, SmackException, XMPPException {
        return ofUserService.sendMsg(xmppModel);
    }
}
