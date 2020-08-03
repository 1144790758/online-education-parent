package com.lhl.authority.controller;


import com.lhl.authority.entity.Permission;
import com.lhl.authority.service.PermissionService;
import com.lhl.commonUtils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限 菜单管理
 *
 */
@RestController
@RequestMapping("/admin/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "递归查询所有菜单")
    @GetMapping("/get_all_permission_index")
    public CommonResult indexAllPermission() {
        List<Permission> list =  permissionService.indexAllPermission();
        return CommonResult.ok().data("list",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable String id) {
        permissionService.removeChildById_digui(id);
        return CommonResult.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public CommonResult doAssign(String roleId,String[] permissionId) {
        permissionService.assign_permissions(roleId,permissionId);
        return CommonResult.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public CommonResult toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return CommonResult.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public CommonResult save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return CommonResult.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public CommonResult updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return CommonResult.ok();
    }

}

