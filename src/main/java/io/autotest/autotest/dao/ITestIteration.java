package io.autotest.autotest.dao;

import io.autotest.autotest.entities.TestIteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ITestIteration extends JpaRepository<TestIteration,Long> {
}
