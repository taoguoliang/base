package cn.linz.base.spec.listener.impl;

import cn.linz.base.spec.anno.Likein;
import cn.linz.base.spec.listener.AbstractListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * LikeinSpecificationListener
 *
 * @author taogl
 * @version v2.0.0
 * @description like查询数组监听器
 * @date 2019年9月16日 下午5:45:00
 */
@Component
public class LikeinSpecificationListener extends AbstractListener {

    @SuppressWarnings("unchecked")
    @Override
    public Class<Likein> getAnnotation() {
        return Likein.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value, Object annotation) {
        Predicate p = null;
        List<Object> vs = (List<Object>) value;
        if (!CollectionUtils.isEmpty(vs)) {
            for (int i = 0; i < vs.size(); i++) {
                Object v = vs.get(i);
                String likeStr = "%" + String.valueOf(v).replace("%", "\\%") +
                        "%";
                if (i == 0) {
                    p = criteriaBuilder.like(from.get(name), likeStr);
                } else {
                    p = criteriaBuilder.or(criteriaBuilder.like(from.get(name), likeStr), p);
                }
            }
        }
        return criteriaBuilder.and(p);
    }

}
