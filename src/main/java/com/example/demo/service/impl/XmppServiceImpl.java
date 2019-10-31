package com.example.demo.service.impl;

import com.example.demo.entity.Ofuser;
import com.example.demo.model.MsgModel;
import com.example.demo.repository.OfuserRepository;
import com.example.demo.service.XmppService;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@Service
public class XmppServiceImpl implements XmppService {

    @Autowired
    OfuserRepository ofuserRepository;

    final String USERNAME = "admin";
    final String PASSWORD = "admin";
    final String HOST ="192.168.11.49";
    final String XMPPDOMAIN ="desktop-ph8lrjf.tgfc.tw";

    @Override
    public boolean broadcast(MsgModel xmppModel) throws InterruptedException, XMPPException, SmackException, IOException {
        List<Ofuser> users = ofuserRepository.findAll();
        for (Ofuser ofuser : users){
            //聊天
            ChatManager chatManager = ChatManager.getInstanceFor(this.xmppLogin());
            EntityBareJid jid = JidCreate.entityBareFrom(ofuser.getUsername()+"@desktop-ph8lrjf.tgfc.tw");
            Chat chat = chatManager.chatWith(jid);
            chat.send(xmppModel.getMessage());
        }
        return true;
    }

    @Override
    public String sendMsg(MsgModel xmppModel) throws InterruptedException, XMPPException, SmackException, IOException {
        String message = xmppModel.getMessage();
        //聊天
        if(xmppModel.getStatus()==1){
        }else{
            String playerAccount = xmppModel.getAccount();
            if (playerAccount.contains(",")) {
                // 多帐号
                for (String account : playerAccount.split(",")) {
                    ChatManager chatManager = ChatManager.getInstanceFor(this.xmppLogin());
                    EntityBareJid jid = JidCreate.entityBareFrom(account+"@desktop-ph8lrjf.tgfc.tw");
                    Chat chat = chatManager.chatWith(jid);
                    chat.send(message);
                }
            } else {
                // 单帐号
                ChatManager chatManager = ChatManager.getInstanceFor(this.xmppLogin());
                EntityBareJid jid = JidCreate.entityBareFrom(xmppModel.getAccount()+"@desktop-ph8lrjf.tgfc.tw");
                Chat chat = chatManager.chatWith(jid);
                chat.send(message);
            }
        }

        return "發送成功";
    }

    private XMPPTCPConnection xmppLogin() throws IOException, InterruptedException, XMPPException, SmackException {
        XMPPTCPConnection.setUseStreamManagementDefault(false);
        XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder(); //XmppDomain 配置

        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled); //不開啟安全模式

        //登入帳號
        config.setUsernameAndPassword(USERNAME,PASSWORD);
        config.setConnectTimeout(86400000);
        config.setHost(HOST);
        config.setXmppDomain(XMPPDOMAIN);
        config.build();

        XMPPTCPConnection mConnection = new XMPPTCPConnection(config.build());
        mConnection.connect(); //Establishes a connection to the server
        mConnection.login(); //Logs in

        return mConnection;
    }

}
