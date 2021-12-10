package cn.taoguoliang.base.common.model;

import cn.taoguoliang.base.common.consts.ResultEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Result.
 *
 * @author taogl
 * @date 2021/12/10 10:31 AM
 **/
@ToString
@Getter
@Setter
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 8708569195046968619L;

    private Integer code;

    private String msg;

    private T data;

    private Result() {
       this(ResultEnum.OK);
    }

    private Result(ResultEnum resultEnum) {
        this.setCode(resultEnum.getCode());
        this.setMsg(resultEnum.getMessage());
    }

    private Result(Integer code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    /**
     * 返回成功 不带msg和data
     *
     * @param <T> T
     * @return Result
     */
    public static <T> Result<T> ok() {
        return new Result<>();
    }

    /**
     * warn
     * @param <T> T
     * @return 结果
     */
    public static <T> Result<T> warn() {
        return new Result<>(ResultEnum.WARN);
    }

    /**
     * warn
     * @param <T> T
     * @param msg 警告⚠️信息
     * @return 结果
     */
    public static <T> Result<T> warn(String msg) {
        return new Result<>(ResultEnum.WARN.getCode(), msg);
    }

    /**
     * 返回成功 带上data
     *
     * @param data data
     * @param <T> T
     * @return Result
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    /**
     * 返回失败
     *
     * @param <T> T
     * @return Result
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMessage());
        return result;
    }

    /**
     * 返回失败
     *
     * @param msg 失败消息
     * @param <T> 返回失败
     * @return Result
     */
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回失败
     *
     * @param code 失败code
     * @param msg 失败消息
     * @param <T> T
     * @return Result
     */
    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
