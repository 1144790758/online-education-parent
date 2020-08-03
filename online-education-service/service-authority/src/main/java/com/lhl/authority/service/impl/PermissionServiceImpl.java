package com.lhl.authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhl.authority.entity.Permission;
import com.lhl.authority.entity.RolePermission;
import com.lhl.authority.entity.User;
import com.lhl.authority.helper.MemuHelper;
import com.lhl.authority.helper.PermissionHelper;
import com.lhl.authority.mapper.PermissionMapper;
import com.lhl.authority.service.PermissionService;
import com.lhl.authority.service.RolePermissionService;
import com.lhl.authority.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;
    
    @Autowired
    private UserService userService;
    

    //根据角色获取菜单
    @Override
    public List<Permission> selectAllMenu(String roleId) {
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));

        //根据角色id获取角色权限
        List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id",roleId));
        //转换给角色id与角色权限对应Map对象
//        List<String> permissionIdList = rolePermissionList.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());
//        allPermissionList.forEach(permission -> {
//            if(permissionIdList.contains(permission.getId())) {
//                permission.setSelect(true);
//            } else {
//                permission.setSelect(false);
//            }
//        });
        for (int i = 0; i < allPermissionList.size(); i++) {
            Permission permission = allPermissionList.get(i);
            for (int m = 0; m < rolePermissionList.size(); m++) {
                RolePermission rolePermission = rolePermissionList.get(m);
                if(rolePermission.getPermissionId().equals(permission.getId())) {
                    permission.setSelect(true);
                }
            }
        }
        List<Permission> permissionList = bulid(allPermissionList);
        return permissionList;
    }



    //根据用户id获取用户菜单
    @Override
    public List<String> selectPermissionValueByUserId(String id) {

        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<Permission> selectPermissionList = null;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
        }

        List<Permission> permissionList = PermissionHelper.bulid(selectPermissionList);
        List<JSONObject> result = MemuHelper.bulid(permissionList);
        return result;
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);

        if(null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }

    /**
     *	递归获取子节点
     * @param id
     * @param idList
     */
    private void selectChildListById(String id, List<String> idList) {
        List<Permission> childList = baseMapper.selectList(new QueryWrapper<Permission>().eq("pid", id).select("id"));
        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    private static List<Permission> bulid(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private static Permission findChildren(Permission treeNode,List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<Permission>());

        for (Permission it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }


    //========================递归查询所有菜单================================================
    //获取全部菜单
    @Override
    public List<Permission> indexAllPermission() {
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        wrapper.eq("pid","1");
        List<Permission> list = baseMapper.selectList(wrapper);
        List<Permission> permissionList = the_select(list,2);
        return permissionList;
    }
    private List<Permission> the_select(List<Permission> list,Integer level){
        if (list==null){
            return null;
        }
        //循环完了之后,一个层级的就在一起了,所以把他们添加为一个数组
        List<Permission>level_list= new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            QueryWrapper<Permission> wrapper=new QueryWrapper<>();
            wrapper.eq("pid",list.get(i).getId());
            List<Permission> selectList = baseMapper.selectList(wrapper);
            //把它的子节点查为一个列表
            List<Permission> sub_select = the_select(selectList,level+1);
            Permission permission = list.get(i);
            permission.setLevel(level);
            permission.setChildren(sub_select);
            level_list.add(permission);
        }
        return level_list;
    }


    //============递归删除菜单==================================
    @Override
    public void removeChildById_digui(String id) {
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        wrapper.eq("pid",id);
        List<Permission> deleteList = baseMapper.selectList(wrapper);
        remove_child_by_id(deleteList);
        //儿子删除了,老子还没有删除,下面删除老子
        baseMapper.deleteById(id);
    }



    //传入一个list这个list就是我们要删除的对象的子节点
    private void remove_child_by_id(List<Permission> list){
        if (list==null){
            return ;
        }
        for (int i = 0; i < list.size(); i++) {
            QueryWrapper<Permission> wrapper=new QueryWrapper<>();
            wrapper.eq("pid",list.get(i).getId());
            List<Permission> deleteList = baseMapper.selectList(wrapper);
            //把它的子节点删了
            remove_child_by_id(deleteList);
            baseMapper.deleteById(list.get(i).getId());
        }
    }


    //=========================给角色分配菜单=======================
    @Override
    public void assign_permissions(String roleId, String[] permissionId) {
        List<RolePermission> list=new ArrayList<>();
        for (int i = 0; i < permissionId.length; i++) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId[i]);
            list.add(rolePermission);
        }
        rolePermissionService.saveBatch(list);
    }
}
