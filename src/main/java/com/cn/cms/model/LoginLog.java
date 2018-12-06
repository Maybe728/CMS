package com.cn.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统登录日志表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-01
 */
public class LoginLog implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
    /**
     * 用户ID
     */
	private Integer userId;
    /**
     * 用户名
     */
	private String userName;
    /**
     * IP地址
     */
	private String ipAddress;
    /**
     * 地理位置
     */
	private String geographyLocation;
    /**
     * 修改时间
     */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
    /**
     * 创建时间
     */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getGeographyLocation() {
		return geographyLocation;
	}

	public void setGeographyLocation(String geographyLocation) {
		this.geographyLocation = geographyLocation;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "LoginLog{" +
			", id=" + id +
			", userId=" + userId +
			", userName=" + userName +
			", ipAddress=" + ipAddress +
			", geographyLocation=" + geographyLocation +
			", updateTime=" + updateTime +
			", createTime=" + createTime +
			"}";
	}
}
