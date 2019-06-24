package com.lhj1998.mybatis.generator.web.common;

import lombok.Data;

@Data
public class CommonResult {

    private Integer code;

    private String msg;

    private Object data;

    public static final Integer SUCCESS = 200;

    public static final Integer FAILED = 505;

    private CommonResult(Integer code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static CommonResult success(String msg, Object data){
        return new CommonResult(SUCCESS, msg, data);
    }

    public static CommonResult success(Object data){
        return new CommonResult(SUCCESS, "操作成功", data);
    }

    public static CommonResult success(){
        return new CommonResult(SUCCESS, "操作成功", null);
    }

    public static CommonResult failed(String msg, Object data){
        return new CommonResult(SUCCESS, msg, data);
    }

    public static CommonResult failed(Object data){
        return new CommonResult(SUCCESS, "操作异常", data);
    }

    public static CommonResult failed(){
        return new CommonResult(SUCCESS, "操作成功", null);
    }

}
