package io.autotest.autotest.dao;

import io.autotest.autotest.entities.ERole;
import io.autotest.autotest.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@Repository
public interface IRoleDao extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
