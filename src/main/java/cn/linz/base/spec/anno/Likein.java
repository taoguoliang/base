package cn.linz.base.spec.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Likein
 *
 * @author taogl
 * @version v1.0.0
 * @description like查询数组
 * @date 2019年9月16日 下午5:05:34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Likein {

    /**
     * Likein
     *
     * @description 字段名
     * @author taogl
     * @date 2019年9月16日 下午5:09:01
     * @version v1.0.0
     */
    String value() default "";

}
