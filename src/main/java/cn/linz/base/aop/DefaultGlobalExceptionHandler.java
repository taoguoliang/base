package cn.linz.base.aop;

import cn.linz.base.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    /**
     * <p>MethodArgumentNotValidException.</p>
     *
     * @param ex a {@link java.lang.Exception} object
     * @return a {@link cn.linz.base.common.model.Result} object
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
        return Result.warn(msg);
    }

    /**
     * <p>ConstraintViolationException.</p>
     *
     * @param ex a {@link java.lang.Exception} object
     * @return a {@link cn.linz.base.common.model.Result} object
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return Result.warn(ex.getMessage());
    }
}
