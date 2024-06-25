package com.example.demo1.utils;

import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class Getip {
    @Value("${server.port}")
    private String port;
    public String getip(){
        try {
            // 获取所有网络接口的枚举
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                // 过滤掉不是WLAN的接口
                System.out.println(networkInterface.getName());
                if (networkInterface.getName().contains("wireless") ||
                        networkInterface.getName().startsWith("wlan") ||
                        networkInterface.getName().startsWith("wl")) {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                        // 只获取IPv4地址，并且排除回环地址
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof java.net.Inet4Address) {
                            if(inetAddress.getHostAddress().startsWith("169.254")){
                                continue;
                            }
                            System.out.println("Wireless LAN adapter WLAN IP address: " + inetAddress.getHostAddress());
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public String getPort() {
        return port;
    }
}
