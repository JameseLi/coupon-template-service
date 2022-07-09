package io.ebonex.coupon.template.service.impl;

import io.ebonex.coupon.template.api.beans.CouponTemplateInfo;
import io.ebonex.coupon.template.api.beans.PageCouponTemplateInfo;
import io.ebonex.coupon.template.api.beans.TemplateSearchParams;
import io.ebonex.coupon.template.api.enums.CouponType;
import io.ebonex.coupon.template.converter.CouponTemplateConverter;
import io.ebonex.coupon.template.dao.CouponTemplateRepository;
import io.ebonex.coupon.template.dao.entity.CouponTemplate;
import io.ebonex.coupon.template.service.CouponTemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Lee
 * @date 2022/7/1
 */
@Slf4j
@AllArgsConstructor
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private final CouponTemplateRepository couponTemplateRepository;

    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        // 单个门店最多创建 100 张优惠券模板
        if (request.getShopId() != null) {
            Integer count = couponTemplateRepository.countByShopIdAndAvailable(request.getShopId(), true);
            if (count >= 100) {
                log.error("the totals of coupon template exceeds maximum number");
                throw new UnsupportedOperationException("exceeded the maximum number of coupon templates that you can create");
            }
        }
        CouponTemplate template = CouponTemplate.builder()
                .shopId(request.getShopId())
                .name(request.getName())
                .category(CouponType.convert(request.getType()))
                .available(request.getAvailable())
                .build();
        template = couponTemplateRepository.save(template);
        return CouponTemplateConverter.convertToTemplateInfo(template);
    }

    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        Optional<CouponTemplate> template = couponTemplateRepository.findById(id);
        return template.map(CouponTemplateConverter::convertToTemplateInfo).orElse(null);
    }

    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        log.info("clone template id {}", templateId);
        CouponTemplate source = couponTemplateRepository.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("invalid template id"));
        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source, target);

        target.setAvailable(true);
        target.setId(null);
        target = couponTemplateRepository.save(target);
        return CouponTemplateConverter.convertToTemplateInfo(target);
    }

    @Override
    public PageCouponTemplateInfo search(TemplateSearchParams request) {
        CouponTemplate example = CouponTemplate.builder()
                .shopId(request.getShopId())
                .category(CouponType.convert(request.getType()))
                .name(request.getName())
                .available(request.getAvailable())
                .build();
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());

        Page<CouponTemplate> templatePage = couponTemplateRepository.findAll(Example.of(example), pageable);
        List<CouponTemplateInfo> couponTemplateInfos = templatePage.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toList());

        return PageCouponTemplateInfo.builder()
                .templates(couponTemplateInfos)
                .page(request.getPage())
                .total(templatePage.getTotalElements())
                .build();
    }

    @Override
    public void deleteTemplate(Long id) {
        int row = couponTemplateRepository.makeCouponUnavailable(id);
        if (row == 0) {
            throw new IllegalArgumentException("Template Not Found: " + id);
        }
    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        List<CouponTemplate> templates = couponTemplateRepository.findAllById(ids);
        return templates.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }
}
