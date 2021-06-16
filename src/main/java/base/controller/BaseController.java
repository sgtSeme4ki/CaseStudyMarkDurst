package base.controller;

import base.model.BaseEntity;
import base.service.BaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public abstract class BaseController<T extends BaseEntity> {

    private final BaseService<T> baseService;

    @PostMapping
    public T create(@RequestBody @Valid T t) {
        return baseService.create(t);
    }

    @GetMapping("{id}")
    public T findById(@PathVariable("id") Long id) {
        return baseService.findById(id);
    }

    @GetMapping
    public List<T> findAll() {
        return baseService.findAll();
    }

    @PutMapping("{id}")
    public T update(@PathVariable("id") Long id, @RequestBody @NonNull @Valid T t) {
        return baseService.update(id, t);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        baseService.delete(id);
    }
}
