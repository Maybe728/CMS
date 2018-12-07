package com.cn.cms.Demo;

import java.io.IOException;
import java.net.Socket;

/**
 * @AUTHER: HuangShiXing
 * @Date: 2018-12-07 21:03
 * @Description:自定义jedis
 */
public class HsxJedis {
    //set key value
    public static  String set(Socket socket,String key, String value) throws IOException {
        //
        StringBuffer str = new StringBuffer();
        str.append("*3").append("\r\n");
        str.append("$3").append("\r\n");
        str.append("set").append("\r\n");

        str.append("$").append(key.getBytes().length).append("\r\n");
        str.append("key").append("\r\n");

        str.append("$").append(key.getBytes().length).append("\r\n");
        str.append("value").append("\r\n");

        socket.getOutputStream().write(str.toString().getBytes());
        byte[] response = new byte[2048];
        socket.getInputStream().read(response);

        return new String(response);

    }
    public static  String get(Socket socket,String key) throws IOException {
        //
        StringBuffer str = new StringBuffer();
        str.append("*2").append("\r\n");
        str.append("$3").append("\r\n");
        str.append("get").append("\r\n");

        str.append("$").append(key.getBytes().length).append("\r\n");
        str.append("key").append("\r\n");


        socket.getOutputStream().write(str.toString().getBytes());
        byte[] response = new byte[2048];
        socket.getInputStream().read(response);

        return new String(response);

    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",6739);
        set(socket,"hsx","hi");
        System.out.println(get(socket,"hsx"));
    }
}
