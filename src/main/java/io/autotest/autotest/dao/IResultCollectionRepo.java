package io.autotest.autotest.dao;

import io.autotest.autotest.entities.ResultCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResultCollectionRepo extends JpaRepository<ResultCollection, Long> {

    ResultCollection findFirstByIdCampagne(Long idCampagne);
}
