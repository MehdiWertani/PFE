package io.autotest.autotest.dao;

import io.autotest.autotest.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ITest extends JpaRepository<Test,Long> {
}
