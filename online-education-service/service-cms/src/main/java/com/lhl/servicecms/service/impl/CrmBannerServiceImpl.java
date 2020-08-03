package com.lhl.servicecms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhl.servicecms.domain.CrmBanner;
import com.lhl.servicecms.mapper.CrmBannerMapper;
import com.lhl.servicecms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-19
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public CrmBanner getBannerById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * @Cacheable
     * value : 缓存名，必填，它指定了你的缓存存放在哪块命名空间
     * key : 可选属性，可以使用 SpEL 标签自定义缓存的key
     * 根据方法对其返回结果进行缓存，
     * 下次请求时，如果缓存存在，
     * 则直接读取缓存数据返回；如果缓存不存在，
     * 则执行方法，并把返回的结果存入缓存中。
     * 一般用在查询方法上。
     *    记得key 是 "' xxx '"
     */
    @Cacheable(value = "banner",key = "'bannerList'")
    @Override
    public List<CrmBanner> getHotBanner() {
        QueryWrapper<CrmBanner> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //使用last方法在后面拼接个sql语句只显示两条数据
        wrapper.last("limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(wrapper);
        return crmBanners;
    }

    //执行更新,删除操作后将所有的banner缓存清空,这里由于业务需要添加后也要使缓存失效
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void saveBanner(CrmBanner banner) {
        baseMapper.insert(banner);
    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void updateByIdBanner(CrmBanner banner) {
        baseMapper.updateById(banner);
    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void removeByIdBanner(String id) {
        baseMapper.deleteById(id);
    }


}
