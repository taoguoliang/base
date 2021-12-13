package cn.linz.base.common.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果枚举
 *
 * @author taogl
 * @date 2021/12/10 5:34 PM
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    /** 常用结果枚举*/
    OK(200, "success"),
    WARN(3000, "warn"),
    ERROR(5000, "error");

    private final int code;
    private final String message;
}
