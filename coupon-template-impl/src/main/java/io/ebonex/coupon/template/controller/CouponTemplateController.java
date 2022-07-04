package io.ebonex.coupon.template.controller;

import com.google.gson.Gson;
import io.ebonex.coupon.template.api.beans.CouponTemplateInfo;
import io.ebonex.coupon.template.api.beans.PageCouponTemplateInfo;
import io.ebonex.coupon.template.api.beans.TemplateSearchParams;
import io.ebonex.coupon.template.service.CouponTemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Lee
 * @date 2022/7/2
 */
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("template")
public class CouponTemplateController {

    private final CouponTemplateService couponTemplateService;

    @PostMapping("addTemplate")
    public CouponTemplateInfo addTemplate(@Valid @RequestBody CouponTemplateInfo request) {
        log.info("Create coupon template: data={} ", request);
        return couponTemplateService.createTemplate(request);
    }

    @PostMapping("cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam("id") Long templateId) {
        log.info("Clone coupon template: data= {}", templateId);
        return couponTemplateService.cloneTemplate(templateId);
    }

    @GetMapping("getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam("id") Long id) {
        log.info("Get coupon template: data= {}", id);
        return couponTemplateService.loadTemplateInfo(id);
    }

    @GetMapping("getBatch")
    public Map<Long, CouponTemplateInfo> getTemplateBatch(@RequestParam("ids") Collection<Long> ids) {
        log.info("getTemplateBatch: {}", new Gson().toJson(ids));
        return couponTemplateService.getTemplateInfoMap(ids);
    }

    @PostMapping("search")
    public PageCouponTemplateInfo search(@Valid @RequestBody TemplateSearchParams request) {
        log.info("search templates: payload= {}", request);
        return couponTemplateService.search(request);
    }

    @DeleteMapping("deleteTemplate")
    public void deleteTemplate(@RequestParam("id") Long id) {
        log.info("Delete template: data={}", id);
        couponTemplateService.deleteTemplate(id);
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4);
        System.out.println(new Gson().toJson(list));
    }
}
