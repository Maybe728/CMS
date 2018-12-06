package com.cn.cms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.cms.dto.ResultInfo;
import com.cn.cms.entity.Role;
import com.cn.cms.entity.User;
import com.cn.cms.service.IRoleService;
import com.cn.cms.service.IUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-10
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/*")
    public void toHtml() {

    }

    @RequestMapping("/selectListData")
    @ResponseBody
    public ResultInfo<List<Role>> selectListData(Role role){
        List<Role> list = iRoleService.selectList(new EntityWrapper<>(role));
        return new ResultInfo<>(list);
    }

    @RequestMapping("/listData")
    @RequiresPermissions("role:view")
    public @ResponseBody
    ResultInfo<List<Role>> listData(Role role, Integer page, Integer limit) {
        EntityWrapper<Role> wrapper = new EntityWrapper<>(role);
        if (role != null && role.getRoleCode() != null) {
            wrapper.like("role_code", role.getRoleCode());
            role.setRoleCode(null);
        }
        if (role != null && role.getRoleName() != null) {
            wrapper.like("role_name", role.getRoleName());
            role.setRoleName(null);
        }
        Page<Role> pageObj = iRoleService.selectPage(new Page<>(page, limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/save")
    @RequiresPermissions(value = {"role:add", "role:edit"}, logical = Logical.OR)
    public @ResponseBody
    ResultInfo<Boolean> save(Role role) {
        return new ResultInfo<>(iRoleService.saveRole(role));
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("role:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.in("role_id", idArr);
        List<User> userList = iUserService.selectList(wrapper);
        if(userList!=null && userList.size()>0){
            return new ResultInfo<>("用户拥有角色不能删除！");
        }
        return new ResultInfo<>(iRoleService.deleteBatchIds(Arrays.asList(idArr)));
    }

}
