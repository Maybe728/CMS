package com.cn.cms.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Auto Generator
 * @since 2019-11-20
 */
@TableName("order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 订单类型
     */
	@TableField("order_type")
	private String orderType;
	@TableField("order_price")
	private BigDecimal orderPrice;
    /**
     * 订单地址
     */
	private String address;
    /**
     * 订单状态
     */
	@TableField("order_status")
	private String orderStatus;
    /**
     * 创建时间
     */
	@TableField("gmt_create")
	private Date gmtCreate;
    /**
     * 修改时间
     */
	@TableField("gmt_modified")
	private Date gmtModified;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OrderInfo{" +
			", id=" + id +
			", orderType=" + orderType +
			", orderPrice=" + orderPrice +
			", address=" + address +
			", orderStatus=" + orderStatus +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
