package cn.linz.base.service;

import cn.linz.base.common.model.BaseSort;
import cn.linz.base.common.model.PageAndSort;
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
     **/
    List<E> getListByParam(Object param);

    /**
     * 根据参数查询列表，带排序
     *
     * @param param 查询参数
     * @return 列表数据
     */
    List<E> getListByParamWithSort(BaseSort param);

    /**
     * CommonQueryService
     *
     * @param param jpa-plus的参数
     * @return 分页集合
     * @description 根据jpa-plus的参数查询分页数据
     * @author taogl
     **/
    Page<E> getPageList(PageAndSort param);

}
