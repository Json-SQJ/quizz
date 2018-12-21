package cn.uenit.quizz.base.service.impl;



import cn.uenit.quizz.base.entity.BaseEntity;
import cn.uenit.quizz.base.repository.IBaseRepository;
import cn.uenit.quizz.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author: SQJ
 * @data: 2018/4/7 15:26
 * @version:
 */
public class BaseServiceImpl<T extends BaseEntity<I>, I extends Serializable> implements IBaseService<T, I> {

    @Autowired
    private IBaseRepository<T,I> repository;

    @Override
    public T saveEntity(T t) {
        return repository.save(t);
    }

    @Override
    public T saveOrUpdate(T t) {
       return repository.saveOrUpdate(t);
    }

    @Override
    public T getById(I id) {
        return repository.get(id);
    }


    @Override
    public void deleteById(I id) {
         repository.delete(id);
    }

}
