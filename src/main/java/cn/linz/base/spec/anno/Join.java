package cn.linz.base.spec.anno;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.persistence.criteria.JoinType;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
/**
 * Join
 *
 * @description 连接查询
 * @author taogl
 * @date 2019年6月4日 下午3:07:18
 * @version v1.0.0
 */
public @interface Join {

    /**
     * Join
     *
     * @return
     * @description 字段名
     * @author taogl
     * @date 2019年3月27日 下午4:27:02
     * @version v1.0.0
     */
    String value() default "";

    /**
     * Join
     *
     * @return
     * @description 联接类型
     * @author taogl
     * @date 2019年6月4日 下午3:12:30
     * @version v1.0.0
     */
    JoinType joinType() default JoinType.INNER;

    /**
     * Join
     *
     * @return
     * @description 关联的实体类
     * @author taogl
     * @date 2019年6月4日 下午3:12:30
     * @version v1.0.0
     */
    Class<?> targetClass();

}
