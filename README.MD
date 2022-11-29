### IMPORTANT:
This project is from https://github.com/wuyouzhuguli/FEBS Fork. Since the original author has stopped updating, I will continue to update this project. Thanks again to the author

###FEBS background authority management system
![https://img.shields.io/badge/build-success-brightgreen.svg?longCache=true&style=flat-square](https://img.shields.io/badge/build-success-brightgreen.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/license-MIT-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-MIT-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/download-1k%2Fm-green.svg?longCache=true&style=flat-square](https://img.shields.io/badge/download-1k%2Fm-green.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springboot-2.0.4-yellow.svg?longCache=true&style=popout-square](https://img.shields.io/badge/springboot-2.0.4-yellow.svg?longCache=true&style=popout-square)
![https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?longCache=true&style=flat-square](https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?longCache=true&style=flat-square)
FEBS is a simple and efficient background authority management system. The basic framework of the project adopts a new Java Web development framework -- Spring Boot2.0.4, which eliminates the complicated XML configuration and makes the secondary development easier; The data access layer adopts Mybatis, and the general Maper and PageHelper plug-ins are introduced at the same time, which can quickly and efficiently add, delete, modify and query a single table, eliminating a large number of traditional XML configuration SQL codes; The current popular Apache Shiro is adopted as the security framework, which can achieve button level permission control; The front-end page is built with Bootstrap, the theme style is the latest design language of Google Material Design, and multiple sets of colors are available for selection. FEBS means Fast, Easy use, Beautiful and Safe.



Note: Due to limited energy, the Oracle branch stopped updating and deleting on August 21, 2018, and can be obtained from the group if necessary.

(https://shiro.mrbird.cc/)

Spring Security Version released:[https://github.com/wuyouzhuguli/FEBS-Security](https://github.com/wuyouzhuguli/FEBS-Security)

###Functional module

The system functional modules are as follows:
```
|-------------------------------------------|
|    ______ ______ ____   _____             |
|   |  ____|  ____|  _ \ / ____|            |
|   | |__  | |__  | |_) | (___              |
|   |  __| |  __| |  _ < \___ \             |
|   | |    | |____| |_) |____) |            |
|   |_|    |______|____/|_____/             |
|   Spring-Boot-Version: 2.0.4.RELEASE      |
|-------------------------------------------|
|
├─ System management
│  ├─ dictionary management
│  ├─ user management
│  ├─ menu management
│  ├─ role management
│  └─ Department management
├─ System monitoring
│  ├─ Online users
│  ├─ system log
│  ├─ Redis monitoring
│  └─ Redis terminal
│─ Network resources
│  ├─ One
│  │  ├─ prose
│  │  ├─ painting
│  │  └─ Language
│  ├─ weather query
│  ├─ film and television information
│  │  ├─ coming soon
│  │  └─ Hot
│  └─ one article a day
└─ Task scheduling
   ├─ Timed tasks
   └─ Dispatch log
```
###Technical selection

####Back end
-Basic framework: Spring Boot 2.0.4. RELEASE
-Persistence layer framework: Mybatis 3.4.5
-Security framework: Apache Shiro 1.4.0
-Template engine: Thymeleaf 3.0.9.RELEASE
-Database connection pool: Alibaba Druid 1.1.10
-Cache framework: Redis
-Log printing: logback
-Others: fastjson, poi, javacsv, quartz, etc.

####Front end
-Basic framework: Bootstrap 4
-JavaScript framework: jQuery
-Message component: Bootstrap notify
-Prompt box plug-in: SweetAlert2
-Tree plug-in: jsTree
-Tree Table plug-in: jqTreeGrid
-Table plug-in: BootstrapTable
-Form verification plug-in: jQuery validate
-Multi select drop-down box plug-in: multiple select
-Chart plug-in: Highcharts
-Time plug-in: daterangepicker

####Development environment
-Language: Java 8
- IDE：Eclipse Oxygen & IDEA 2018.1.4(Ultimate Edition)
-Dependency Management: Maven
-Database: MySQL 5.7
-Version management: SVN, git

##System preview
![f47c1606680527619bc462afea4d068f.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/64420208.jpg)

![e3d9fc443686ec84003496d742c783ad.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/50183418.jpg)

![2911d8fa7bbe2b6674854a5c7f59de7a.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/57316459.jpg)

![48a18207aa79b0e75cfd7258d61dab1e.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/31072514.jpg)

![155af0ed234a7fe040666b1b7818d42e.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/38600911.jpg)

![57a6c25f7b616fcf4e452da673154960.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/5832650.jpg)

![d4973ba38ff475543eab95839b5314dc.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/99045302.jpg)

![](http://olwqftdzl.bkt.clouddn.com/18-6-29/66445963.jpg)

![](http://olwqftdzl.bkt.clouddn.com/18-6-29/18024225.jpg)

### 主题预览

![2bbae66a0cc36ea96ca1ffe66951921d.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/21305793.jpg)

![a9f57431583b8ed9a5d18b7cf37bd053.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/22217970.jpg)

![d8cb6313fd2ee8eb793d443b83a78ce4.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/6580523.jpg)

![2bd554ce2511cd1e7e3e7b3b56216ed6.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/86512301.jpg)

![5e6d35e4390cf7f0752d7864ad701e2b.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/81830531.jpg)


