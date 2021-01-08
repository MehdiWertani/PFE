package io.autotest.autotest.dao;

import io.autotest.autotest.entities.CampagneMarketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICampagneMarketing extends JpaRepository<CampagneMarketing, Long> {

    @Transactional
    @Query(nativeQuery = true, value = "call launch_campagne(:campagneId, :campagneName, :succSms, :koSms)")
    CampagneMarketing fetchMarketingCampagne(@Param("campagneId") Long id,
                                             @Param("campagneName") String campagneName,
                                             @Param("succSms") Long succSms,
                                             @Param("koSms") Long koSms);
}
