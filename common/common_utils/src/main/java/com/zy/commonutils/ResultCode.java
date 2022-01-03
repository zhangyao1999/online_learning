package com.zy.commonutils;

import lombok.Data;

/**
 * @author ZY
 * @create 2022/1/3 19:51
 */

public enum ResultCode {
    SUCCESS(20000) ,
    ERROR(20001);

    private Integer code ;

    private ResultCode (Integer integer){
        this.code=integer;
    }

    public Integer getCode() {
        return code;
    }
}
