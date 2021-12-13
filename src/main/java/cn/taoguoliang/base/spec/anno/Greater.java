package cn.taoguoliang.base.spec.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Greater
 *
 * @author taogl
 * @version v1.0.0
 * @description Greater条件
 * @date 2019年6月6日 下午5:27:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Greater {

    /**
     * Equels
     *
     * @return
     * @description 字段名
     * @author taogl
     * @date 2019年6月6日 下午5:27:50
     * @version v1.0.0
     */
    String value() default "";

}
