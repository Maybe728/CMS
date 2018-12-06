package com.cn.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统权限表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
    /**
     * 是否可用
     */
	private Integer available;
    /**
     * 权限名称
     */
	private String permissionName;
    /**
     * 父权限ID
     */
	private Integer parentId;
    /**
     * 父权限ID列表
     */
	private String parentIds;
    /**
     * 权限编码
     */
	private String permissionCode;
    /**
     * 资源类型(menu/button)
     */
	private String resourceType;
    /**
     * 资源路径
     */
	private String url;
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

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		return "Permission{" +
			", id=" + id +
			", available=" + available +
			", permissionName=" + permissionName +
			", parentId=" + parentId +
			", parentIds=" + parentIds +
			", permissionCode=" + permissionCode +
			", resourceType=" + resourceType +
			", url=" + url +
			", updateTime=" + updateTime +
			", createTime=" + createTime +
			"}";
	}
}
