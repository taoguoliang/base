package cn.taoguoliang.base.generic;

import cn.taoguoliang.base.exception.GenericException;

import java.lang.reflect.ParameterizedType;

/**
 * 泛型生成工具类
 *
 * @author taogl
 * @date 2021/12/10 10:43 AM
 */
@SuppressWarnings("unchecked")
public class GenericBean<E> {

    public E getInstance() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<E> type = (Class<E>) superClass.getActualTypeArguments()[0];
        try {
            return type.newInstance();
        } catch (Exception e) {
            // Oops, no default constructor
            throw new GenericException(e.getMessage(), e);
        }
    }

    public Class<E> getClassOfT() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) superClass.getActualTypeArguments()[0];
    }

}
