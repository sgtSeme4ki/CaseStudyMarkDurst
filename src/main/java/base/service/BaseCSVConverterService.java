package base.service;

import base.model.BaseEntity;
import base.model.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseCSVConverterService<T extends BaseEntity> extends BaseService<T> {

    public BaseCSVConverterService(BaseRepository<T> repository) {
        super(repository);
    }
}
