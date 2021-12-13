package cn.linz.base.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * BeanMapUtils
 *
 * @author taogl
 * @version v1.0.0
 * @description map和bean互相转换的utils
 * @date 2018年11月26日 下午3:30:15
 */
@Slf4j
public final class BeanUtils {

    private BeanUtils() {
    }

    private static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> Objects.isNull(src.getPropertyValue(name)))
                .toArray(String[]::new);
    }

    /**
     * BeanUtils
     *
     * @param source 源
     * @param target 目的
     * @description 拷贝非null的参数
     * @author taogl
     * @date 2019/11/11 11:01
     * @version v1.0.0
     **/
    public static void copyNotNullProperties(Object source, Object target) {
        BeanUtil.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 根据class拷贝实体.
     *
     * @param source source
     * @param cls 类
     * @return T
     * @author taogl
     * @date 2021/12/10 10:55 AM
     **/
    public static <T> T copyProperties(Object source, Class<T> cls) {
        T target = ReflectUtil.newInstance(cls);
        BeanUtil.copyProperties(source, target);
        return target;
    }

    /**
     * 根据传进来的cls类型判断是否类型相同，相同强转，不同就拷贝.
     *
     * @param source 原bean
     * @param cls 要check的class类型
     * @return D
     * @author taogl
     * @date 2021/12/10 4:46 PM
     **/
    @SuppressWarnings("unchecked")
    public static <S, D> D getBeanCheckCls(S source, Class<D> cls) {
        if (Objects.equals(source.getClass(), cls)) {
            return (D) source;
        }
        return BeanUtils.copyProperties(source, cls);
    }

}
