package io.autotest.autotest.dao;

import io.autotest.autotest.entities.CampagneMarketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ICampagneMarketing extends JpaRepository<CampagneMarketing,Long> {
}
