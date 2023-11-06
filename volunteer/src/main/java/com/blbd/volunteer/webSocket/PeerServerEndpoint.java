package com.blbd.volunteer.webSocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;


@Component
@ServerEndpoint("/peerServerEndpoint/{peerId}")
public class PeerServerEndpoint {

    private static final Map<String, Session> peerIdSessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("peerId") String peerId) {
        //log.info("on open:the session is is :{},the peer id is:{}", session.getId(), peerId);
        Session removedSession = PeerServerEndpoint.peerIdSessionMap.remove(peerId);
        try {
            if (removedSession != null) {//Objects.nonNull(removedSession)
                removedSession.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            PeerServerEndpoint.peerIdSessionMap.put(peerId, session);
            refreshOnlineSessionsList();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("peerId") String peerId) {
        //log.warn("on close:the session is is :{},the peer id is:{}", session.getId(), peerId);

        Session removedSession = PeerServerEndpoint.peerIdSessionMap.remove(peerId);

        try {
            if (removedSession != null) {
                removedSession.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            refreshOnlineSessionsList();
        }
//        try {
//            if (Objects.nonNull(removedSession)) {
//                removedSession.close();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            refreshOnlineSessionsList();
//        }
    }

    @OnError
    public void onError(Session session, Throwable e, @PathParam("peerId") String peerId) {
        //log.error("on error:the session is is :{},the exception class is: {},the peer id is:{}", session.getId(), e.getClass(), peerId);
        onClose(session, peerId);

        e.printStackTrace();
    }

    private void refreshOnlineSessionsList() {
        for(Map.Entry<String, Session> entry : PeerServerEndpoint.peerIdSessionMap.entrySet()) {
            String key = entry.getKey();
            Session value = entry.getValue();

            value.getAsyncRemote().sendText(JSON.toJSONString(PeerServerEndpoint.peerIdSessionMap.keySet()));
        }
//        PeerServerEndpoint.peerIdSessionMap.forEach((key, value) -> {
//            value.getAsyncRemote().sendText(JSON.toJSONString(PeerServerEndpoint.peerIdSessionMap.keySet()));
//        });
    }

}