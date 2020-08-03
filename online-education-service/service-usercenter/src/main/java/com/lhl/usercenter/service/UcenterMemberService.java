package com.lhl.usercenter.service;

import com.lhl.usercenter.domain.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhl.usercenter.domain.vo.UcenterLoginInfoVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-23
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(UcenterMember member);

    UcenterLoginInfoVo getLoginInfo(String id);

    Integer selectByOpenId(String openid);

    UcenterMember getByOpenId(String openid);

    Integer countRegister(String date);
}
