package cn.taoguoliang.base.spec.listener.impl;

import cn.taoguoliang.base.spec.anno.OrLesser;
import cn.taoguoliang.base.spec.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * OrLesserSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description 构造小于等于条件监听器
 * @date 2019年3月27日 下午4:28:03
 */
@Component
@Slf4j
public class OrLesserSpecificationListener extends AbstractListener {

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value,
            Object annotation) {
        return criteriaBuilder.or(criteriaBuilder.lessThan(from.get(name), (Comparable) value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<OrLesser> getAnnotation() {
        return OrLesser.class;
    }

}
