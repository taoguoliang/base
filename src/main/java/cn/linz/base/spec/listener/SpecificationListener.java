package cn.linz.base.spec.listener;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;

/**
 * SpecificationFilter
 *
 * @author taogl
 * @version v1.2.0
 * @description Sql构造监听器
 * @date 2019年3月26日 下午6:00:35
 */
public interface SpecificationListener {

    /**
     * SpecificationListener
     *
     * @param param 用来构建条件的参数实体
     * @param field 查询对象的当前字段
     * @param criteriaBuilder a {@link javax.persistence.criteria.CriteriaBuilder} object
     * @param root a {@link javax.persistence.criteria.From} object
     * @description 执行监听器逻辑
     * @author taogl
     * @date 2019年3月27日 下午4:16:48
     * @version v1.2.0
     * @param <Z> a Z class
     * @param <X> a X class
     * @return a {@link javax.persistence.criteria.Predicate} object
     */
    <Z, X> Predicate execute(Object param, Field field, CriteriaBuilder criteriaBuilder, From<Z, X> root);

}
