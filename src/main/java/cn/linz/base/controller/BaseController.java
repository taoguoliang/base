package cn.linz.base.controller;

import cn.linz.base.common.model.Result;
import cn.linz.base.generic.GenericBean;
import cn.linz.base.service.BaseService;
import cn.linz.base.utils.BeanUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * BaseController.
 *
 * @author taogl
 * @date 2021/12/10 10:36 AM
 **/
public abstract class BaseController<E, K extends Serializable, D, V> extends GenericBean<E, D, V> implements InitializingBean {

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
     * @return cn.taoguoliang.base.common.model.Result<V>
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

    @ApiOperation(value = "删除一个数据", notes = "物理删除")
    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable(value = "id") K id) {
        baseService.deleteById(id);
        return Result.ok();
    }

}