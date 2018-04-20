package com.tingfeng.controller;

import com.tingfeng.utils.HttpProxy;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Arrays;

/**
 * Cas代理回调处理
 */
@RestController
@RequestMapping("/proxy")
public class CasProxyController {

    @GetMapping("/users")
    public String proxyUsers(HttpServletRequest request, HttpServletResponse response) {

        String result = "无结果";
        try {
            String serviceUrl = "http://client2.com:8889/user/users";

            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();

            //1、获取到AttributePrincipal对象
//            AttributePrincipal principal = AssertionHolder.getAssertion().getPrincipal();
            if (principal == null) {
                return "用户未登录";
            }

            //2、获取对应的(PT)proxy ticket
            String proxyTicket = principal.getProxyTicketFor(serviceUrl);
            if (proxyTicket == null) {
                return "PGT 或 PT 不存在";
            }

            //3、请求被代理应用时将获取到的proxy ticket以参数ticket进行传递
            String url = serviceUrl + "?ticket=" + URLEncoder.encode(proxyTicket, "UTF-8");
            result = HttpProxy.httpRequest(url, "", HttpMethod.GET);

            System.out.println("result结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @GetMapping("/books")
    public String proxyBooks(HttpServletRequest request, HttpServletResponse response) {

        String result = "无结果";
        try {
            String serviceUrl = "http://client2.com:8889/book/books";

            //1、获取到AttributePrincipal对象
            AttributePrincipal principal = AssertionHolder.getAssertion().getPrincipal();
            if (principal == null) {
                return "用户未登录";
            }

            //2、获取对应的(PT)proxy ticket
            String proxyTicket = principal.getProxyTicketFor(serviceUrl);
            if (proxyTicket == null) {
                return "PGT 或 PT 不存在";
            }

            //3、请求被代理应用时将获取到的proxy ticket以参数ticket进行传递
            String url = serviceUrl + "?ticket=" + URLEncoder.encode(proxyTicket, "UTF-8");
            result = HttpProxy.httpRequest(url, "", HttpMethod.GET);

            System.out.println("result结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
