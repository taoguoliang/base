package cn.taoguoliang.base.spec.listener.impl;

import cn.taoguoliang.base.spec.anno.OrLikeOrLike;
import cn.taoguoliang.base.spec.exception.BuildSpecificationException;
import cn.taoguoliang.base.spec.listener.AbstractListener;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * OrLikeOrLikeSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description Or (a like xx or b like xx)
 * @date 2019年5月5日 下午4:33:32
 */
@Component
public class OrLikeOrLikeSpecificationListener extends AbstractListener {

    @SuppressWarnings("unchecked")
    @Override
    public Class<OrLikeOrLike> getAnnotation() {
        return OrLikeOrLike.class;
    }

    /**
     * @see AbstractListener#buildPredicate(CriteriaBuilder, From, String, Object, Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value, Object annotation) {
        OrLikeOrLike likeOrlike = (OrLikeOrLike) annotation;
        String[] fileds = likeOrlike.fileds();
        List<Object> values = (List<Object>) value;
        if (fileds.length != values.size()) {
            throw new BuildSpecificationException(
                    "对 " + name + " 使用LikeOrLike时，注解上fields长度和字段值的大小不同，fileds长为:" + fileds.length + ";字段值大小为：" + values.size());
        }
        List<Predicate> predicates = new ArrayList<>();
        for (int i = 0; i < fileds.length; i++) {
            predicates
                    .add(criteriaBuilder.like(from.get(fileds[i]),
                            "%" + values.get(i).toString().replace("%", "\\%") + "%"));
        }
        Predicate[] restrictions = new Predicate[predicates.size()];
        return criteriaBuilder.or(criteriaBuilder.or(predicates.toArray(restrictions)));
    }

}
