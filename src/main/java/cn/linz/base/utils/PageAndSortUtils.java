package cn.linz.base.utils;

import cn.linz.base.common.model.BaseSort;
import cn.linz.base.common.model.PageAndSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

/**
 * PageAndSortUtils
 *
 * @author taogl
 * @version 1.0.0
 */
public final class PageAndSortUtils {

    private PageAndSortUtils() {
    }

    /**
     * PageAndSortUtils
     *
     * @param pageAndSort 分页及排序
     * @description 设置分页信息
     * @author taogl
     * @return a {@link org.springframework.data.domain.PageRequest} object
     */
    public static PageRequest buildPageRequest(PageAndSort pageAndSort) {
        if (pageAndSort.getOffset() != null) {
            return PageRequest.of(pageAndSort.getOffset() / pageAndSort.getLimit(), pageAndSort.getLimit(),
                    sequence(pageAndSort));
        }
        return PageRequest.of(pageAndSort.getPage() - 1, pageAndSort.getLimit(), sequence(pageAndSort));
    }

    /**
     * PageAndSortUtils
     *
     * @param sort 排序字段
     * @description 设置排序
     * @author taogl
     * @return a {@link org.springframework.data.domain.Sort} object
     */
    public static Sort sequence(BaseSort sort) {
        if (sort.getOrders() != null && !sort.getOrders().isEmpty()) {
            return Sort.by(sort.getOrders());
        }
        return Sort.unsorted();
    }

    /**
     * PageUtils
     *
     * @param direction 顺序
     * @param field 字段
     * @description 设置排序
     * @author taogl
     * @date 2018年9月7日 上午10:32:52
     * @version 1.0.0
     * @return a {@link org.springframework.data.domain.Sort.Order} object
     */
    public static Order sequence(Direction direction, String field) {
        return new Order(direction, field);
    }

    /**
     * PageAndSortUtils
     *
     * @param list 需要分页的list
     * @param pageable 分页参数
     * @description 把传入的List分页返回
     * @author taogl
     * @param <T> a T class
     * @return a {@link org.springframework.data.domain.Page} object
     */
    public static <T> Page<T> listConvertToPage(List<T> list, PageAndSort pageable) {
        int start = pageable.getOffset();
        int end = Math.min((start + pageable.getLimit()), list.size());
        return new PageImpl<>(list.subList(start, end), PageAndSortUtils.buildPageRequest(pageable), list.size());
    }

}
