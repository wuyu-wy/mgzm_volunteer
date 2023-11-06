package com.blbd.volunteer.webSocket;

public interface SocketConstants {
    /**
     * 消息类型
     */
    interface MsgType {
        /**
         * 消息类型：0心跳 1登录 2文字 3图片 4文件
         * HEART_BEAT,LOGIN,TEXT_MESSAGE,IMAGE,FILE
         *
         */
        int HEART_BEAT = 0;

        int ONLINE = 1;

        int TEXT_MESSAGE = 2;

        int IMAGE = 3;

        int FILE= 4;

    }
}
