package com.lhj1998.mybatis.generator.web.service.impl;

import com.lhj1998.mybatis.generator.web.dto.DBLinkInfo;
import com.lhj1998.mybatis.generator.web.dto.GenerateInfo;
import com.lhj1998.mybatis.generator.web.service.DBService;
import com.lhj1998.mybatis.generator.web.utils.DBUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBServiceImpl implements DBService {

    private static final String TARGET_PROJECT = "src";

    private static final String DEFAULT_FILE_NAME = "temp.xml";

    @Override
    public List<String> link(DBLinkInfo info) throws SQLException {
        if(null == info) return null;
        Connection connection = DBUtil.getConnection(info);
        if(null == connection) return null;
        ResultSet rs = connection.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
        List<String> tables = new ArrayList<>();
        while(rs.next()){
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

    @Override
    public boolean generate(GenerateInfo generateInfo, DBLinkInfo linkInfo) {
        if(null == generateInfo || null == linkInfo) return false;
        try{
            if(!createDocument(generateInfo, linkInfo)) return false;
            //如果当前文件夹内没有一个TARGET_PROJECT的文件，生成一个
            File file = new File(TARGET_PROJECT);
            if(!file.exists()) file.mkdirs();
            List<String> warnings = new ArrayList<>();
            ConfigurationParser parser = new ConfigurationParser(warnings);
            Configuration configuration = parser.parseConfiguration(new File(DEFAULT_FILE_NAME));
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warnings);
            generator.generate(null);
            //生成完毕以后删除临时文件 DEFAULT_FILE_NAME
            File tempFile = new File(DEFAULT_FILE_NAME);
            tempFile.delete();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean createDocument(GenerateInfo generateInfo, DBLinkInfo linkInfo) {
        try{
            ClassPathResource resource = new ClassPathResource("config/config.xml");
//            File file = ResourceUtils.getFile("classpath:config/config.xml");

            SAXReader reader = new SAXReader();
            Document document = reader.read(resource.getInputStream());
            Element rootElement = document.getRootElement();
            Element context = rootElement.element("context");

            //添加连接信息
            context.addElement("jdbcConnection")
                    .addAttribute("driverClass", linkInfo.getDriver())
                    .addAttribute("connectionURL", linkInfo.getUrl())
                    .addAttribute("userId", linkInfo.getUsername())
                    .addAttribute("password", linkInfo.getPassword());
            //实体类包信息
            Element pojoElement = context.addElement("javaModelGenerator")
                    .addAttribute("targetPackage", generateInfo.getPojo())
                    .addAttribute("targetProject", TARGET_PROJECT);
            pojoElement.addElement("property").addAttribute("name", "enableSubPackages").addAttribute("value", "true");
            pojoElement.addElement("property").addAttribute("name", "trimStrings").addAttribute("value", "true");
            //XML文件信息
            Element xmlElement = context.addElement("sqlMapGenerator")
                    .addAttribute("targetPackage", generateInfo.getMappers())
                    .addAttribute("targetProject", TARGET_PROJECT);
            xmlElement.addElement("property").addAttribute("name", "enableSubPackages").addAttribute("value", "true");
            //DAO文件信息
            Element daoElement = context.addElement("javaClientGenerator")
                    .addAttribute("targetPackage", generateInfo.getMapper())
                    .addAttribute("targetProject", TARGET_PROJECT)
                    .addAttribute("type", "XMLMAPPER");
            daoElement.addElement("property")
                    .addAttribute("name", "enableSubPackages").addAttribute("value", "true");
            for(String table : generateInfo.getTable()){
                //表信息
                context.addElement("table")
                        .addAttribute("tableName", table)
                        .addAttribute("enableCountByExample", "true")
                        .addAttribute("enableUpdateByExample", "true")
                        .addAttribute("enableDeleteByExample", "true")
                        .addAttribute("enableSelectByExample", "true")
                        .addAttribute("selectByExampleQueryId", "true");
            }

            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
            FileOutputStream fos = new FileOutputStream(DEFAULT_FILE_NAME);
            writer.setOutputStream(fos);
            writer.write(document);
            fos.close();
            writer.close();

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
