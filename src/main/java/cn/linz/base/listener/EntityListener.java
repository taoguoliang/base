package cn.linz.base.listener;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * listener.
 *
 * @author taogl
 * @date 2021/12/10 3:35 PM
 **/
@Slf4j
public class EntityListener {

    @PrePersist
    public void prePersist(Object object) {
        log.debug("prePersis: {}", object);
    }

    @PreUpdate
    public void preUpdate(Object object) {
        log.debug("preUpdate: {}", object);
    }

    @PreRemove
    public void preRemove(Object object) {
        log.debug("preRemove: {}", object);
    }

    @PreDestroy
    public void preDestroy(Object object) {
        log.debug("preDestroy: {}", object);
    }

    @PostPersist
    public void postPersist(Object object) {
        log.debug("postPersist: {}", object);
    }

    @PostUpdate
    public void postUpdate(Object object) {
        log.debug("postUpdate: {}", object);
    }

    @PostRemove
    public void postRemove(Object object) {
        log.debug("postRemove: {}", object);
    }

    @PostLoad
    public void postLoad(Object object) {
        log.debug("postLoad: {}", object);
    }

}