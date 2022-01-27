package cn.linz.base.spec.listener.impl;

import cn.linz.base.spec.anno.OrNotEquals;
import cn.linz.base.spec.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * OrNotEqualsSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description 构造or a != xx监听器
 * @date 2019年3月27日 下午4:28:03
 */
@Component
@Slf4j
public class OrNotEqualsSpecificationListener extends AbstractListener {

    /** {@inheritDoc} */
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value,
            Object annotation) {
        return criteriaBuilder.or(criteriaBuilder.notEqual(from.get(name), value));
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Class<OrNotEquals> getAnnotation() {
        return OrNotEquals.class;
    }

}
