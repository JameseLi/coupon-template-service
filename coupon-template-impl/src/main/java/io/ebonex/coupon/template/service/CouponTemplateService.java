package io.ebonex.coupon.template.service;

import io.ebonex.coupon.template.api.beans.CouponTemplateInfo;
import io.ebonex.coupon.template.api.beans.PageCouponTemplateInfo;
import io.ebonex.coupon.template.api.beans.TemplateSearchParams;

import java.util.Collection;
import java.util.Map;

/**
 * @author Lee
 * @date 2022/7/1
 */
public interface CouponTemplateService {

    /**
     * 创建优惠券模板
     *
     * @param request 优惠券模版详情
     * @return CouponTemplateInfo
     */
    CouponTemplateInfo createTemplate(CouponTemplateInfo request);

    /**
     * 根据ID获取优惠券模板详情
     *
     * @param id 优惠券模板ID
     * @return CouponTemplateInfo
     */
    CouponTemplateInfo loadTemplateInfo(Long id);

    /**
     * 克隆优惠券
     *
     * @param templateId 优惠券模板ID
     * @return CouponTemplateInfo
     */
    CouponTemplateInfo cloneTemplate(Long templateId);

    /**
     * 获取优惠券模板分页列表
     *
     * @param request 优惠券模板查询详情
     * @return PageCouponTemplateInfo
     */
    PageCouponTemplateInfo search(TemplateSearchParams request);

    /**
     * 根据ID删除优惠券模板
     *
     * @param id 优惠券模板ID
     */
    void deleteTemplate(Long id);

    /**
     * 批量查询模板ID-模板详情映射
     *
     * @param ids 优惠券模板ID集合
     * @return Map<Long, CouponTemplateInfo>
     */
    Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids);
}
