package io.ebonex.coupon.template.dao.converter;

import io.ebonex.coupon.template.api.enums.CouponType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Lee
 * @date 2022/7/1
 */
@Converter
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {
    @Override
    public String convertToDatabaseColumn(CouponType couponType) {
        return couponType.getCode();
    }

    @Override
    public CouponType convertToEntityAttribute(String code) {
        return CouponType.convert(code);
    }
}
