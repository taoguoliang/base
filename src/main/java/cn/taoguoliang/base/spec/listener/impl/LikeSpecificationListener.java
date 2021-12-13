package cn.taoguoliang.base.spec.listener.impl;

import cn.taoguoliang.base.spec.anno.Like;
import cn.taoguoliang.base.spec.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * EquelsSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description 构造相等条件监听器
 * @date 2019年3月27日 下午4:28:03
 */
@Component
@Slf4j
public class LikeSpecificationListener extends AbstractListener {

    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value, Object annotation) {
        String valueStr = value.toString();
        valueStr = valueStr.replace("%", "\\%");
        return criteriaBuilder.and(criteriaBuilder.like(from.get(name), "%" + valueStr + "%"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<Like> getAnnotation() {
        return Like.class;
    }

}
