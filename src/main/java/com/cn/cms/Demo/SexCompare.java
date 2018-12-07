package com.cn.cms.Demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @AUTHER: HuangShiXing
 * @Date: 2018-12-07 21:21
 * @Description:
 */
public class SexCompare {
    public static void dels(String... keys) {
        Jedis jedis = new Jedis("127.0.0.1",6739);
        /**
         * 删除10000条数据
         */
        //性能差
        for (String id:keys) {
            jedis.del(id);
        }
        jedis.close();

        //性能更好
        Pipeline pipeline = jedis.pipelined();
        for (String id:keys) {
            pipeline.del(id);//append,将删除操作组装起来，一次提交
        }
        pipeline.sync();//正式执行del操作
        jedis.close();

    }

    public static void main(String[] args) {

    }
}
