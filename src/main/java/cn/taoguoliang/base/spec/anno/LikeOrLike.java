package cn.taoguoliang.base.spec.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NotIn
 *
 * @author taogl
 * @version v1.0.0
 * @description LikeOrLike条件 ( a like 'xxx' or b like 'xxx'),相应字段的值须要是List。
 * @date 2019年3月27日 下午4:25:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LikeOrLike {

    /**
     * Equels
     *
     * @return
     * @description 该注解中该方法无用
     * @author taogl
     * @date 2019年3月27日 下午4:27:02
     * @version v1.0.0
     * @deprecated LikeOrLike条件中通过{@link fileds}获取列名，value方法将会无效。
     */
    @Deprecated
    String value() default "";

    /**
     * LikeOrLike
     *
     * @return
     * @description 字段名
     * @author taogl
     * @date 2019年5月31日 上午11:31:48
     * @version v1.0.0
     */
    String[] fileds();

}
