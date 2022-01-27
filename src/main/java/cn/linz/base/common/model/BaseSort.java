package cn.linz.base.common.model;

import cn.linz.base.utils.SortUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 排序字段
 *
 * @author taogl
 * @date 2022/1/11 11:27 AM
 * @version 1.0.0
 */
@Getter
@Setter
@ToString
@ApiModel("排序")
public class BaseSort {

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", notes = "-create_time,id => create_time desc and id asc")
    private String sort;

    /**
     * 排序
     **/
    @ApiModelProperty(hidden = true)
    private List<Sort.Order> orders;

    /**
     * 设置排序
     *
     * @param sort 排序字段
     */
    public void setSort(String sort) {
        this.orders = SortUtils.toOrders(sort);
    }
}
