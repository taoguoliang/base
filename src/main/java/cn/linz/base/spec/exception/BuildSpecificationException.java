package cn.linz.base.spec.exception;

/**
 * BuildSpecificationException
 *
 * @author taogl
 * @version v1.0.0
 * @description 构建异常
 * @date 2019年3月28日 下午3:32:22
 */
public class BuildSpecificationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for BuildSpecificationException.</p>
     *
     * @param message a {@link java.lang.String} object
     * @param e a {@link java.lang.Throwable} object
     */
    public BuildSpecificationException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * <p>Constructor for BuildSpecificationException.</p>
     *
     * @param message a {@link java.lang.String} object
     */
    public BuildSpecificationException(String message) {
        super(message);
    }

}
