package cn.taoguoliang.base.service;

import cn.taoguoliang.base.common.model.PageAndSort;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * CommonQueryService
 *
 * @param <E> 任意参数
 * @author taogl
 * @version v1.0.0
 * @description 通用查询服务
 * @date 2019/7/19 11:32
 */
public interface CommonQueryService<E> {

    /**
     * CommonQueryService
     *
     * @param param 查询参数
     * @return 列表集合
     * @description 列表查询
     * @author taogl
     * @date 2019/11/11 11:02
     * @version v1.0.0
     **/
    List<E> getListByParam(Object param);

    /**
     * CommonQueryService
     *
     * @param param jpa-plus的参数
     * @return 分页集合
     * @description 根据jpa-plus的参数查询分页数据
     * @author taogl
     * @date 2019/11/11 11:02
     * @version v1.0.0
     **/
    Page<E> getPageList(PageAndSort param);

}
