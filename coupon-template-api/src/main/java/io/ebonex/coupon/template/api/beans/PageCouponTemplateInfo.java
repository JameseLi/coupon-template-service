package io.ebonex.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Lee
 * @date 2022/7/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageCouponTemplateInfo {

    private List<CouponTemplateInfo> templates;

    private int page;

    private long total;
}
