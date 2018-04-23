package com.tingfeng.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    private static final Logger logger = LogManager.getLogger();

    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = "";
        ip = request.getHeader("realClientIP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            logger.info("get ip form realClienUp and ip is " + ip);
            return ip;
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            logger.info("get ip form X-Real-IP and ip is " + ip);
            return ip;
        }
        logger.info("get ip form RemoteAddr and ip is " + ip);
        ip = request.getRemoteAddr();
        return ip;
    }
}
