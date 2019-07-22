package com;


import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetResource {
    public static void main(String argss[]) {
        for (int i = 0; i < 1000; i++) {
            double cert = (new Date()).getTime() + Math.random();
            String url = "http://mp.weixin.qq.com/mp/verifycode?cert=" + cert;
            System.out.println(url);

            Map<String, String> requestHeaders = new HashMap<String, String>();
            requestHeaders.put("Connection", "keep-alive");
            requestHeaders.put("Host", "weixin.sogou.com");
            requestHeaders.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            requestHeaders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            requestHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

            Map<String, String> resultUrl4 = sendGetToGetPicture(url, requestHeaders, String.valueOf(i));
            String cookie = resultUrl4.get("responseCookie");
        }
    }

    //发送get请求获取图片
    public static Map<String, String> sendGetToGetPicture(String url, Map<String, String> requestHeaders, String title) {
        Map<String, String> responseMap = new HashMap<String, String>();
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(100000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(url);
        if (!(null == requestHeaders)) {
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                getMethod.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            httpClient.executeMethod(getMethod);

            File storeFile = new File("C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\down\\" + title + ".jpg");

            FileOutputStream output = new FileOutputStream(storeFile);
            //得到网络资源的字节数组,并写入文件
            output.write(getMethod.getResponseBody());
            output.close();

            String cookiestr = "";
            Cookie[] cookies = httpClient.getState().getCookies();
            if (!(cookies.length == 0)) {
                for (int i = 0; i < cookies.length; i++) {
                    cookiestr = cookiestr + cookies[i].toString() + ";";
                }
                cookiestr = cookiestr.substring(0, cookiestr.length() - 1);
            }

            String result = getMethod.getResponseBodyAsString();
            responseMap.put("responseCookie", cookiestr);
            responseMap.put("responseContext", result);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        getMethod.releaseConnection();

        return responseMap;
    }
}
