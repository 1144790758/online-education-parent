package com.lhl.usercenter.mapper;

import com.lhl.usercenter.domain.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-07-23
 */
@Repository
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    //统计日期注册人数
    @Select("select count(1) from `ucenter_member` where DATE(gmt_create)=#{date} ")
    Integer countRegisterByDate(String date);
}
