package cn.taoguoliang.base.repository.impl;

import cn.taoguoliang.base.repository.BaseRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
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
 */
@Repository("baseRepository")
@Transactional(rollbackFor = Exception.class)
public class BaseRepositoryImpl<E, K extends Serializable> implements BaseRepository<E, K>, ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<E> cls;

    private SimpleJpaRepository<E, K> simpleJpaRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        simpleJpaRepository = new SimpleJpaRepository<>(cls, entityManager);
    }

    @Override
    public void setEntityCls(Class<E> cls) {
        this.cls = cls;
    }

    @Override
    public List<E> findAll() {
        return simpleJpaRepository.findAll();
    }

    @Override
    public List<E> findAll(Sort sort) {
        return simpleJpaRepository.findAll(sort);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return simpleJpaRepository.findAll(pageable);
    }

    @Override
    public List<E> findAllById(Iterable<K> ks) {
        return simpleJpaRepository.findAllById(ks);
    }

    @Override
    public long count() {
        return simpleJpaRepository.count();
    }

    @Override
    public void deleteById(K k) {
        simpleJpaRepository.deleteById(k);
    }

    @Override
    public void delete(E entity) {
        simpleJpaRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends K> ks) {
        simpleJpaRepository.deleteAllById(ks);
    }

    @Override
    public void deleteAll(Iterable<? extends E> entities) {
        simpleJpaRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        simpleJpaRepository.deleteAll();
    }

    @Override
    public <S extends E> S save(S entity) {
        return simpleJpaRepository.save(entity);
    }

    @Override
    public <S extends E> List<S> saveAll(Iterable<S> entities) {
        return simpleJpaRepository.saveAll(entities);
    }

    @Override
    public Optional<E> findById(K k) {
        return simpleJpaRepository.findById(k);
    }

    @Override
    public boolean existsById(K k) {
        return simpleJpaRepository.existsById(k);
    }

    @Override
    public void flush() {
        simpleJpaRepository.flush();
    }

    @Override
    public <S extends E> S saveAndFlush(S entity) {
        return simpleJpaRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends E> List<S> saveAllAndFlush(Iterable<S> entities) {
        return simpleJpaRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<E> entities) {
        simpleJpaRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<K> ks) {
        simpleJpaRepository.deleteAllByIdInBatch(ks);
    }

    @Override
    public void deleteAllInBatch() {
        simpleJpaRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public E getOne(K k) {
        return simpleJpaRepository.getOne(k);
    }

    @Override
    public E getById(K k) {
        return simpleJpaRepository.getById(k);
    }

    @Override
    public <S extends E> Optional<S> findOne(Example<S> example) {
        return simpleJpaRepository.findOne(example);
    }

    @Override
    public <S extends E> List<S> findAll(Example<S> example) {
        return simpleJpaRepository.findAll(example);
    }

    @Override
    public <S extends E> List<S> findAll(Example<S> example, Sort sort) {
        return simpleJpaRepository.findAll(example, sort);
    }

    @Override
    public <S extends E> Page<S> findAll(Example<S> example, Pageable pageable) {
        return simpleJpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends E> long count(Example<S> example) {
        return simpleJpaRepository.count(example);
    }

    @Override
    public <S extends E> boolean exists(Example<S> example) {
        return simpleJpaRepository.exists(example);
    }

    @Override
    public <S extends E, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return simpleJpaRepository.findBy(example, queryFunction);
    }

    @Override
    public Optional<E> findOne(Specification<E> spec) {
        return simpleJpaRepository.findOne(spec);
    }

    @Override
    public List<E> findAll(Specification<E> spec) {
        return simpleJpaRepository.findAll(spec);
    }

    @Override
    public Page<E> findAll(Specification<E> spec, Pageable pageable) {
        return simpleJpaRepository.findAll(spec, pageable);
    }

    @Override
    public List<E> findAll(Specification<E> spec, Sort sort) {
        return simpleJpaRepository.findAll(spec, sort);
    }

    @Override
    public long count(Specification<E> spec) {
        return simpleJpaRepository.count(spec);
    }

}
