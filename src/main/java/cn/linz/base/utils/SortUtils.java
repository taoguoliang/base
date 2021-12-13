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
 * @version 1.0.0
 * @description 处理 Sort 工具类
 * @date 2019年9月10日 下午1:54:56
 */
public final class SortUtils {

    private SortUtils() {
    }

    /**
     * SortUtils
     *
     * @param sort
     * @return
     * @description 将 sort 字符串转换为 Order 集合<br>
     *         例如："-fyid,create_time" -> [Order(Direction.DESC, "fyid"),Order(Direction.ASC, "create_time")]
     * @author taogl
     * @date 2019年9月10日 下午2:00:20
     * @version v1.0.0
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
