package com.lhl.servicecms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhl.servicecms.domain.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-19
 */
public interface CrmBannerService extends IService<CrmBanner> {

    CrmBanner getBannerById(String id);

    List<CrmBanner> getHotBanner();

    void saveBanner(CrmBanner banner);

    void updateByIdBanner(CrmBanner banner);

    void removeByIdBanner(String id);
}
