package cn.linz.base.exception;

/**
 * 泛型化异常.
 *
 * @author taogl
 * @date 2021/12/10 10:48 AM
 * @version 1.0.0
 */
public class GenericException extends RuntimeException {

    /**
     * <p>Constructor for GenericException.</p>
     *
     * @param message a {@link java.lang.String} object
     * @param e a {@link java.lang.Throwable} object
     */
    public GenericException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * <p>Constructor for GenericException.</p>
     *
     * @param message a {@link java.lang.String} object
     */
    public GenericException(String message) {
        super(message);
    }

}
