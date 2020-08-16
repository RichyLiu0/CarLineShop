package com.CarLineShop.biz;

import com.CarLineShop.dao.mapper.CarSerialMapper;
import com.CarLineShop.entities.CarSerial;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * @author Richy
 * @date 2020/08/16
 */
//@Service("zarSerialBiz")

@Component
public class CarSerialBiz extends ServiceImpl<CarSerialMapper, CarSerial>  {

    @Autowired
    private CarSerialMapper mapper;

    public List<CarSerial> getLst()
    {
        QueryWrapper<CarSerial> wrapper =new QueryWrapper<>();
         return mapper.selectList(wrapper);
    }

}
