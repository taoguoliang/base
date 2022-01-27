package cn.linz.base.service.impl;

import cn.linz.base.repository.BaseRepository;
import cn.linz.base.service.BaseService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * base service impl
 *
 * @author taogl
 * @date 2021/12/12 13:42
 * @version 1.0.0
 */
@Service("baseService")
@Scope("prototype")
public class BaseServiceImpl<E, K extends Serializable> implements BaseService<E, K> {

    @Resource(name = "baseRepository")
    private BaseRepository<E, K> baseRepository;

    /** {@inheritDoc} */
    @Override
    public BaseRepository<E, K> getBaseRepository() {
        return baseRepository;
    }

}
