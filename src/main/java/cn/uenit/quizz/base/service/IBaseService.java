package cn.uenit.quizz.base.service;


import cn.uenit.quizz.base.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author: SQJ
 * @data: 2018/4/7 15:24
 * @version:
 */
@NoRepositoryBean
public interface IBaseService<T extends BaseEntity<I>, I extends Serializable> {

    T saveEntity(T t);

    T saveOrUpdate(T t);

    T getById(I id);

    void deleteById(I id);
}
