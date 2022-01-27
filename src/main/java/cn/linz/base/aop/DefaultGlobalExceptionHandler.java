package cn.linz.base.aop;

import cn.linz.base.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 默认全局异常处理
 *
 * @author taogl
 * @date 2022/1/12 11:06 AM
 * @version 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class DefaultGlobalExceptionHandler {

    /**
     * <p>exception.</p>
     *
     * @param ex a {@link java.lang.Exception} object
     * @return a {@link cn.linz.base.common.model.Result} object
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public Result<String> exception(Exception ex) {
        log.error("未知异常,{}", ex.getMessage(), ex);
        return Result.error(ex.getMessage());
    }
}
