package cn.linz.base.spec.listener.impl;

import cn.linz.base.spec.anno.OrLike;
import cn.linz.base.spec.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * OrLikeSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description 构造or a like xx条件监听器
 * @date 2019年3月27日 下午4:28:03
 */
@Component
@Slf4j
public class OrLikeSpecificationListener extends AbstractListener {

    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value,
            Object annotation) {
        String valueStr = value.toString().replace("%", "\\%");
        return criteriaBuilder.or(criteriaBuilder.like(from.get(name), "%" + valueStr + "%"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<OrLike> getAnnotation() {
        return OrLike.class;
    }

}