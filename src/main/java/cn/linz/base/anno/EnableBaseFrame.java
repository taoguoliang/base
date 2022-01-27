package cn.linz.base.anno;

import cn.linz.base.config.BaseCommonConfig;
import org.springframework.context.annotation.Import;

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
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BaseCommonConfig.class)
public @interface EnableBaseFrame {

}
