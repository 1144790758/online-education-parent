package com.lhl.servicecms.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhl.commonUtils.CommonResult;
import com.lhl.servicecms.domain.CrmBanner;
import com.lhl.servicecms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-19
 * 后端的banner crud
 */
@RestController
@RequestMapping("/servicecms/banner-admin")
//@CrossOrigin
public class AdminBannerController {

    @Autowired
    CrmBannerService bannerService;

    //分页查询banner
    @GetMapping("/bannerPage/{page}/{limt}")
    public CommonResult bannerPage(@PathVariable Integer page,@PathVariable Integer limit){
        Page<CrmBanner> bannerPage=new Page<>(page,limit);
        bannerService.page(bannerPage,null);
        List<CrmBanner> items = bannerPage.getRecords();
        long total = bannerPage.getTotal();
        return CommonResult.ok().data("items",items).data("total",total);
    }

    //添加banner
    @PostMapping("/bannerAdd")
    public CommonResult bannerAdd(@RequestBody CrmBanner banner){
        bannerService.saveBanner(banner);
        return CommonResult.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("/getBannerById/{id}")
    public CommonResult getBannerById(@PathVariable String id) {
        CrmBanner banner = bannerService.getBannerById(id);
        return CommonResult.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("/updateBanner")
    public CommonResult updateById(@RequestBody CrmBanner banner) {
        bannerService.updateByIdBanner(banner);
        return CommonResult.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("/removeBanner/{id}")
    public CommonResult removeBanner(@PathVariable String id) {
        bannerService.removeByIdBanner(id);
        return CommonResult.ok();
    }
}

