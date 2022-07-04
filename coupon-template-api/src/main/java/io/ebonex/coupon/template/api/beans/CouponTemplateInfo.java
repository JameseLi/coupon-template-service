package io.ebonex.coupon.template.api.beans;

import io.ebonex.coupon.template.api.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Lee
 * @date 2022/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String desc;

    @NotNull
    private String type;

    private Long shopId;

    @NotNull
    private TemplateRule rule;

    private Boolean available;
}
