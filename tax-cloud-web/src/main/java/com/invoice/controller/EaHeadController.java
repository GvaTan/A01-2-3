package com.invoice.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.EntityValidate.EntityValidate;
import com.zhys.ea.po.EaHead;
import com.zhys.excel.ExcelCell;
import com.zhys.excel.ImportExcelUtil;
import com.zhys.exception.BusinessException;
import com.zhys.result.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.invoice.fegin.EaHeadServiceFegin;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ResponseResult
@Slf4j
@RestController
@RequestMapping("ea")
@Api(value="报销接口",description="报销接口" )
public class EaHeadController {

    @Autowired
	private EaHeadServiceFegin service;


    

    @ApiOperation(value = "报销分页列表",notes="根据条件查询数据并分页")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true,paramType="query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageNum", value = "当前第几页", required = true, paramType="query",dataType = "Integer"),
    })
    @PostMapping(value = "page",produces = MediaType.APPLICATION_JSON_VALUE)
	public  Pages<List<EaHead>> pages( @RequestParam Integer pageSize,@RequestParam Integer pageNum,@ApiParam(name="invoiceHead",value="查询条件",required=true) @RequestBody(required=false) EaHead eaHead){
		return  service.pages(eaHead,pageSize,pageNum);
	}
    
    @ApiOperation(value = "报销保存",notes="报销保存")
    @PostMapping("save")
	public  Integer save(@RequestBody EaHead eaHead){
		return  service.save(eaHead);
	}
    
    @ApiOperation(value = "查询单条报销信息",notes="查询单条报销信息")
    @PostMapping("queryByEntity")
	public  EaHead queryByEntity(@RequestBody EaHead eaHead){
		return  service.queryByEntity(eaHead);
	}

    
    
    
}