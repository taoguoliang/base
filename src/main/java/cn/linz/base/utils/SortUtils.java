package cn.linz.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * SortUtils
 *
 * @author taogl
 */
public final class SortUtils {

    private SortUtils() {
    }

    /**
     * @param sort 排序字段
     * @description 将 sort 字符串转换为 Order 集合<br>
     *         例如："-create_time,id" -> [Order(Direction.DESC, "create_time"),Order(Direction.ASC, "id"),]
     */
    public static List<Order> toOrders(String sort) {
        List<Order> orders = null;
        if (!StringUtils.isBlank(sort)) {
            String[] sorts = sort.split(",");
            orders = new ArrayList<>();
            for (String s : sorts) {
                if (s.startsWith("-")) {
                    orders.add(new Order(Direction.DESC, s.substring(1)));
                } else {
                    orders.add(new Order(Direction.ASC, s));
                }
            }
        }
        return orders;
    }

}
