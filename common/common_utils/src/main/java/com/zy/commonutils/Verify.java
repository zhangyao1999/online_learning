package com.zy.commonutils;


import lombok.Data;

/**
 * @author ZY
 * @create 2022/4/23 15:57
 */
@Data
public  class Verify {
    public static  boolean isString (String s){
        System.out.println(s);
        if(s == null || "".equals(s) ){
            return  true;
        }
        return  false;
    }
    public  static boolean iszhengshu(Integer i){
        if(i<= 0 || i == null ){
            return true;
        }
        return false;
    }
}
