package cn.taoguoliang.base.repository.impl;

import cn.taoguoliang.base.repository.BaseRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
public class BaseRepositoryImpl<E, K extends Serializable> implements BaseRepository<E, K> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<E> cls;

    @Override
    public void setEntityCls(Class<E> cls) {
        this.cls = cls;
    }

    @Override
    public List<E> findAll() {
        return null;
    }

    @Override
    public List<E> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<E> findAllById(Iterable<K> ks) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(K k) {

    }

    @Override
    public void delete(E entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends K> ks) {

    }

    @Override
    public void deleteAll(Iterable<? extends E> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends E> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <S extends E> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<E> findById(K k) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(K k) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends E> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends E> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<E> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<K> ks) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public E getOne(K k) {
        return null;
    }

    @Override
    public E getById(K k) {
        return null;
    }

    @Override
    public <S extends E> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends E> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends E> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends E> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends E> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends E> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends E, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<E> findOne(Specification<E> spec) {
        return Optional.empty();
    }

    @Override
    public List<E> findAll(Specification<E> spec) {
        return null;
    }

    @Override
    public Page<E> findAll(Specification<E> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<E> findAll(Specification<E> spec, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<E> spec) {
        return 0;
    }

}
