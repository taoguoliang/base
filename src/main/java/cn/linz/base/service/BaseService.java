package cn.linz.base.service;

import cn.linz.base.common.model.BaseSort;
import cn.linz.base.common.model.PageAndSort;
import cn.linz.base.repository.BaseRepository;
import cn.linz.base.spec.supplier.SpecificationSupplier;
import cn.linz.base.utils.BeanUtils;
import cn.linz.base.utils.PageAndSortUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 *
 * @param <E> 查询实体
 * @author taogl
 * @version 1.0.0
 */
public interface BaseService<E, K extends Serializable> extends CommonQueryService<E> {

    /**
     * BaseService
     *
     * @return repository
     * @description 获取对应的repository
     * @author taogl
     */
    BaseRepository<E, K> getBaseRepository();

    /**
     * 默认服务实体填充.
     *
     * @param cls 默认实体
     * @author taogl
     * @date 2021/12/12 21:17
     */
    default void setEntityCls(Class<E> cls) {
        getBaseRepository().setEntityCls(cls);
    }

    /**
     * BaseService
     *
     * @param id 主键
     * @return 实体
     * @description 根据主键查询实体
     * @author taogl
     */
    default E getById(K id) {
        return getBaseRepository().findById(id).orElse(null);
    }

    /**
     * BaseService
     *
     * @param entity 实体
     * @return 保存之后的实体
     * @description 保存
     * @author taogl
     */
    default E save(E entity) {
        return getBaseRepository().save(entity);
    }

    /**
     * BaseService
     *
     * @param entity 需要更新的实体
     * @return 更新之后的实体
     * @description 全量更新
     * @author taogl
     */
    default E update(E entity) {
        return getBaseRepository().save(entity);
    }

    /**
     * BaseService
     *
     * @param id 主键
     * @description 根据主键删除
     * @author taogl
     */
    default void deleteById(K id) {
        getBaseRepository().deleteById(id);
    }

    /**
     * BaseService
     *
     * @param entities 实体集合
     * @description 批量删除-不能实现逻辑删除
     * @author taogl
     */
    default void deleteInBatch(Iterable<E> entities) {
        getBaseRepository().deleteInBatch(entities);
    }

    /**
     * BaseService
     *
     * @description 全量删除
     * @author taogl
     */
    default void deleteAll() {
        getBaseRepository().deleteAll();
    }

    /**
     * BaseService
     *
     * @param entities 删除的实体
     * @description 批量删除-能实现逻辑删除
     * @author taogl
     */
    default void deleteAll(Iterable<E> entities) {
        getBaseRepository().deleteAll(entities);
    }

    /**
     * BaseService
     *
     * @param entity 实体
     * @param id 编号
     * @return 保存/更新的实体
     * @description 保存或者更新
     * @author taogl
     */
    @Transactional(rollbackFor = Exception.class)
    default E saveOrUpdate(E entity, K id) {
        E old = id == null ? null : getById(id);
        if (old == null) {
            return save(entity);
        } else {
            BeanUtils.copyNotNullProperties(entity, old);
            return save(old);
        }
    }

    /**
     * BaseService
     *
     * @param param jpa-plus查询实体
     * @return 查询的实体
     * @description 根据参数查询（参数是pageAndSort，jpa-plus的实体，不是普通的entity，和example不一样）
     * @author taogl
     */
    default E getOneByParam(Object param) {
        return getBaseRepository().findOne(queryPre(param)).orElse(null);
    }

    /**
     * {@inheritDoc}
     *
     * BaseService
     * @description 根据参数查询（参数是pageAndSort，jpa-plus的实体，不是普通的entity，和example不一样）
     * @author taogl
     */
    @Override
    default List<E> getListByParam(Object param) {
        return getBaseRepository().findAll(queryPre(param));
    }

    /**
     * {@inheritDoc}
     *
     * 根据参数查询列表，带排序
     */
    @Override
    default List<E> getListByParamWithSort(BaseSort param) {
        return getBaseRepository().findAll(queryPre(param), PageAndSortUtils.sequence(param));
    }

    /**
     * {@inheritDoc}
     *
     * BaseService
     * @description 根据参数查询（参数是pageAndSort，jpa-plus的实体，不是普通的entity，和example不一样）
     * @author taogl
     */
    @Override
    default Page<E> getPageList(PageAndSort param) {
        return getBaseRepository().findAll(queryPre(param), PageAndSortUtils.buildPageRequest(param));
    }

    /**
     * BaseService
     *
     * @param param jpa查询参数
     * @return 查询参数
     * @description 构建查询参数
     * @author taogl
     */
    default Specification<E> queryPre(Object param) {
        return SpecificationSupplier.buildSpecification(param);
    }

    /**
     * BaseService
     *
     * @param example 实体实例
     * @return 实体集合
     * @description 根据example查询list
     * @author taogl
     */
    default List<E> findAll(Example<E> example) {
        return getBaseRepository().findAll(example);
    }

    /**
     * BaseService
     *
     * @return 查询集合E
     * @description 查询全部
     * @author taogl
     */
    default List<E> findAll() {
        return getBaseRepository().findAll();
    }

    /**
     * BaseService
     *
     * @param sort 排序规则
     * @return 查询集合E
     * @description 查询全部-排序
     * @author taogl
     */
    default List<E> findAll(Sort sort) {
        return getBaseRepository().findAll(sort);
    }

    /**
     * BaseService
     *
     * @param example 实体实例
     * @param pageAndSort jpa-plus的分页参数
     * @return 实体集合分页
     * @description 根据example查询page
     * @author taogl
     */
    default Page<E> findPageAll(Example<E> example, PageAndSort pageAndSort) {
        return getBaseRepository().findAll(example, PageAndSortUtils.buildPageRequest(pageAndSort));
    }

    /**
     * BaseService
     *
     * @param example 实体实例
     * @return 单个实体
     * @description 根据example查询一个对象
     * @author taogl
     */
    default E findOne(Example<E> example) {
        return getBaseRepository().findOne(example).orElse(null);
    }

    /**
     * BaseService
     *
     * @param entities 批量保存的实体
     * @return v1.0.0
     * @description 批量保存
     * @author taogl
     */
    default List<E> saveAll(Iterable<E> entities) {
        return getBaseRepository().saveAll(entities);
    }

}
