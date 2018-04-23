package com.tingfeng.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 通过 Redis 存储/获取 TGT 数据
 */
@Component
public class TgtServer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置用户TGT到Redis
     *
     * @param username
     * @param tgt
     * @param time
     * @return
     */
    public void setTGT(String username, String tgt, long time) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String value = operations.get(username);
        if (StringUtils.isNotBlank(value)) {
            System.out.println("用户：" + username + " 缓存中旧值：" + value + " 替换为新值：" + tgt);
        }
        operations.set(username, tgt, time, TimeUnit.SECONDS);
    }

    /**
     * 获取 TGT
     *
     * @param username
     * @return
     */
    public String getTGT(String username) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String value = operations.get(username);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return null;
    }

    /**
     * 删除 TGT
     *
     * @param username
     * @return
     */
    public void delTGT(String username) {
        stringRedisTemplate.delete(username);
    }

}
