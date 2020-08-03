package com.lhl.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhl.authority.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
