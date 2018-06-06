# Eureka 高可用配置 #

创建项目 `hk-eureka-ha`，其实就是从 `hk-eureka-simple`项目复制过来的。

修改 `application.yml` 文件内容，修改后的内容请查看文件。配置了三个 profiles

修改 hosts 文件，添加如下内容 ：
```
127.0.0.1 peer1 peer2 peer3
```

使用 idea 分别启动三个服务。
浏览器访问 http://localhost:8765  或 http://localhost:8766 或 http://localhost:8767 查看高可用相关信息。


客户端可以连接任意一个eureka 服务 ，即可 某个或多个 eureka server 不可用，但至少还有一个可以，都能保证整个 eureka 服务的可用性。但是，如果客户端只配置某个 eureka server，如果这个eureka server 不可以，在启动client的时候，不能正常连接到 eureka 其它服务，所以建议 每个client配置连接所有的eureka server，以英文分号(`,`)分隔

事实上， eureka的注册信息不仅仅复制在server端，也会复制到client端。也就是说，即便eureka的server全部挂掉，client间任然是可以互通的！譬如client1 client2都已经注册在了server1 server2上，即便server1 server2全死掉了，Client1还是可以和client2进行互通，因为eureka的客户端也会复制所有的注册信息，当server全死掉后，客户端会根据自己本地的备份进行连接。

