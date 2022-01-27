package cn.linz.base.exception;

/**
 * id生成异常
 *
 * @author taogl
 * @date 2022/1/12 11:02 AM
 * @version 1.0.0
 */
public class IdGeneratorException extends RuntimeException {

    /**
     * <p>Constructor for IdGeneratorException.</p>
     *
     * @param message a {@link java.lang.String} object
     */
    public IdGeneratorException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for IdGeneratorException.</p>
     *
     * @param message a {@link java.lang.String} object
     * @param t a {@link java.lang.Throwable} object
     */
    public IdGeneratorException(String message, Throwable t) {
        super(message, t);
    }

}
