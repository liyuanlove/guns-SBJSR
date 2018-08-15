# 基于Guns V4.0

## 介绍
基于开源项目Guns,整合springboot + shiro + spring-data-jpa + spring-data-redis + beetl! 
Guns包含许多基础模块(用户管理,角色管理,部门管理,字典管理等10个模块),可以直接作为一个后台管理系统的脚手架! 

## 开发环境
```
IntelliJ IDEA: 2017.3
Java version: 1.8.0_152
Apache Maven: 3.5.2
Your MySQL Server version: 5.7.20 MySQL Community Server (GPL)
SpringBoot: 2.0.3.RELEASE
Apache Shiro: 1.4.0
Beetl: 2.8.5
Redis server v=3.2.100
```

## 管理系统功能
1.用户管理 2.角色管理 3.部门管理 4.菜单管理 5.字典管理 6.业务日志 7.登录日志 8.监控管理 9.通知管理 
10.代码生成(前端生成较为鸡肋故移除，修改为后台一次性生成，根据需要复制到项目中重构后使用)

## 主要工作

> * 版本升级：将spring boot升级到2.0.3版本,并升级各个依赖包的版本！
> * 将持久层的框架替换为spring-boot-starter-data-jpa，自定义了父接口BaseDao,service层的IBaseService和BaseServiceImpl
> * 实现了代码自动生成的JPA版本代码,优化了模板文件并实现了JPA的dao,service层的btl文件
> * 移除了可视化代码生成功能(对开发者的帮助较为鸡肋)
> * 结合代码自动生成功能重写了service层和DAO层代码
> * 选取spring-boot-starter-data-redis作为整个项目的缓存
> * 选取protostuff作为redis的序列化方案并整合到redis中
> * 整合redis作为shiro的缓存
> * 采用自定义的shiro过滤器控制访问（删减了部分硬编码代码）
> * 采用重新打包kaptcha-spring-boot-starter作为验证码生成方案
> * 借助lombok简化了core,generator包的代码
> * 废弃了map + warpper方案，将处理过后的PO数据保存到VO对象，再返回给前端
> * 简化日志AOP功能(可根据业务场景进一步简化)
> * 采用javax.validation作为后台表单验证的工具
> * 修复了富文本编辑器乱码bug
> * 对service进行了单元测试

## 将redis作为windows服务安装
```
1,下载redis并解压到一个目录下，然后切换到该目录下，也就是redis-server.exe文件所在的目录
2,在cmd下执行 redis-server --service-install redis.windows.conf
3,服务安装成功后启动服务 redis-server --service-start
卸载命令：
 redis-server --service-uninstall  
```

## 运行
> * http://localhost:8080/guns/login
> * 用户名：admin
> * 密码：111111

## 结语
匆忙完成,难免有纰漏之处