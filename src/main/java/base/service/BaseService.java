package base.service;

import base.model.BaseEntity;
import base.model.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity> {

    private final BaseRepository<T> repository;
}
