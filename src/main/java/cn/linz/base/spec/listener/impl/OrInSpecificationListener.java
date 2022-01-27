package cn.linz.base.spec.listener.impl;

import cn.linz.base.spec.anno.OrIn;
import cn.linz.base.spec.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * EquelsSpecificationListener
 *
 * @author taogl
 * @version v1.0.0
 * @description 构造相等条件监听器
 * @date 2019年3月27日 下午4:28:03
 */
@Component
@Slf4j
public class OrInSpecificationListener extends AbstractListener {

    private static final Integer INTEGER = -9999;

    private static final float FLOAT = -9999.0f;

    private static final String STRING = "-9999";

    /** {@inheritDoc} */
    @Override
    protected <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value,
            Object annotation) {
        Path<Object> path = from.get(name);
        boolean allowNull = getAllowNull(annotation);
        CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
        if (value instanceof Collection) {
            Collection<?> statusList = (Collection<?>) value;
            if (((Collection<?>) value).isEmpty()) {
                Class<?> type = getType(annotation);
                dealEmptyCollection(in, type);
            } else {
                statusList.forEach(in::value);
            }
        } else {
            in.value(value);
        }
        return criteriaBuilder.or(allowNull ? criteriaBuilder.or(in, criteriaBuilder.isNull(path)) : in);
    }

    private void dealEmptyCollection(CriteriaBuilder.In<Object> in, Type type) {
        if (type.equals(String.class)) {
            in.value(STRING);
        } else if (Integer.class.equals(type) || Long.class.equals(type)) {
            in.value(INTEGER);
        } else if (Float.class.equals(type) || Double.class.equals(type)) {
            in.value(FLOAT);
        }
    }

    private Class<?> getType(Object annotation) {
        try {
            return (Class<?>) getAnnotation().getMethod("type").invoke(annotation);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e1) {
            log.error("获取@In中allowNull时失败", e1);
        }
        return String.class;
    }

    private boolean getAllowNull(Object annotation) {
        try {
            return (boolean) getAnnotation().getMethod("allowNull").invoke(annotation);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e1) {
            log.error("获取@In中allowNull时失败", e1);
        }
        return false;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Class<OrIn> getAnnotation() {
        return OrIn.class;
    }

}
