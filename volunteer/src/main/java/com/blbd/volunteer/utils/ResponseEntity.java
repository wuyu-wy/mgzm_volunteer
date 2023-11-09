package com.blbd.volunteer.utils;

import lombok.Data;

@Data
public class ResponseEntity {
    //状态码 200成功 500失败
    private String code;
    //数据
    private Object data;
    //提示信息
    private String message;
}
