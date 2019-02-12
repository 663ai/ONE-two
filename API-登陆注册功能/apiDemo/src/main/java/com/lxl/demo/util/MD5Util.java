package com.lxl.demo.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.thymeleaf.expression.Strings;

public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md2Hex(src);
    }
    private static final String salt = "lan";
    //对应第一次前端加密
    public static String inputToForm(String inputPass){
        String str = inputPass + salt;
        return md5(str);
    }
    //对应第二次加密
    public static String  formToDB(String formPass,String dbSalt){
        String str = formPass + dbSalt;
        return md5(str);
    }
    public static String inputToDb(String inputPass,String dbSalt){
        String formPass = inputToForm(inputPass);
        String dbPass = formToDB(formPass, dbSalt);
        return dbPass;
    }
    public static void main(String args[]){
        System.out.println(inputToDb("1234", "user"));
    }
}
