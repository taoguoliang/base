package cn.taoguoliang.base.spec.listener.impl;

import cn.taoguoliang.base.spec.anno.OrNotNull;
import cn.taoguoliang.base.spec.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * OrNullSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description Or xxx is not null
 * @date 2019年5月5日 下午4:33:32
 */
@Component
@Slf4j
public class OrNotNullSpecificationListener extends AbstractListener {

    @SuppressWarnings("unchecked")
    @Override
    public Class<OrNotNull> getAnnotation() {
        return OrNotNull.class;
    }

    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value,
            Object annotation) {
        return criteriaBuilder.or(criteriaBuilder.isNotNull(from.get((String) value)));
    }

}
