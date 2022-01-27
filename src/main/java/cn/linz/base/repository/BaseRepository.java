package cn.linz.base.repository;

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
public interface BaseRepository<E, K extends Serializable> extends JpaSpecificationExecutor<E>, JpaRepository<E, K> {

    /**
     * 默认范型实体填充.
     *
     * @param cls 默认范型实体
     * @author taogl
     * @date 2021/12/12 21:20
     */
    default void setEntityCls(Class<E> cls) {
    }
}
