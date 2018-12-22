# CMS

#Redis 学习          
        
        1.什么是RESP？
        
        Redis服务器与客户端通过RESP（REdis Serialization Protocol）
        协议通信。
        
        客户端以规定格式的形式发送命令给服务器；
        服务器在执行最后一条命令后，返回结果
        
        set deer deer
        *3 (*数组元素个数)
        $3 ($字符串长度)
        set
        $4
        deer
        $4
        deer
        
        
        
        2.如何将数据库数据快速插入Redis中？
        
            登录数据库
            select * from tableName;--查询到结果集
            登录redis（ip,redis端口）
            拿到结果集--pipe（管道）--redis 
#P6面试题
        
        IO（netty），多线程（并发编程），集合源码，JDK源码
        分布式（dubbo，springcloud），缓存memcached，redis，monodb
        搜索，elk，设计模式，zookeeper，nginx，mq，kafka
        tomcat优化，mysql优化，javaee规范（spring，dubbo，mybatis源码）
        
# Mybatis 源码解读

    第一步：读取配置文件构建configuration对象
        1.
        
# 前端
    
    
    1.数组去重
    
    let array = [1,2,1,2,1,2];
    array = Array.from(new Set(array));