package cn.linz.base.spec.listener.impl;

import cn.linz.base.spec.anno.NotIn;
import cn.linz.base.spec.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Collection;

/**
 * EquelsSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description 构造NotIn条件监听器
 * @date 2019年3月27日 下午4:28:03
 */
@Component
@Slf4j
public class NotInSpecificationListener extends AbstractListener {

    /** {@inheritDoc} */
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value,
            Object annotation) {
        Path<Object> path = from.get(name);
        CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
        if (value instanceof Collection) {
            Collection<?> statusList = (Collection<?>) value;
            if (((Collection<?>) value).isEmpty()) {
                in.value("NOT IN ()");
            } else {
                statusList.stream().forEach(in::value);
            }
        } else {
            in.value(value);
        }
        return criteriaBuilder.not(in);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Class<NotIn> getAnnotation() {
        return NotIn.class;
    }

}
