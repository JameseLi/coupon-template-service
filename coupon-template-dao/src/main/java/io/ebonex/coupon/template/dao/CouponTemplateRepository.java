package io.ebonex.coupon.template.dao;

import io.ebonex.coupon.template.dao.entity.CouponTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Lee
 * @date 2022/7/1
 */
public interface CouponTemplateRepository extends JpaRepository<CouponTemplate, Long> {

    /**
     * 根据门店ID 查询优惠券列表
     *
     * @param shopId 门店ID
     * @return List<CouponTemplate>
     */
    List<CouponTemplate> findAllByShopId(Long shopId);

    /**
     * 根据优惠券ID 集合 查询优惠券分页列表
     *
     * @param id       优惠券ID 集合
     * @param pageable 分页详情
     * @return Page<CouponTemplate>
     */
    Page<CouponTemplate> findAllByIdIn(List<Long> id, Pageable pageable);

    /**
     * 统计门店可用/不可用的优惠券个数
     *
     * @param shopId    门店ID
     * @param available 是否可用
     * @return Integer
     */
    Integer countByShopIdAndAvailable(Long shopId, Boolean available);

    /**
     * 根据ID使得优惠券不可用
     *
     * @param id 优惠券ID
     * @return int
     */
    @Modifying
    @Query("update CouponTemplate c set c.available = 0 where c.id= :id")
    int makeCouponUnavailable(@Param("id") Long id);
}
