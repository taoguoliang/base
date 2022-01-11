package cn.linz.base.controller;

import cn.linz.base.common.model.BaseSort;
import cn.linz.base.common.model.PageAndSort;
import cn.linz.base.common.model.Result;
import cn.linz.base.generic.GenericBean;
import cn.linz.base.service.BaseService;
import cn.linz.base.utils.BeanUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BaseController.
 *
 * @author taogl
 * @date 2021/12/10 10:36 AM
 **/
public abstract class BaseController<E, K extends Serializable, D, Q, V> extends GenericBean<E, D, Q, V> implements InitializingBean {

    @Resource(name = "baseService")
    private BaseService<E, K> baseService;

    /**
     * 初始化默认范型实体
     * 这里是为了让继承此类的接口类，能传入当前接口操作的实体，然后通过 Service 传入 repository
     * 传入到 repository 之后 就可以使用 EntityManager 的通用方法了
     */
    @Override
    public void afterPropertiesSet() {
        final Class<E> classOfGeneric = this.getClassOfE();
        baseService.setEntityCls(classOfGeneric);
    }

    /**
     * 保存实体.
     *
     * @param dto dto
     * @return 返回的VO结果
     * @author taogl
     * @date 2021/12/10 3:26 PM
     **/
    @ApiOperation(value = "新增一个实体", notes = "新增一个实体")
    @PostMapping
    public Result<V> save(@RequestBody D dto) {
        E entity = BeanUtils.getBeanCheckCls(dto, this.getClassOfE());
        E afterSave = baseService.save(entity);
        V vo = BeanUtils.getBeanCheckCls(afterSave, this.getClassOfV());
        return Result.ok(vo);
    }

    /**
     * 保存前先校验数据是否存在.
     *
     * @param dto dto
     * @return Result<V>
     * @author taogl
     * @date 2021/12/10 5:39 PM
     **/
    @PostMapping("/actions/save-before-check")
    @ApiOperation(value = "special:新增一个实体", notes = "新增一个实体，先校验数据是否存在")
    public Result<V> saveBeforeCheck(@RequestBody D dto) {
        List<E> listByParam = baseService.getListByParam(dto);
        if (CollectionUtils.isNotEmpty(listByParam)) {
            return Result.warn("传参数据已存在");
        }
        return this.save(dto);
    }

    /**
     * 保存或更新实体.
     *
     * @param id 主键，可以为空
     * @param dto 结果dto
     * @return vo结果
     * @author taogl
     * @date 2021/12/10 4:52 PM
     **/
    @ApiOperation(value = "新增或更新实体", notes = "新增或更新实体")
    @PutMapping
    public Result<V> saveOrUpdate(@RequestParam(value = "id", required = false) K id, @RequestBody D dto) {
        E entity = BeanUtils.getBeanCheckCls(dto, this.getClassOfE());
        E afterSave = baseService.saveOrUpdate(entity, id);
        V vo = BeanUtils.getBeanCheckCls(afterSave, this.getClassOfV());
        return Result.ok(vo);
    }

    /**
     * 根据主键删除实体
     *
     * @param id key
     * @return Result
     */
    @ApiOperation(value = "根据主键删除一个数据", notes = "物理删除")
    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable(value = "id") K id) {
        baseService.deleteById(id);
        return Result.ok();
    }

    /**
     * 根据主键获取一个实体
     *
     * @param id key
     * @return Result
     */
    @ApiOperation(value = "根据主键获取数据")
    @GetMapping("{id}")
    public Result<V> get(@PathVariable(value = "id") K id) {
        E byId = baseService.getById(id);
        V result = BeanUtils.getBeanCheckCls(byId, this.getClassOfV());
        return Result.ok(result);
    }

    /**
     * 根据参数查询分页数据
     *
     * @param pageAndSort 分页对象，可扩展
     * @return 分页结果数据
     */
    @ApiOperation(value = "查询分页数据")
    @GetMapping
    @SuppressWarnings("unchecked")
    public Result<Page<V>> page(PageAndSort pageAndSort) {
        Page<E> pageList = baseService.getPageList(pageAndSort);
        if (Objects.equals(this.getClassOfE(), this.getClassOfV())) {
            return Result.ok((Page<V>) pageList);
        }
        return Result.ok(pageList.map(entity -> BeanUtils.copyProperties(entity, this.getClassOfV())));
    }

    /**
     * 根据参数查询列表数据
     *
     * @param param 查询参数
     * @return 列表数据
     */
    @ApiOperation(value = "查询列表数据")
    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public List<V> list(Q param) {
        List<E> listByParam = baseService.getListByParam(param);
        if (Objects.equals(this.getClassOfE(), this.getClassOfV())) {
            return (List<V>) listByParam;
        }
        return listByParam.stream().map(entity -> BeanUtils.copyProperties(entity, this.getClassOfV())).collect(Collectors.toList());
    }

    /**
     * 根据参数查询列表数据，带排序
     *
     * @param param 查询参数
     * @return 列表数据
     */
    @ApiOperation(value = "查询列表数据，带排序")
    @GetMapping("/list-sort")
    @SuppressWarnings("unchecked")
    public List<V> listWithSort(BaseSort param) {
        List<E> listByParamWithSort = baseService.getListByParamWithSort(param);
        if (Objects.equals(this.getClassOfE(), this.getClassOfV())) {
            return (List<V>) listByParamWithSort;
        }
        return listByParamWithSort.stream().map(entity -> BeanUtils.copyProperties(entity, this.getClassOfV())).collect(Collectors.toList());
    }

    /**
     * 根据参数查询单个数据
     *
     * @param param 查询参数
     * @return 单个数据
     */
    @ApiOperation(value = "查询单个数据")
    @GetMapping("/one")
    public Result<V> one(Q param) {
        E oneByParam = baseService.getOneByParam(param);
        V result = BeanUtils.getBeanCheckCls(oneByParam, this.getClassOfV());
        return Result.ok(result);
    }

}