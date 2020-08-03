package com.lhl.servicecms.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lhl.commonUtils.CommonResult;
import com.lhl.servicecms.domain.CrmBanner;
import com.lhl.servicecms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-19
 * 前端的banner查询
 */
@RestController
@RequestMapping("/servicecms/banner-front")
//@CrossOrigin
public class FrontBannerController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner,id并进行排序,显示热点课程")
    @GetMapping("/getAllBanner")
    public CommonResult getAllBanner() {
        List<CrmBanner> list = bannerService.getHotBanner();
        return CommonResult.ok().data("bannerList", list);
    }
}

