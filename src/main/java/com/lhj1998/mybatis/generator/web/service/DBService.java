package com.lhj1998.mybatis.generator.web.service;

import com.lhj1998.mybatis.generator.web.dto.DBLinkInfo;
import com.lhj1998.mybatis.generator.web.dto.GenerateInfo;
import org.mybatis.generator.exception.InvalidConfigurationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DBService {

    List<String> link(DBLinkInfo info) throws SQLException;

    boolean generate(GenerateInfo generateInfo, DBLinkInfo linkInfo);

}
