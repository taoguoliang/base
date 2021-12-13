package cn.linz.base.spec.listener.impl;

import cn.linz.base.spec.anno.Join;
import cn.linz.base.spec.exception.BuildSpecificationException;
import cn.linz.base.spec.listener.AbstractListener;
import cn.linz.base.spec.supplier.SpecificationSupplier;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * JoinSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description Join条件监听
 * @date 2019年6月4日 下午3:14:04
 */
@Component
public class JoinSpecificationListener extends AbstractListener {

    @SuppressWarnings("unchecked")
    @Override
    public Class<Join> getAnnotation() {
        return Join.class;
    }

    /**
     * @see AbstractListener#buildPredicate(CriteriaBuilder, From, String, Object, Object)
     */
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value, Object annotation) {
        if (!(annotation instanceof Join)) {
            throw new BuildSpecificationException("构建Join时," + getClass().getName() + ".getAnnotation()获取的值" + getAnnotation().getName() + "与字段使用的注解值" + annotation.getClass().getName() + "不同");
        }
        Join joinAnnotation = (Join) annotation;
        javax.persistence.criteria.Join<X, ?> subJoin = createJoin(from, name, joinAnnotation.joinType(),
                joinAnnotation.targetClass());
        List<Predicate> predicates = SpecificationSupplier.paramToPredicate(subJoin, criteriaBuilder, value);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private <Z, X, C> javax.persistence.criteria.Join<X, C> createJoin(From<Z, X> from, String name,
            JoinType joinType, Class<C> valueClass) {
        return from.join(name, joinType);
    }

}
