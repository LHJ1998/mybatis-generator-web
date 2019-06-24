# mybatis-generator-web

> 很早之前就想做的一个东西，后来因为懒啊一直拖着，今天花了一下午搞定了，嘿嘿

使用WEB UI 逆向工程生成mapper文件

如果不使用WEB UI（或者更直观的操作方式），生成诸如实体类（pojo）、接口（dao）、xml配置文件你需要

1. 编写配置文件（最麻烦）
2. 执行java命令（虽然不是很长，但是也有点麻烦）

配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <classPathEntry location="mysql-connector-java-5.1.46.jar"/>
    <context id="my" targetRuntime="MyBatis3">

        <!-- 自动识别数据库关键字，默认false -->
        <property name="autoDelimitKeywords" value="true" />
        <!--使用``包括字段名，避免字段名与sql保留字冲突报错 -->
        <property name="beginningDelimiter" value="`" />
        <property name="endingDelimiter" value="`" />

        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://127.0.0.1:3306/music?useSSL=true" userId="root"
                        password="root"/>

        <javaModelGenerator targetPackage="com.lhj.music.pojo"
                            targetProject="src">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="com.lhj.music.mapper"
                         targetProject="src">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <javaClientGenerator targetPackage="com.lhj.music.dao"
                             targetProject="src" type="XMLMAPPER">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <table tableName="music_user" domainObjectName="MUser"
               enableCountByExample="true" enableUpdateByExample="true"
               enableDeleteByExample="true" enableSelectByExample="true"
               selectByExampleQueryId="true">
        </table>

    </context>
</generatorConfiguration>
```

```java
//需要执行的命令行命令
java -jar mybatis-generator-core-1.3.7.jar -configfile config.xml -overwrite
```

如果选择使用WEB UI就会简单很多了

![1561365939183](image\WEB UI.png)

如上图，操作很简单就可以生成了



打开方式：

运行jar包：`mybatis-generator-web.jar`

默认端口是8080，在浏览器中打开 http://localhost:8080/index即可访问到页面



目前只支持MySQL数据库..后续有时间慢慢加上去



中间出了一点小问题

SpringBoot打成jar包的时候用ResourcesUtils读取不到classpath下的文件了，然后查了网上的方法，使用ClassPathResource就可以搞定..学习了

然后偶然看到另一位的做法，在application.yml文件里面配置读取文件的路径 /usr/local/... 感觉以后用得上，先记下来...