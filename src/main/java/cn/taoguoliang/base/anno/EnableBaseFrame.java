package cn.taoguoliang.base.anno;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用base框架注解
 *
 * @author taogl
 * @date 2021/12/13 9:49 AM
 */
@ComponentScan("cn.taoguoliang.base")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableBaseFrame {

}
