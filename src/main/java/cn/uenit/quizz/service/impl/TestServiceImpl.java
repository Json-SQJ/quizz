package cn.uenit.quizz.service.impl;

import cn.uenit.quizz.base.service.impl.BaseServiceImpl;
import cn.uenit.quizz.entity.TestEntity;
import cn.uenit.quizz.repository.TestRepository;
import cn.uenit.quizz.service.ITestService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl extends BaseServiceImpl<TestEntity,Long> implements ITestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<TestEntity> findAll(TestEntity testEntity) {
        Specification<TestEntity> specification = new Specification<TestEntity>() {
            @Override
            public Predicate toPredicate(Root<TestEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (!Strings.isNullOrEmpty(testEntity.getCreateUser())) {
                    predicate.add(cb.equal(root.get("createUser"), testEntity.getCreateUser()));
                }
                if (!Strings.isNullOrEmpty(testEntity.getQuizzName())) {
                    predicate.add(cb.like(root.get("quizzName"), "%"+testEntity.getQuizzName()+"%"));
                }
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return testRepository.findAllEntities(specification);
    }

    @Override
    public TestEntity getByTestId(long id) {
        TestEntity testEntity = testRepository.get(id);
        return testEntity;
    }
}
