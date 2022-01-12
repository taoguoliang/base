package cn.linz.base.exception;

/**
 * id生成异常
 *
 * @author taogl
 * @date 2022/1/12 11:02 AM
 */
public class IdGeneratorException extends RuntimeException {

    public IdGeneratorException(String message) {
        super(message);
    }

    public IdGeneratorException(String message, Throwable t) {
        super(message, t);
    }

}
