package com.cn.cms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.cms.annotation.SysLog;
import com.cn.cms.dto.ResultInfo;
import com.cn.cms.entity.OrderInfo;
import com.cn.cms.service.IOrderInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-11-20
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderInfoService iOrderInfoService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("order:view")
    public @ResponseBody
    ResultInfo<List<OrderInfo>> listData(OrderInfo orderInfo, Integer page, Integer limit){
        EntityWrapper<OrderInfo> wrapper = new EntityWrapper<>(orderInfo);
        if(orderInfo!=null&&orderInfo.getOrderStatus()!=null){
            wrapper.eq("order_status", orderInfo.getOrderStatus());
            orderInfo.setOrderStatus(null);
        }
        wrapper.orderBy("gmt_create");
        Page<OrderInfo> pageObj = iOrderInfoService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    /**
     * @param idArr
     * @return
     */
    @SysLog("批量删除订单操作")
    @RequestMapping("/delBatch")
    @RequiresPermissions("order:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iOrderInfoService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

//    @RequestMapping("/edit")
//    @RequiresPermissions("order:edit")
//    public @ResponseBody
//    ResultInfo<Boolean> edit(Order2 order){
//        Order2 order1 = iOrderService.selectById(order.getId());
//        order1.setName(user.getName());
//        order1.setRoleId(user.getRoleId());
//        order1.setState(user.getState());
//        boolean b = iUserService.updateById(us);
//        return new ResultInfo<>(b);
//    }

    @SysLog("查询是否有新的订单")
    @RequestMapping("/getNewOrders")
    public @ResponseBody
    ResultInfo<Integer> getNewOrders(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderStatus("1");
        EntityWrapper<OrderInfo> wrapper = new EntityWrapper<>(orderInfo);
        wrapper.like("order_status", orderInfo.getOrderStatus());
        orderInfo.setOrderStatus(null);
        int count = iOrderInfoService.selectCount(wrapper);
        return new ResultInfo<>(count);
    }

    @RequestMapping("/count")
    public @ResponseBody
    ResultInfo<Integer> count(){
        return new ResultInfo<>(iOrderInfoService.selectCount(new EntityWrapper<>()));
    }
}

