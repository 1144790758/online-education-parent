package com.lhl.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhl.authority.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("p.id,p.pid,p.name,p.type,p.permission_value,path,p.component,p.icon,p.status,p.is_deleted,p.gmt_create,p.gmt_modified\n" +
            " from acl_user_role ur\n" +
            " inner join acl_role_permission rp on rp.role_id = ur.role_id\n" +
            " inner join acl_permission p on p.id = rp.permission_id\n" +
            " where ur.user_id = #{userId}\n" +
            " and ur.is_deleted = 0\n" +
            " and rp.is_deleted = 0\n" +
            " and p.is_deleted = 0")
    List<String> selectPermissionValueByUserId(String id);

    @Select("select\n" +
            "        permission_value\n" +
            "        from acl_permission\n" +
            "        where type = 2\n" +
            "        and is_deleted = 0")
    List<String> selectAllPermissionValue();

    @Select("select\n" +
            "        p.permission_value\n" +
            "        from acl_user_role ur\n" +
            "        inner join acl_role_permission rp on rp.role_id = ur.role_id\n" +
            "        inner join acl_permission p on p.id = rp.permission_id\n" +
            "        where ur.user_id = #{userId}\n" +
            "        and p.type = 2\n" +
            "        and ur.is_deleted = 0\n" +
            "        and rp.is_deleted = 0\n" +
            "        and p.is_deleted = 0")
    List<Permission> selectPermissionByUserId(String userId);
}
