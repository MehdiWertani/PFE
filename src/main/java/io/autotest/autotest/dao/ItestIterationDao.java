package io.autotest.autotest.dao;

import io.autotest.autotest.entities.TestIteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@PreAuthorize("hasRole('ROLE_COLLABORATOR') or hasRole('ROLE_DELIVERY_MANAGER') or hasRole('ROLE_ADMIN')")
@Repository
public interface ItestIterationDao extends JpaRepository<TestIteration, Long> {
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")

    @Override
    <S extends TestIteration> S save(S s);

    TestIteration findByIterationName(String iterationName);

    TestIteration findByCampagneid(String campId);
}
