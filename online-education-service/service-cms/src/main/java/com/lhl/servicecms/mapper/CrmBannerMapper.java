package com.lhl.servicecms.mapper;

import com.lhl.servicecms.domain.CrmBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 首页banner表 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-07-19
 */
@Mapper
@Repository
public interface CrmBannerMapper extends BaseMapper<CrmBanner> {

}
