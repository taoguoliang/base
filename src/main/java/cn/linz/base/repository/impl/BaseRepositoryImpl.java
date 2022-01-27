package cn.linz.base.repository.impl;

import cn.linz.base.repository.BaseRepository;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * base repository
 *
 * @author taogl
 * @date 2021/12/12 13:44
 * @version 1.0.0
 */
@Repository("baseRepository")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class BaseRepositoryImpl<E, K extends Serializable> implements BaseRepository<E, K> {

    @PersistenceContext
    private EntityManager entityManager;

    @Getter
    private Class<E> cls;

    @Getter
    private SimpleJpaRepository<E, K> simpleJpaRepository;

    /** {@inheritDoc} */
    @Override
    public void setEntityCls(Class<E> cls) {
        this.cls = cls;
        simpleJpaRepository = new SimpleJpaRepository<>(cls, entityManager);
    }

    /** {@inheritDoc} */
    @Override
    public List<E> findAll() {
        return getSimpleJpaRepository().findAll();
    }

    /** {@inheritDoc} */
    @Override
    public List<E> findAll(Sort sort) {
        return getSimpleJpaRepository().findAll(sort);
    }

    /** {@inheritDoc} */
    @Override
    public Page<E> findAll(Pageable pageable) {
        return getSimpleJpaRepository().findAll(pageable);
    }

    /** {@inheritDoc} */
    @Override
    public List<E> findAllById(Iterable<K> ks) {
        return getSimpleJpaRepository().findAllById(ks);
    }

    /** {@inheritDoc} */
    @Override
    public long count() {
        return getSimpleJpaRepository().count();
    }

    /** {@inheritDoc} */
    @Override
    public void deleteById(K k) {
        getSimpleJpaRepository().deleteById(k);
    }

    /** {@inheritDoc} */
    @Override
    public void delete(E entity) {
        getSimpleJpaRepository().delete(entity);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteAllById(Iterable<? extends K> ks) {
        getSimpleJpaRepository().deleteAllById(ks);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteAll(Iterable<? extends E> entities) {
        getSimpleJpaRepository().deleteAll(entities);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteAll() {
        getSimpleJpaRepository().deleteAll();
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> S save(S entity) {
        return getSimpleJpaRepository().save(entity);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> List<S> saveAll(Iterable<S> entities) {
        return getSimpleJpaRepository().saveAll(entities);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<E> findById(K k) {
        return getSimpleJpaRepository().findById(k);
    }

    /** {@inheritDoc} */
    @Override
    public boolean existsById(K k) {
        return getSimpleJpaRepository().existsById(k);
    }

    /** {@inheritDoc} */
    @Override
    public void flush() {
        getSimpleJpaRepository().flush();
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> S saveAndFlush(S entity) {
        return getSimpleJpaRepository().saveAndFlush(entity);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> List<S> saveAllAndFlush(Iterable<S> entities) {
        return getSimpleJpaRepository().saveAllAndFlush(entities);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteAllInBatch(Iterable<E> entities) {
        getSimpleJpaRepository().deleteAllInBatch(entities);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteAllByIdInBatch(Iterable<K> ks) {
        getSimpleJpaRepository().deleteAllByIdInBatch(ks);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteAllInBatch() {
        getSimpleJpaRepository().deleteAllInBatch();
    }

    /** {@inheritDoc} */
    @Override
    @Deprecated
    public E getOne(K k) {
        return getSimpleJpaRepository().getOne(k);
    }

    /** {@inheritDoc} */
    @Override
    public E getById(K k) {
        return getSimpleJpaRepository().getById(k);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> Optional<S> findOne(Example<S> example) {
        return getSimpleJpaRepository().findOne(example);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> List<S> findAll(Example<S> example) {
        return getSimpleJpaRepository().findAll(example);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> List<S> findAll(Example<S> example, Sort sort) {
        return getSimpleJpaRepository().findAll(example, sort);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getSimpleJpaRepository().findAll(example, pageable);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> long count(Example<S> example) {
        return getSimpleJpaRepository().count(example);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E> boolean exists(Example<S> example) {
        return getSimpleJpaRepository().exists(example);
    }

    /** {@inheritDoc} */
    @Override
    public <S extends E, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return getSimpleJpaRepository().findBy(example, queryFunction);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<E> findOne(Specification<E> spec) {
        return getSimpleJpaRepository().findOne(spec);
    }

    /** {@inheritDoc} */
    @Override
    public List<E> findAll(Specification<E> spec) {
        return getSimpleJpaRepository().findAll(spec);
    }

    /** {@inheritDoc} */
    @Override
    public Page<E> findAll(Specification<E> spec, Pageable pageable) {
        return getSimpleJpaRepository().findAll(spec, pageable);
    }

    /** {@inheritDoc} */
    @Override
    public List<E> findAll(Specification<E> spec, Sort sort) {
        return getSimpleJpaRepository().findAll(spec, sort);
    }

    /** {@inheritDoc} */
    @Override
    public long count(Specification<E> spec) {
        return getSimpleJpaRepository().count(spec);
    }

}
