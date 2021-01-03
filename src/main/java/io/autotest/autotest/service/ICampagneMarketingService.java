package io.autotest.autotest.service;

import io.autotest.autotest.common.LaunchCampagne;
import io.autotest.autotest.entities.CampagneMarketing;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICampagneMarketingService {

    String launchCampagne(LaunchCampagne launchCampagne);

    List<CampagneMarketing> getAll();

    void processFile(MultipartFile file);

    void deleteById(Long id);
}
