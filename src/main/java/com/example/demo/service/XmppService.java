package com.example.demo.service;

import com.example.demo.model.MsgModel;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;

public interface XmppService {
    boolean broadcast(MsgModel xmppModel) throws InterruptedException, XMPPException, SmackException, IOException;

    String sendMsg(MsgModel xmppModel) throws InterruptedException, XMPPException, SmackException, IOException;
}
