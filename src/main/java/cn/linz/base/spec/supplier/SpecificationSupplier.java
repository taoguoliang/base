package cn.linz.base.spec.supplier;

import cn.hutool.core.util.ReflectUtil;
import cn.linz.base.spec.listener.SpecificationListener;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Predicate.BooleanOperator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SpecificationSupplier
 *
 * @author taogl
 * @version v1.0.0
 * @description 动态生成Specification
 * @date 2019年3月26日 下午5:15:10
 */
public final class SpecificationSupplier {

    private static final Map<Class<?>, SpecificationListener> LISTENER_MAP = new HashMap<>();

    private SpecificationSupplier() {

    }

    public static <T> Specification<T> buildSpecification(Object param) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = paramToPredicate(root, criteriaBuilder, param);
            Map<BooleanOperator, List<Predicate>> predicatesMap
                    = predicates.stream().collect(Collectors.groupingBy(Predicate::getOperator));
            List<Predicate> andPredicates = predicatesMap.get(BooleanOperator.AND);
            andPredicates = andPredicates == null ? Collections.emptyList() : andPredicates;
            List<Predicate> orPredicates = predicatesMap.get(BooleanOperator.OR);
            orPredicates = orPredicates == null ? Collections.emptyList() : orPredicates;
            return mergePredicate(criteriaBuilder, andPredicates, orPredicates);
        };
    }

    /**
     * SpecificationSupplier
     *
     * @param criteriaBuilder criteriaBuilder
     * @param andPredicates andPredicates
     * @param orPredicates orPredicates
     * @return Predicate
     * @description 将and条件和or条件进行合并
     * @author taogl
     * @date 2019年10月31日 下午1:58:36
     * @version v1.0.0
     */
    private static Predicate mergePredicate(CriteriaBuilder criteriaBuilder, List<Predicate> andPredicates,
            List<Predicate> orPredicates) {
        if (!CollectionUtils.isEmpty(andPredicates) && !CollectionUtils.isEmpty(orPredicates)) {
            // 若有and和or条件
            return criteriaBuilder.or(criteriaBuilder.and(andPredicates.toArray(new Predicate[0])),
                    criteriaBuilder.or(orPredicates.toArray(new Predicate[0])));
        } else if (!CollectionUtils.isEmpty(orPredicates)) {
            // 只有or条件
            return criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));
        } else if (!CollectionUtils.isEmpty(andPredicates)) {
            // 只有and条件
            return criteriaBuilder.and(andPredicates.toArray(new Predicate[0]));
        }
        // and和or均没有
        return null;
    }

    /**
     * @return the listeners
     */
    public static Map<Class<?>, SpecificationListener> getListeners() {
        return LISTENER_MAP;
    }

    public static synchronized void addListener(Class<?> cls, SpecificationListener listener) {
        SpecificationSupplier.LISTENER_MAP.put(cls, listener);
    }

    public static <Z, X> List<Predicate> paramToPredicate(From<Z, X> from, CriteriaBuilder criteriaBuilder,
            Object param) {
        List<Predicate> predicates = new ArrayList<>();
        Field[] fields = ReflectUtil.getFields(param.getClass());
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations == null) {
                continue;
            }
            for (Annotation annotation : annotations) {
                SpecificationListener specificationListener = LISTENER_MAP.get(annotation.annotationType());
                if (null == specificationListener) {
                    continue;
                }
                Predicate predicate = specificationListener.execute(param, field, criteriaBuilder, from);
                if (null != predicate) {
                    predicates.add(predicate);
                }
            }
        }
        return predicates;
    }

}
