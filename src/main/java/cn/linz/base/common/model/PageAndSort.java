package cn.linz.base.common.model;

import cn.linz.base.common.consts.PageConsts;
import cn.linz.base.utils.SortUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

/**
 * PageAndSort
 *
 * @author taogl
 * @version 1.0
 * @description 分页参数接收实体类
 * @date 2019年3月13日 下午2:38:50
 */
@Getter
@Setter
@NoArgsConstructor
public class PageAndSort {

    /**
     * 请求页index
     */
    @ApiModelProperty("页面索引")
    private int page = PageConsts.DEFAULT_PAGE_PAGE;

    /**
     * 每页显示记录数
     */
    @ApiModelProperty("页面大小")
    private int limit = PageConsts.DEFAULT_PAGE_LIMIT;

    /**
     * 偏移量
     */
    @ApiModelProperty("偏移量")
    private Integer offset;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", notes = "-create_time,id => create_time desc and id asc")
    private String sort;

    /**
     * 排序
     **/
    @ApiModelProperty(hidden = true)
    private List<Order> orders;

    /**
     * 设置排序
     * @param sort 排序字段
     */
    public void setSort(String sort) {
        this.orders = SortUtils.toOrders(sort);
    }

}