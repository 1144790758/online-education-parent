package com.lhl.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.commonUtils.CustomException;
import com.lhl.commonUtils.MD5;
import com.lhl.servicebase.JwtUtils;
import com.lhl.servicebase.RedisUtil;
import com.lhl.usercenter.domain.UcenterMember;
import com.lhl.usercenter.domain.vo.UcenterLoginInfoVo;
import com.lhl.usercenter.mapper.UcenterMemberMapper;
import com.lhl.usercenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-23
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

//    @Autowired
//    private RedisUtil redisUtil;

    @Autowired
    private UcenterMemberMapper ucenterMemberMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();

        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ) {
            throw new CustomException(20001,"error");
        }

        //获取会员
        UcenterMember q_member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(null == q_member) {
            throw new CustomException(20001,"没有该账号");
        }

        //校验密码
        if(!MD5.encrypt(password).equals(q_member.getPassword())) {
            throw new CustomException(20001,"密码错误");
        }

        //校验是否被禁用
        if(q_member.getIsDisabled()) {
            throw new CustomException(20001,"账号不可用");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(q_member.getId(), q_member.getNickname());
        return token;

    }

    @Override
    public void register(UcenterMember member) {
        //首先校验member对象
        String nickname = member.getNickname();
        String mobile = member.getMobile();
        String password = member.getPassword();
        String code = member.getCode();
        String avatar=member.getAvatar();

//        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new CustomException(20001,"请将信息填写完整");
        }

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(count > 0) {
            throw new CustomException(20001,"手机以注册过账号");
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String redis_code = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redis_code)) {
            throw new CustomException(20001,"验证码错误");
        }

        //添加注册信息到数据库
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setNickname(nickname);
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar(avatar);
        baseMapper.insert(ucenterMember);

    }

    @Override
    public UcenterLoginInfoVo getLoginInfo(String id) {
        UcenterMember member = baseMapper.selectById(id);
        UcenterLoginInfoVo ucenterLoginInfoVo = new UcenterLoginInfoVo();
        BeanUtils.copyProperties(member,ucenterLoginInfoVo);
        return ucenterLoginInfoVo;
    }

    @Override
    public Integer selectByOpenId(String openid) {
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("openid",openid);
        Integer count = baseMapper.selectCount(wrapper);
        return count;
    }

    @Override
    public UcenterMember getByOpenId(String openid) {
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        return ucenterMember;
    }

    //统计日期注册人数
    @Override
    public Integer countRegister(String date) {
        Integer countRegisterByDate = ucenterMemberMapper.countRegisterByDate(date);
        return countRegisterByDate;
    }
}
