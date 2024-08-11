package com.why.bigevent.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

/**
 * WebSocketServer
 */
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    private static Map<String, Session> sessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("WebSocket opened: " + sid);
        sessions.put(sid, session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        System.out.println("Received message: " + message + " from " + sid);
    }

    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("WebSocket closed: " + sid);
        sessions.remove(sid);
    }

    public void sendToALlClient() {
        for (Session session : sessions.values()) {
            try {
                session.getBasicRemote().sendText("Hello, all clients!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}