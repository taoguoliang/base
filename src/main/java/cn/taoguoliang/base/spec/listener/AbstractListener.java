package cn.taoguoliang.base.spec.listener;

import cn.taoguoliang.base.spec.exception.BuildSpecificationException;
import cn.taoguoliang.base.spec.supplier.SpecificationSupplier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * AbstractListener
 *
 * @author taogl
 * @version v1.2.0
 * @description 抽象监听器逻辑
 * @date 2019年3月27日 下午5:10:48
 */
public abstract class AbstractListener implements SpecificationListener {

    public AbstractListener() {
        SpecificationSupplier.addListener(this.getAnnotation(), this);
    }

    @Override
    public <Z, X> Predicate execute(Object param, Field field, CriteriaBuilder criteriaBuilder, From<Z, X> from) {
        Annotation annotation = field.getAnnotation(getAnnotation());
        if (annotation == null) {
            return null;
        }
        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(param.getClass(), field.getName());
        Method readMethod = descriptor.getReadMethod();
        Object value = null;
        String name = null;
        try {
            name = (String) getAnnotation().getMethod("value").invoke(annotation);
            name = StringUtils.isBlank(name) ? field.getName() : name;
            value = readMethod.invoke(param);
        } catch (ReflectiveOperationException e) {
            throw new BuildSpecificationException("构建" + getAnnotation().getName() + "条件时，反射回去属性值异常", e);
        }
        if (null == value) {
            return null;
        }
        if (field.getType() == String.class) {
            if (!StringUtils.isBlank(value.toString())) {
                return buildPredicate(criteriaBuilder, from, name, value, annotation);
            }
        } else {
            return buildPredicate(criteriaBuilder, from, name, value, annotation);
        }
        return null;
    }

    /**
     * AbstractListener
     *
     * @return
     * @description 获取相对应的注解类
     * @author taogl
     * @date 2019年3月27日 下午5:14:40
     * @version v1.0.0
     */
    public abstract <T> Class<T> getAnnotation();

    /**
     * AbstractListener
     *
     * @param criteriaBuilder
     * @param from {@link From}
     * @param name 实体类的属性名
     * @param value 对应属性的值
     * @param annotation 当前字段使用的注解
     * @return {@link Predicate}
     * @description 提供条件的构建逻辑
     * @author taogl
     * @date 2019年3月27日 下午5:15:59
     * @version v1.2.0
     */
    protected abstract <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String name,
            Object value, Object annotation);

}
