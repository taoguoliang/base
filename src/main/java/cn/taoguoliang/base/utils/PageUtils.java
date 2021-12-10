package cn.taoguoliang.base.utils;

import cn.taoguoliang.base.common.model.PageAndSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

/**
 * PageUtil
 *
 * @author taogl
 * @version 1.0
 * @description 分页工具类
 * @date 2019年3月13日 下午2:38:37
 */
public final class PageUtils {

    private PageUtils() {
    }

    /**
     * PageUtil
     *
     * @param pageAndSort
     * @return
     * @description 设置分页信息
     * @author taogl
     * @date 2019年3月13日 下午2:38:29
     * @version 1.0
     */
    public static PageRequest buildPageRequest(PageAndSort pageAndSort) {
        if (pageAndSort.getOffset() != null) {
            return PageRequest.of(pageAndSort.getOffset() / pageAndSort.getLimit(), pageAndSort.getLimit(),
                    sequence(pageAndSort));
        }
        return PageRequest.of(pageAndSort.getPage() - 1, pageAndSort.getLimit(), sequence(pageAndSort));
    }

    /**
     * PageUtil
     *
     * @param pageAndSort
     * @return
     * @description 设置排序
     * @author taogl
     * @date 2019年3月13日 下午2:38:18
     * @version 1.0
     */
    public static Sort sequence(PageAndSort pageAndSort) {
        if (pageAndSort.getOrders() != null && !pageAndSort.getOrders().isEmpty()) {
            return Sort.by(pageAndSort.getOrders());
        }
        return Sort.unsorted();
    }

    /**
     * PageUtils
     *
     * @param direction 顺序
     * @param field 字段
     * @return
     * @description 设置排序
     * @author taogl
     * @date 2018年9月7日 上午10:32:52
     * @version 1.0.0
     */
    public static Order sequence(Direction direction, String field) {
        return new Order(direction, field);
    }

    /**
     * PageUtils
     *
     * @param list 需要分页的list
     * @param pageable 分页参数
     * @return 分页后的数据
     * @description 把传入的List分页返回
     * @author taogl
     * @date 2019/4/12 13:17
     * @version v1.0.0
     **/
    public static <T> Page<T> listConvertToPage(List<T> list, PageAndSort pageable) {
        int start = pageable.getOffset();
        int end = Math.min((start + pageable.getLimit()), list.size());
        return new PageImpl<>(list.subList(start, end), PageUtils.buildPageRequest(pageable), list.size());
    }

}