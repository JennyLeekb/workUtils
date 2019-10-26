package com.ld.support.consts;

import lombok.Data;

/**
 * @Description
 * @Author lkb
 * @CreateDate: 2019/10/19
 */
@Data
public class BaseReturnVo {

    private int result;
    private String msg;
    private Object data;


    private static final String OK = "OK";
    private static final String FAIL = "FAIL";

    public BaseReturnVo(int result, String msg){
        this.result = result;
        this.msg = msg;
    }


    public BaseReturnVo(int result, String msg, Object data){
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public static BaseReturnVo success(){
        return new BaseReturnVo(1,OK);
    }

    public static BaseReturnVo success(Object data){
        return new BaseReturnVo(1,OK,data);
    }

    public static BaseReturnVo fail(String msg){
        return new BaseReturnVo(0,msg);
    }

}
