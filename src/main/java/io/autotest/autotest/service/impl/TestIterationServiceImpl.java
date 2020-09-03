package io.autotest.autotest.service.impl;

import io.autotest.autotest.dao.ItestIterationDao;
import io.autotest.autotest.entities.TestIteration;
import io.autotest.autotest.service.ITestIterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestIterationServiceImpl implements ITestIterationService {
    @Autowired
    ItestIterationDao itestIterationDao;
    @Override
    public TestIteration save(TestIteration testIteration) {
        return (itestIterationDao.save(testIteration));
    }

    @Override
    public void delete(TestIteration testIteration) {
     itestIterationDao.delete(testIteration);
    }

    @Override
    public List<TestIteration> getAll() {
        return itestIterationDao.findAll();
    }
}
