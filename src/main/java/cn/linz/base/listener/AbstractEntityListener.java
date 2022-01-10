package cn.linz.base.listener;

import cn.linz.base.common.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * listener.
 *
 * @author taogl
 * @date 2021/12/10 3:35 PM
 **/
@Slf4j
public abstract class AbstractEntityListener<K extends Serializable> {

    protected void prePersist(BaseEntity<K> baseEntity) {
        log.debug("prePersis: {}", baseEntity);
    }

    protected void preUpdate(BaseEntity<K> baseEntity) {
        log.debug("preUpdate: {}", baseEntity);
    }

    protected void preRemove(BaseEntity<K> baseEntity) {
        log.debug("preRemove: {}", baseEntity);
    }

    protected void preDestroy() {
        log.debug("preDestroy");
    }

    protected void postPersist(BaseEntity<K> baseEntity) {
        log.debug("postPersist: {}", baseEntity);
    }

    protected void postUpdate(BaseEntity<K> baseEntity) {
        log.debug("postUpdate: {}", baseEntity);
    }

    protected void postRemove(BaseEntity<K> baseEntity) {
        log.debug("postRemove: {}", baseEntity);
    }

    protected void postLoad(BaseEntity<K> baseEntity) {
        log.debug("postLoad: {}", baseEntity);
    }

}