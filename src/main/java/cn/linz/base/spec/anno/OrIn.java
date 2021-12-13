package cn.linz.base.spec.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * In
 *
 * @author taogl
 * @version v1.0.0
 * @description In条件
 * @date 2019年3月27日 下午4:25:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrIn {

    /**
     * Equels
     *
     * @return
     * @description 字段名
     * @author taogl
     * @date 2019年3月27日 下午4:27:02
     * @version v1.0.0
     */
    String value() default "";

    /**
     * In
     *
     * @return
     * @description 是否允许为null
     * @author taogl
     * @date 2019年5月5日 下午5:19:39
     * @version v1.0.0
     */
    boolean allowNull() default false;

    Class<?> type() default String.class;

}
