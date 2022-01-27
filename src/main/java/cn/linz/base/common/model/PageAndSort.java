package cn.linz.base.common.model;

import cn.linz.base.common.consts.PageConsts;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class PageAndSort extends BaseSort {

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

}
