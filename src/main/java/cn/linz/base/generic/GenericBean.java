package cn.linz.base.generic;

import java.lang.reflect.ParameterizedType;

/**
 * 泛型生成工具类
 *
 * @param <E> 实体
 * @param <D> 请求保存DTO
 * @param <Q> 请求查询DTO
 * @param <V> 展示VO
 * @author taogl
 * @date 2021/12/10 10:43 AM
 * @version 1.0.0
 */
@SuppressWarnings("unchecked")
public class GenericBean<E, K, D, Q, V> {

    /**
     * <p>getClassOfE.</p>
     *
     * @return a {@link java.lang.Class} object
     */
    public Class<E> getClassOfE() {
        return (Class<E>) this.getClassOfGeneric(0);
    }

    /**
     * <p>getClassOfD.</p>
     *
     * @return a {@link java.lang.Class} object
     */
    public Class<D> getClassOfD() {
        return (Class<D>) this.getClassOfGeneric(2);
    }

    /**
     * <p>getClassOfQ.</p>
     *
     * @return a {@link java.lang.Class} object
     */
    public Class<Q> getClassOfQ() {
        return (Class<Q>) this.getClassOfGeneric(3);
    }

    /**
     * <p>getClassOfV.</p>
     *
     * @return a {@link java.lang.Class} object
     */
    public Class<V> getClassOfV() {
        return (Class<V>) this.getClassOfGeneric(4);
    }

    private Class<?> getClassOfGeneric(int idx) {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<?>) superClass.getActualTypeArguments()[idx];
    }

}
