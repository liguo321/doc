# cookie

domain .hnx.com  .24.127

登录成功后，一次性将用户所有单点系统的跨域cookie写到浏览器 hashSet

response.addCookie

等同于：response.addHeader("Set-Cookie", "")?

直接通过中台登录（中台必须用dns方式?），不需要网关重定向 



# 环境

## 密码设置

redis.conf   requirepass foobared



# 缓存更新

* 先更新数据库，更新成功后，让缓存失效
* 更新数据的时候，只更新缓存，不更新数据库，然后同步异步调度去批量更新数据库

# 缓存击穿

## 缓存穿透

* 查定一定不存在的数据的恶意请求，redis不存在，穿透redis

  key -> '&&' 不存在保存映射

* 布隆过滤器

## 缓存击穿

所有key同时expire，打到db数据库

* key设置随机expire时间

* redis设置互斥锁 setnx，排队load

## 缓存失效

* redis挂掉。master salve模式
* 设置策略，保证服务不挂掉。业务不重要抛异常

# 功能

* 数据缓存  散列 JSON （组织数据，热点数据）

* 单点登录 

* 分布式id  字符类型的递增数字 set  mic:age 18    /   incr mic:age  原子递增

  incrby key increment 递增指定数

   decr key 原子递减 

  验证码输入次数，短信重发机制（key的过期时间，重试次数）

* 分布式锁  setnx 防止缓存击穿 + 超时时间 expire ttl==-1检测是否设置

* 分布式消息队列 列表类型  lpush/lpop  rpush/rpop

* 网站访问统计热搜 有序集合 z

* //发布订阅的消息模式

* 默认16个数据库，默认为0，可据系统需求指定不同数据库select dbid(0-15)

* 多模块使用时，key的命名策略

  对象类型：对象id：对象属性：对象子属性

  不能太长

  对key进行分类，统一管理



# redis 事务处理

multi 开启事务

set加入事务队列

exec 执行事务

# 安装

## linux

**依赖**

make && make install 后，生成src目录

在redis根目录，安装到指定目录

make install PREFIX=/mic/data/program/redis

cp redis.conf ../redis/redis.conf

cd bin/

./  redis-server ../redis.conf

后台进程

vi redis.conf

daemonize yes

停止 ./redis-cli shutdown 



### master-slave

三台服务器安装好redis后 make / make install / make test

cd /opt/data/program

mkdir redis

cd redis-3.2.8

make install PREFIX=/opt/data/program/redis

cd ../redis

cp ../redis-3.2.8/redis.conf redis.conf

vim redis.conf

```properties
# bind 127.0.0.1
daemonize yes
protected-mode no
```

* 主

* 从

  `slaveof 主ip 6379`

  读取同步数据策略 `slave-serve-stale-data yes/no`

 重启

```shell
cd bin
./redis-cli shutdown
./redis-server ../redis.conf
./redis-cli
>info replication
```

从节点测试同步

replconf listening-port 6379

sync

* 哨兵

  备份 cp redis.conf redis.conf.bak

  ```shell
  cd redis
  cp ../redis-3.2.8/sentinel.conf sentinel.conf
  vim sentinel.conf
  port 
  sentinel monitor mymaster masterip 6379 1
  
  cd bin
  ./redis-sentinel ../sentinel.conf
  --test
  ./redis-cli shutdown  //master
  
  --哨兵集群
  在每个哨兵配置同样的master
  ```

### 集群(数据分片)

* codis代理
* cluster



### ip

* 哨兵模式   哨兵集群ip  sentinel
* 集群ip  master节点ip   cluster



## windows

```properties
https://github.com/ServiceStack/redis-windows redis-64.3.0.503
redis.windows.conf
maxmemory  1024000000
requirepass  123456
redis-server.exe redis.windows.conf


# 安装redis服务
redis-server --service-install redis.windows.conf --loglevel verbose
# 启动服务
redis-server --service-start
# 停止服务
redis-server --service-stop
# 启动指定的配置文件
redis-server --service-start redis.windows-service.conf
```



# 方案

[springboot redis 分布式锁](https://www.cnblogs.com/carrychan/p/9431137.html)

* 主从数据不同步

  将主的rdb文件copy至从



