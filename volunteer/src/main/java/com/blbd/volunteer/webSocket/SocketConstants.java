package com.blbd.volunteer.webSocket;

public interface SocketConstants {

    /**
     * 消息类型：0心跳 1登录 2文字 3图片 4文件 5下线 6语音消息
     * HEART_BEAT,LOGIN,TEXT_MESSAGE,IMAGE,FILE,OFFLINE,VOICE
     *
     */
    interface MsgType {

        int HEART_BEAT = 0;

        int ONLINE = 1;

        int TEXT_MESSAGE = 2;

        int IMAGE = 3;

        int FILE= 4;

        int OFFLINE = 5;

        int VOICE = 6;

    }
}
