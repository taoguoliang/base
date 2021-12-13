package cn.linz.base.generic;

import cn.linz.base.exception.GenericException;

import java.lang.reflect.ParameterizedType;

/**
 * 泛型生成工具类
 *
 * @author taogl
 * @date 2021/12/10 10:43 AM
 */
@SuppressWarnings("unchecked")
public class GenericBean<E, D, V> {

    public E getInstanceOfE() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<E> type = (Class<E>) superClass.getActualTypeArguments()[0];
        try {
            return type.newInstance();
        } catch (Exception e) {
            // Oops, no default constructor
            throw new GenericException(e.getMessage(), e);
        }
    }

    public Class<E> getClassOfE() {
        return (Class<E>) this.getClassOfGeneric(0);
    }

    public Class<D> getClassOfD() {
        return (Class<D>) this.getClassOfGeneric(1);
    }

    public Class<V> getClassOfV() {
        return (Class<V>) this.getClassOfGeneric(2);
    }

    private Class<?> getClassOfGeneric(int idx) {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<?>) superClass.getActualTypeArguments()[idx];
    }

}
