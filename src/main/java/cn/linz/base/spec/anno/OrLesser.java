package cn.linz.base.spec.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * OrLesser
 *
 * @author taogl
 * @version v1.0.0
 * @description OrLesser条件
 * @date 2019年6月6日 下午5:28:26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrLesser {

    /**
     * Equels
     *
     * @return
     * @description 字段名
     * @author taogl
     * @date 2019年6月6日 下午5:28:26
     * @version v1.0.0
     */
    String value() default "";

}
