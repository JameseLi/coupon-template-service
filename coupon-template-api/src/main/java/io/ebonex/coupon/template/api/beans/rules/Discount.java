package io.ebonex.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lee
 * @date 2022/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount {

    /**
     * 对于满减券：quota减掉的是满减的金额，单位是分
     * 对于打折券：quota是折扣，100 表示原价，90表示9折，95表示9.5折
     * 对于随机满减券：quota是最高的满减金额
     * 对也晚间双倍券：quota是日间优惠额，晚间优惠翻倍
     */
    private Long quota;

    /**
     * 订单最低要达到多少钱才能使用使用优惠券，单位是分
     */
    private Long threshold;
}
