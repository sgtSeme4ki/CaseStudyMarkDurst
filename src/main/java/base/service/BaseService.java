package base.service;

import base.model.BaseEntity;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T extends BaseEntity> {
}
