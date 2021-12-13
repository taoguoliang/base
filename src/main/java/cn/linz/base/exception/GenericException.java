package cn.linz.base.exception;

/**
 * 泛型化异常.
 *
 * @author taogl
 * @date 2021/12/10 10:48 AM
 **/
public class GenericException extends RuntimeException {

    public GenericException(String message, Throwable e) {
        super(message, e);
    }

    public GenericException(String message) {
        super(message);
    }

}
