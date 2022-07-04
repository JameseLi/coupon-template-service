package io.ebonex.coupon.template.converter;

import io.ebonex.coupon.template.api.beans.CouponTemplateInfo;
import io.ebonex.coupon.template.dao.entity.CouponTemplate;

/**
 * @author Lee
 * @date 2022/7/1
 */
public class CouponTemplateConverter {

    public static CouponTemplateInfo convertToTemplateInfo(CouponTemplate template) {
        return CouponTemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .desc(template.getDescription())
                .type(template.getCategory().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }
}
