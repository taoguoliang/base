package cn.taoguoliang.base.service.impl;

import cn.taoguoliang.base.repository.BaseRepository;
import cn.taoguoliang.base.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * base service impl
 *
 * @author taogl
 * @date 2021/12/12 13:42
 */
@Service("baseService")
public class BaseServiceImpl<E> implements BaseService<E> {

    @Resource(name = "baseRepository")
    private BaseRepository<E> baseRepository;

    @Override
    public BaseRepository<E> getBaseRepository() {
        return baseRepository;
    }

}
