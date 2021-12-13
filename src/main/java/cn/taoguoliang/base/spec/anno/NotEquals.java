package cn.taoguoliang.base.spec.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NotEquals
 *
 * @author taogl
 * @version v1.0.0
 * @description 不相等条件
 * @date 2019年6月6日 下午5:29:07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotEquals {

    /**
     * Equels
     *
     * @return
     * @description 字段名
     * @author taogl
     * @date 2019年6月6日 下午5:29:07
     * @version v1.0.0
     */
    String value() default "";

}
