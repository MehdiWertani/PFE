package io.autotest.autotest.dao;

import io.autotest.autotest.entities.TestIteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@PreAuthorize("hasRole('USER') or hasRole('BILLINGMANAGER') or hasRole('ADMIN')")
@Repository
public interface ItestIterationDao extends JpaRepository<TestIteration,Long> {
    @PreAuthorize("hasRole('BILLINGMANAGER')")

    @Override
    <S extends TestIteration> S save(S s);

    TestIteration findByIterationName(String iterationName);
}
