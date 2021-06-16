package base.service;

import base.model.BaseEntity;
import base.model.BaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity> {

    private final BaseRepository<T> repository;
}
