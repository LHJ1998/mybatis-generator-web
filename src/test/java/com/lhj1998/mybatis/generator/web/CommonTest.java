package com.lhj1998.mybatis.generator.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class CommonTest {

    @Test
    public void test() throws IOException, DocumentException {

//        ClassPathResource resource = new ClassPathResource("config/config.xml");
//
////        File file = ResourceUtils.getFile("classpath:config/config.xml");
//
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(resource.getInputStream());
//        Element rootElement = document.getRootElement();
//        Element context = rootElement.element("context");
//
//        //添加连接信息
//        context.addElement("jdbcConnection")
//                .addAttribute("driverClass", "driverClassName")
//                .addAttribute("connectionURL", "url")
//                .addAttribute("userId", "username")
//                .addAttribute("password", "password");
//        //实体类包信息
//        Element pojoElement = context.addElement("javaModelGenerator")
//                .addAttribute("targetPackage", "package")
//                .addAttribute("targetProject", "src");
//        pojoElement.addElement("property").addAttribute("name", "enableSubPackages").addAttribute("value", "true");
//        pojoElement.addElement("property").addAttribute("name", "trimStrings").addAttribute("value", "true");
//        //XML文件信息
//        Element xmlElement = context.addElement("sqlMapGenerator")
//                .addAttribute("targetPackage", "package")
//                .addAttribute("targetProject", "src");
//        xmlElement.addElement("property").addAttribute("name", "enableSubPackages").addAttribute("value", "true");
//        //DAO文件信息
//        Element daoElement = context.addElement("javaClientGenerator")
//                .addAttribute("targetPackage", "package")
//                .addAttribute("targetProject", "src")
//                .addAttribute("type", "XMLMAPPER");
//        daoElement.addElement("property")
//                .addAttribute("name", "enableSubPackages").addAttribute("value", "true");
//        //表信息
//        context.addElement("table")
//                .addAttribute("tableName", "tableName")
//                .addAttribute("domainObjectName", "实体类名称")
//                .addAttribute("enableCountByExample", "true")
//                .addAttribute("enableUpdateByExample", "true")
//                .addAttribute("enableDeleteByExample", "true")
//                .addAttribute("enableSelectByExample", "true")
//                .addAttribute("selectByExampleQueryId", "true");
//
//        XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
//        FileOutputStream fos = new FileOutputStream("temp.xml");
//        writer.setOutputStream(fos);
//        writer.write(document);
//        writer.close();


    }

}
