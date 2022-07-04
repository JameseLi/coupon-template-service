package io.ebonex.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lee
 * @date 2022/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {

    /**
     * 优惠券可以享受的折扣
     */
    private Discount discount;

    /**
     * 每个用户最多可领数量
     */
    private Integer limitation;

    /**
     * 过期时间
     */
    private Long deadline;
}
