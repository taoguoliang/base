package cn.taoguoliang.base.spec.exception;

/**
 *
 * BuildSpecificationException
 *
 * @description 构建异常
 * @author taogl
 * @date 2019年3月28日 下午3:32:22
 * @version v1.0.0
 */
public class BuildSpecificationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BuildSpecificationException(String message, Throwable e) {
        super(message, e);
    }

    public BuildSpecificationException(String message) {
        super(message);
    }

}
