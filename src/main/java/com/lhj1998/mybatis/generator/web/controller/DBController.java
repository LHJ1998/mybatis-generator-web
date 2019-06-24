package com.lhj1998.mybatis.generator.web.controller;

import com.lhj1998.mybatis.generator.web.common.CommonResult;
import com.lhj1998.mybatis.generator.web.dto.DBLinkInfo;
import com.lhj1998.mybatis.generator.web.dto.GenerateInfo;
import com.lhj1998.mybatis.generator.web.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/db")
public class DBController {

    @Autowired
    private DBService dbService;

    @PostMapping(value = "/link")
    public CommonResult link(@RequestBody DBLinkInfo info, HttpServletRequest request) throws SQLException {
        request.getSession().setAttribute("linkInfo", info);
        List<String> tables = dbService.link(info);
        return null == tables ? CommonResult.failed() : CommonResult.success(tables);
    }

    @PostMapping(value = "/generate")
    public CommonResult generate(@RequestBody GenerateInfo info, HttpServletRequest request){
        DBLinkInfo linkInfo = (DBLinkInfo) request.getSession().getAttribute("linkInfo");
        boolean success = dbService.generate(info, linkInfo);
        return success ? CommonResult.success() : CommonResult.failed();
    }

}
