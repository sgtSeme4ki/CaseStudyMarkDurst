package com.DBSystelGmbH.CaseStudyMarkDurst.common.base.service;

import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.model.BaseEntity;
import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.model.BaseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity> {

    private final BaseRepository<T> repository;

    @Transactional
    public T create(@NonNull T t) {
        T persisted = repository.save(t);
        log.info("{} created with id {}", persisted.getClass().getSimpleName(), persisted.getId());

        return persisted;
    }

    public T findById(@NonNull Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        });
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public T update(@NonNull Long id, @NonNull T t) {
        T update = findById(id);

        BeanUtils.copyProperties(t, update);

        T persisted = repository.save(t);

        log.info("{} with id {} updated", persisted.getClass().getSimpleName(), persisted.getId());
        return persisted;
    }

    @Transactional
    public void delete(@NonNull Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity with id " + id + " does not exist");
        }
        repository.deleteById(id);
    }
}
