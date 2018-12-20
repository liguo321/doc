一、前言
最近搞了一台腾讯云内网服务器（把公网带宽调到了0Mbps），为了使内网服务器也能连上外网，稍微折腾了一下下Http代理。

本文以Squid代理服务器为栗子，简单介绍一下基本用法

二、安装Squid服务器
首先，在一台能同时连接公网和内网服务器的服务器上安装Squid代理服务器

yum install squid -y
然后就安装完了，是不是很简单呢（鬼才懒得编译呢）

三、配置Squid服务器
主要需要配置的是三个部分：

1、配置允许使用代理的IP地址
#此处使用10.0.0.0/16代表的是，整个10.0.x.x IP段都能使用（x代表任意255内的数字）
acl localnet src 10.0.0.0/16

#如果是限定单个IP使用，则是10.0.0.1/32
acl localnet src 10.0.0.1/32
2、配置允许访问的IP列表
第一个步骤是将10.0.0.0/16整个IP段命名标记为localnet，现在要将localnet添加进允许使用的列表中

#此处代表允许访问的列表包括localhost、manager、localnet三个IP段
http_access allow localhost manager localnet
3、配置Squid监听地址
这个步骤是为了进一步加强安全措施，防止代理服务器被滥用（盗用）

#最简便的规则，监听所有IP的3128端口，不安全，不建议使用
http_port 0.0.0.0:3128

#仅监听内网IP的3128端口，相对安全，建议使用
http_port 10.0.0.1:3128
四、使用代理
1、yum代理
#编辑/etc/yum.conf文件
vim /etc/yum.conf

#查找并修改或追加以下语句（将10.0.0.1修改为你的Squid配置的监听地址或服务器的IP）：

proxy=http://10.0.0.1:3128/ 
2、wget代理
#编辑/etc/wgetrc文件
vim /etc/wgetrc

#查找并修改或追加以下语句（将10.0.0.1修改为你的Squid配置的监听地址或服务器的IP）：

http_proxy=http://10.0.0.1:3128/
ftp_proxy=http://10.0.0.1:3128/
3、环境变量
#编辑/etc/profile文件
vim /etc/profile

#查找并修改或追加以下语句（将10.0.0.1修改为你的Squid配置的监听地址或服务器的IP）：

http_proxy=http://10.0.0.1:3128/
ftp_proxy=http://10.0.0.1:3128/

export http_proxy
export ftp_proxy