package io.ebonex.coupon.template.dao.converter;

import com.google.gson.Gson;
import io.ebonex.coupon.template.api.beans.rules.TemplateRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Lee
 * @date 2022/7/1
 */
@Converter
public class RuleConverter implements AttributeConverter<TemplateRule, String> {
    @Override
    public String convertToDatabaseColumn(TemplateRule templateRule) {
        return new Gson().toJson(templateRule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return new Gson().fromJson(rule, TemplateRule.class);
    }
}
