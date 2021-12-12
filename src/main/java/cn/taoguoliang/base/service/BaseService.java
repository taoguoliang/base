package cn.taoguoliang.base.service;

import cn.taoguoliang.base.common.model.PageAndSort;
import cn.taoguoliang.base.repository.BaseRepository;
import cn.taoguoliang.base.spec.supplier.SpecificationSupplier;
import cn.taoguoliang.base.utils.BeanUtils;
import cn.taoguoliang.base.utils.PageUtils;
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
 * @version v1.0.0
 * @description 基础服务
 * @date 2019/7/18 17:07
 */
public interface BaseService<E, K extends Serializable> extends CommonQueryService<E> {

    /**
     * BaseService
     *
     * @return repository
     * @description 获取对应的repository
     * @author taogl
     * @date 2019/9/11 10:57
     * @version v1.0.0
     **/
    BaseRepository<E, K> getBaseRepository();

    /**
     * 默认服务实体填充.
     *
     * @param cls 默认实体
     * @author taogl
     * @date 2021/12/12 21:17
     **/
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
     * @date 2019/9/11 10:58
     * @version v1.0.0
     **/
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
     * @date 2019/9/11 10:58
     * @version v1.0.0
     **/
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
     * @date 2019/11/14 17:37
     * @version v1.0.0
     **/
    default E update(E entity) {
        return getBaseRepository().save(entity);
    }

    /**
     * BaseService
     *
     * @param id 主键
     * @description 根据主键删除
     * @author taogl
     * @date 2019/9/11 11:01
     * @version v1.0.0
     **/
    default void deleteById(K id) {
        getBaseRepository().deleteById(id);
    }

    /**
     * BaseService
     *
     * @param entities 实体集合
     * @description 批量删除-不能实现逻辑删除
     * @author taogl
     * @date 2019/9/11 11:01
     * @version v1.0.0
     **/
    default void deleteInBatch(Iterable<E> entities) {
        getBaseRepository().deleteInBatch(entities);
    }

    /**
     * BaseService
     *
     * @description 全量删除
     * @author taogl
     * @date 2019/12/5 10:49
     * @version v1.0.0
     **/
    default void deleteAll() {
        getBaseRepository().deleteAll();
    }

    /**
     * BaseService
     *
     * @param entities 删除的实体
     * @description 批量删除-能实现逻辑删除
     * @author taogl
     * @date 2019/12/5 10:50
     * @version v1.0.0
     **/
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
     * @date 2019/9/11 11:01
     * @version v1.0.0
     **/
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
     * @date 2019/9/11 11:02
     * @version v1.0.0
     **/
    default E getOneByParam(Object param) {
        return getBaseRepository().findOne(queryPre(param)).orElse(null);
    }

    /**
     * BaseService
     *
     * @param param jpa-plus查询实体
     * @return 查询的实体集合
     * @description 根据参数查询（参数是pageAndSort，jpa-plus的实体，不是普通的entity，和example不一样）
     * @author taogl
     * @date 2019/9/11 11:03
     * @version v1.0.0
     **/
    @Override
    default List<E> getListByParam(Object param) {
        return getBaseRepository().findAll(queryPre(param));
    }

    /**
     * BaseService
     *
     * @param param jpa-plus查询实体
     * @return 查询的实体集合-分页
     * @description 根据参数查询（参数是pageAndSort，jpa-plus的实体，不是普通的entity，和example不一样）
     * @author taogl
     * @date 2019/9/11 11:03
     * @version v1.0.0
     **/
    @Override
    default Page<E> getPageList(PageAndSort param) {
        return getBaseRepository().findAll(queryPre(param), PageUtils.buildPageRequest(param));
    }

    /**
     * BaseService
     *
     * @param param jpa查询参数
     * @return 查询参数
     * @description 构建查询参数
     * @author taogl
     * @date 2019/9/11 11:07
     * @version v1.0.0
     **/
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
     * @date 2019/9/11 11:08
     * @version v1.0.0
     **/
    default List<E> findAll(Example<E> example) {
        return getBaseRepository().findAll(example);
    }

    /**
     * BaseService
     *
     * @return 查询集合E
     * @description 查询全部
     * @author taogl
     * @date 2019/10/11 11:02
     * @version v1.0.0
     **/
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
     * @date 2019/11/11 10:58
     * @version v1.0.0
     **/
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
     * @date 2019/9/11 11:08
     * @version v1.0.0
     **/
    default Page<E> findPageAll(Example<E> example, PageAndSort pageAndSort) {
        return getBaseRepository().findAll(example, PageUtils.buildPageRequest(pageAndSort));
    }

    /**
     * BaseService
     *
     * @param example 实体实例
     * @return 单个实体
     * @description 根据example查询一个对象
     * @author taogl
     * @date 2019/9/11 11:08
     * @version v1.0.0
     **/
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
     * @date 2019/11/11 10:54
     * @version v1.0.0
     **/
    default List<E> saveAll(Iterable<E> entities) {
        return getBaseRepository().saveAll(entities);
    }

}
