package cn.taoguoliang.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * BaseRepository
 *
 * @author taogl
 * @version v1.0.0
 * @description 基础dao
 * @date 2019/7/18 17:08
 */
@NoRepositoryBean
public interface BaseRepository<E> extends JpaSpecificationExecutor<E>, JpaRepository<E, Serializable> {

    default void setEntityCls(Class<E> cls) {
    }
}
