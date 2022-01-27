package cn.linz.base.listener;

import cn.linz.base.common.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * listener.
 *
 * @author taogl
 * @date 2021/12/10 3:35 PM
 * @version 1.0.0
 */
@Slf4j
public abstract class AbstractEntityListener<K extends Serializable> {

    /**
     * <p>prePersist.</p>
     *
     * @param baseEntity a {@link cn.linz.base.common.model.BaseEntity} object
     */
    protected void prePersist(BaseEntity<K> baseEntity) {
        log.debug("prePersis: {}", baseEntity);
    }

    /**
     * <p>preUpdate.</p>
     *
     * @param baseEntity a {@link cn.linz.base.common.model.BaseEntity} object
     */
    protected void preUpdate(BaseEntity<K> baseEntity) {
        log.debug("preUpdate: {}", baseEntity);
    }

    /**
     * <p>preRemove.</p>
     *
     * @param baseEntity a {@link cn.linz.base.common.model.BaseEntity} object
     */
    protected void preRemove(BaseEntity<K> baseEntity) {
        log.debug("preRemove: {}", baseEntity);
    }

    /**
     * <p>preDestroy.</p>
     */
    protected void preDestroy() {
        log.debug("preDestroy");
    }

    /**
     * <p>postPersist.</p>
     *
     * @param baseEntity a {@link cn.linz.base.common.model.BaseEntity} object
     */
    protected void postPersist(BaseEntity<K> baseEntity) {
        log.debug("postPersist: {}", baseEntity);
    }

    /**
     * <p>postUpdate.</p>
     *
     * @param baseEntity a {@link cn.linz.base.common.model.BaseEntity} object
     */
    protected void postUpdate(BaseEntity<K> baseEntity) {
        log.debug("postUpdate: {}", baseEntity);
    }

    /**
     * <p>postRemove.</p>
     *
     * @param baseEntity a {@link cn.linz.base.common.model.BaseEntity} object
     */
    protected void postRemove(BaseEntity<K> baseEntity) {
        log.debug("postRemove: {}", baseEntity);
    }

    /**
     * <p>postLoad.</p>
     *
     * @param baseEntity a {@link cn.linz.base.common.model.BaseEntity} object
     */
    protected void postLoad(BaseEntity<K> baseEntity) {
        log.debug("postLoad: {}", baseEntity);
    }

}
