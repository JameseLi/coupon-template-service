package io.ebonex.coupon.template.dao.entity;

import io.ebonex.coupon.template.api.beans.rules.TemplateRule;
import io.ebonex.coupon.template.api.enums.CouponType;
import io.ebonex.coupon.template.dao.converter.CouponTypeConverter;
import io.ebonex.coupon.template.dao.converter.RuleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lee
 * @date 2022/7/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
public class CouponTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "shopId")
    private Long shopId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType category;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "rule", nullable = false)
    @Convert(converter = RuleConverter.class)
    private TemplateRule rule;
}
