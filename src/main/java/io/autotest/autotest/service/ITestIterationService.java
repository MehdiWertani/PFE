package io.autotest.autotest.service;

import io.autotest.autotest.entities.TestIteration;

import java.util.List;

public interface ITestIterationService {
   TestIteration save(TestIteration testIteration);
   void delete(TestIteration testIteration);
 List<TestIteration> getAll();
}
