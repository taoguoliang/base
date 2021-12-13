package cn.taoguoliang.base.spec.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * OrNotIn
 *
 * @author taogl
 * @version v1.0.0
 * @description OrNotIn条件
 * @date 2019年3月27日 下午4:25:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrNotIn {

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

}
