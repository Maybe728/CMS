#Mysql
   1.数据库设计三范式
        
        1NF：所有的列必须保持原子性，不能分割。
        
        2NF：所有的非主键列必须对主键列完全依赖，（针对联合主键）
        
        3NF：列之间不能存在传递性（A-->B-->C）
   
   2.锁
      
      1.类型：表级锁，页级锁，行级锁
        
        共享锁：读锁  读锁允许多个连接可以同一时刻并发的读取同一资源,互不干扰
        
        排它锁：写锁  一个写锁会阻塞其他的写锁或读锁，保证同一时刻只有一个连接
                     可以写入数据，同时防止其他用户对这个数据的读写。
               
        具体有如下几种recordLock,gapLock,next key lock。
        
      2.锁策略：锁的开销是较为昂贵的，锁策略其实就是保证了线程安全的同时获取最大的性能之间的平衡策略。
      
          1.行级锁：（NDBCluster,INNODB）颗粒度最小，发生锁定资源争抢的概率最低，
            给系统最大的并发处理能力，但是颗粒度小，导致行级锁在枷锁或者释放锁的时候需要耗费较大的资源，而且易发生死锁。
            （INNODB 只有WHERE条件为主键的时候才会行锁，其他情况下还是表锁）
            
          2.页级锁：BerkeleyDB存储引擎的锁定方式，会发生死锁，性能介于行级锁和表级锁之间。
          
          3.表级锁：（MyISAM，Memory，CSV，INNODB），表级锁是最大颗粒度的锁表机制，实现简单，获取锁和
            释放锁的速度很快。因为一次性将表锁了，所以避免了死锁，但是并发度最低。分为两种类型：一种是读锁，一种是写锁。
      
   3.索引
   
        msyql在5.6之后存储引擎默认为innodb之前为myIsam，无论是innodb还是myisam都支持B+Tree作为索引的数据结构，
        但是INNODB中，我们必须启用innodb_adaptive_hash_index参数来禁用或者启动（默认是启用）hash索引，然后hash
        索引是由INNODB存储引擎自动优化创建，我们干预不了。 
        
        索引的类型（聚簇索引和非聚簇索引），
            MYISAM索引文件和数据文件是分离的，分为data.MYD,jiegou.frm,index.MYI三个文件。
            
            MYISAM的索引类型是非聚簇索引，主键索引和非主键索引没有必然的关系，都是在叶子节点中存储指向数据的
            地址指针。在通过节点查询到地址指针后，在根据地址指针从data.MYD文件中取得数据。
        
        INNODB数据文件本身就是索引文件，索引类型是聚簇索引，叶子节点中包含了改节点指向的完整的数据记录。
        
        INNODB的主键索引和非主键锁引存在的联系，非主键索引中的叶子节点data域存着主键值，在根据非主键索引查询的时候，找到叶子节点中的
        主键数据，然后根据主键数据到主键索引中直接拿数据。
        
        为什么非主键索引叶子节点中存储的是主键值？
            节省空间：指向主键的节点，不用再存储一份完整的数据
            一致性：如果我们修改主键索引的值，只需要修改主键索引的值，不需要修改非主键索引的值
        
        为什么INNODB表必须要有主键值，而且推荐整型的自增值？
            因为主键不存在的话，INNODB也会以某一列唯一非空的列（如果也不存在，则自定义一个隐式的（主键，长度为6，类型为长整型）
            聚簇索引））定义为聚簇索引
            整型主键相较于字符串型少了一步ASCII码的转换
        
        为什么INNODB 比  MYISAM 快？
            MYISAM： .MYI(索引文件找到地址指针)---->.MYD(根据地址指针拿到数据)
            INNODB： 数据文件中直接拿到数据
        
   4.优化
      
      一般优化流程：
            1.通过脚本监控status，并生成图表观察
            2.是否有周期性的故障和波动
                否：使用processList或开启慢查询日志，获取有问题的sql
                    通过profiling和explain分析sql语句
                    sql语句等待时间长
                        否：sql语句等待时间长（优化sql，建立索引，优化表结构）
                        是：对服务器参数进行调优（如缓冲区，线程数）
                是：一般有访问高峰或缓存崩溃引起(增加缓存并修改缓存失效策略，使失效时间分散)
                
       一般以整型值为主的表在千万级以下，字符串为主的表在五百万以下是没有太大问题的。
       
      字段
           尽量使用TINYINT、SMALLINT、MEDIUM_INT作为整数类型而非INT，如果非负则加上UNSIGNED
           
           VARCHAR的长度只分配真正需要的空间
           
           使用枚举或整数代替字符串类型
           
           尽量使用TIMESTAMP而非DATETIME，
           
           单表不要有太多字段，建议在20以内
           
           避免使用NULL字段，很难查询优化且占用额外索引空间
           
           用整型来存IP
   
   
   5.存储引擎INNODB和MYISAM的区别
   
   6.事务
      
      解释：一组原子性的sql，或者说一个独立的工作单元。
      
      事务的ACID
      
          A:atomiciy原子性
          一个事务必须保证其中的操作要么全部执行，要么全部回滚，不可能存在只执行了一部分这种情况出现。
          
          C:consistency一致性
          数据必须保证从一种一致性的状态转换为另一种一致性状态。
          比如上一个事务中执行了第二步时系统崩溃了，数据也不会出现bill的账户少了100块，但是tim的账户没变的情况。
          要么维持原装（全部回滚），要么bill少了100块同时tim多了100块，只有这两种一致性状态的
          
          I：isolation隔离性
          在一个事务未执行完毕时，通常会保证其他Session 无法看到这个事务的执行结果
          
          D:durability持久性
          事务一旦commit，则数据就会保存下来，即使提交完之后系统崩溃，数据也不会丢失。
          
      隔离级别
      
          READ UNCOMMITTED(未提交读,可脏读)
          事务中的修改，即使没有提交，对其他会话也是可见的。可以读取未提交的数据----脏读
          
          READ COMMITTED(提交读或不可重复读，幻读)
          一般数据库都默认使用这个隔离级别（mysql不是），
          这个隔离级别保证了一个事务如果没有完全成功（commit执行完），事务中的操作对其他会话是不可见的。
          read committed隔离级别解决了脏读的问题，
          但是会对其他Session 产生两次不一致的读取结果（因为另一个Session 执行了事务，一致性变化）。
          
          REPEATABLE READ(可重复读)
          一个事务中多次执行统一读SQL,返回结果一样。
          这个隔离级别解决了脏读的问题，幻读问题。
          
          SERIALIZABLE(可串行化)
          最强的隔离级别，通过给事务中每次读取的行加锁，写加写锁，保证不产生幻读问题，
          但是会导致大量超时以及锁争用问题
          
          
   
            
               