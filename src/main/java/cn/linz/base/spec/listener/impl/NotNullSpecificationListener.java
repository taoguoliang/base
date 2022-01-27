package cn.linz.base.spec.listener.impl;

import cn.linz.base.spec.anno.NotNull;
import cn.linz.base.spec.listener.AbstractListener;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * OrNullSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description Or xxx is null
 * @date 2019年5月5日 下午4:33:32
 */
@Component
public class NotNullSpecificationListener extends AbstractListener {

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Class<NotNull> getAnnotation() {
        return NotNull.class;
    }

    /** {@inheritDoc} */
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value,
            Object annotation) {
        return criteriaBuilder.and(criteriaBuilder.isNotNull(from.get((String) value)));
    }

}
