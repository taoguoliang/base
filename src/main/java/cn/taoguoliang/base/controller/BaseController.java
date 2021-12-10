package cn.taoguoliang.base.controller;

import cn.taoguoliang.base.common.model.Result;
import cn.taoguoliang.base.repository.BaseRepository;
import cn.taoguoliang.base.service.BaseService;
import cn.taoguoliang.base.utils.BeanUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
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
public abstract class BaseController<E, K extends Serializable, R extends BaseRepository<E>, D, V> {

    @Resource
    private BaseService<E, R> baseService;

    /**
     * 获取entity，实例化需要，暂时没想到好办法初始化bean.
     *
     * @return Class<E>
     * @author taogl
     * @date 2021/12/10 3:17 PM
     **/
    protected abstract Class<E> getEntityCls();

    /**
     * 获取vo，实例化需要，暂时没想到好办法初始化bean.
     *
     * @return Class<V>
     * @author taogl
     * @date 2021/12/10 3:17 PM
     **/
    protected abstract Class<V> getVoCls();

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
        E entity = BeanUtils.getBeanCheckCls(dto, getEntityCls());
        E afterSave = baseService.save(entity);
        V vo = BeanUtils.getBeanCheckCls(afterSave, getVoCls());
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
        E entity = BeanUtils.getBeanCheckCls(dto, getEntityCls());
        E afterSave = baseService.saveOrUpdate(entity, id);
        V vo = BeanUtils.getBeanCheckCls(afterSave, getVoCls());
        return Result.ok(vo);
    }

    @ApiOperation(value = "删除一个数据", notes = "物理删除")
    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable(value = "id") K id) {
        baseService.deleteById(id);
        return Result.ok();
    }

}