package cn.taoguoliang.base.service.impl;

import cn.taoguoliang.base.repository.BaseRepository;
import cn.taoguoliang.base.repository.impl.BaseRepositoryImpl;
import cn.taoguoliang.base.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * base service impl
 *
 * @author taogl
 * @date 2021/12/12 13:42
 */
@Service("baseService")
@RequiredArgsConstructor
public class BaseServiceImpl<E> implements BaseService<E> {

    private final BaseRepositoryImpl<E> baseRepository;

    @Override
    public BaseRepository<E> getBaseRepository() {
        return baseRepository;
    }



}
