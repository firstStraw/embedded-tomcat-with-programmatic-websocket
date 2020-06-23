package com.github.firststraw;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoEndpoint {

    @OnOpen
    public void onOpen(final Session session) throws IOException {
        session.getBasicRemote().sendText("You have connected with session ID: " + session.getId());
    }

    @OnMessage
    public void onMessage(final Session session, final String msg) throws IOException {
        session.getBasicRemote().sendText("You said: " + msg);
    }

    @OnClose
    public void onClose(final Session session) {
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
        Logger.getLogger(EchoEndpoint.class.getName())
                .log(Level.SEVERE, "Error, session: " + session.getId(), throwable);
    }
}
