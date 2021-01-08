package io.autotest.autotest.dao;

import io.autotest.autotest.entities.PreparedCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPreparedCampaignDao extends JpaRepository<PreparedCampaign, String> {
}
