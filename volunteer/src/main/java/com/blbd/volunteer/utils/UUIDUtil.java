package com.blbd.volunteer.utils;

import java.util.Date;
import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取一个UUID
     */
    public static String getOneUUID(){
        //获取UUID
        String s = UUID.randomUUID().toString();
        //去掉"-"符号
        return s.substring(0,8) + s.substring(9,13) + s.substring(14,18) +s.substring(19,23) + s.substring(24);
    }
    /**
     * 获取指定数目的UUID
     * @Param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] ss = new String[number];
        for(int i=0;i<number ;i++){
            ss[i] = getOneUUID();
        }
        return ss;
    }


    //时间戳函数
        public static Date getCurrentTime() {
            return new Date();
        }
}
