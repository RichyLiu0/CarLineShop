package com.CarLineShop.controllers;
import com.CarLineShop.biz.CarSerialBiz;
import com.CarLineShop.entities.CarSerial;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * 功能简述:实体类<br>
 * 详细描述:<br>
 *
 * @author 刘伟锐
 * @date 2020/04/27
 */
@Api(value = "车系")
@RestController
@RequestMapping(value = "/base/carSerial")
public class carSerialService {

    private static Logger log = LoggerFactory.getLogger(carSerialService.class);

    @Autowired
    private CarSerialBiz biz;


    @ApiOperation(value = "获取列表",notes = "获取列表")
    @RequestMapping(path = "/getList", method = {RequestMethod.GET,RequestMethod.POST})
    public List<CarSerial> getLst()
    {
        return  biz.getLst();
    }



}