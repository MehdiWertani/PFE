package io.autotest.autotest.controller;

import io.autotest.autotest.common.LaunchCampagne;
import io.autotest.autotest.common.ResponseModel;
import io.autotest.autotest.dao.IResultCollectionRepo;
import io.autotest.autotest.entities.ResultCollection;
import io.autotest.autotest.service.ICampagneMarketingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campagne")
@RequiredArgsConstructor
public class CampagneController {

    private final ICampagneMarketingService campganeService;
    private final IResultCollectionRepo collectionRepo;

    @PreAuthorize("hasRole('ROLE_BILLINGMANAGER')")
    @PostMapping("/launch_campagne")
    public ResponseModel launchCampagne(@RequestBody LaunchCampagne launchCampagne) {
        return new ResponseModel(campganeService.launchCampagne(launchCampagne));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_BILLINGMANAGER','ROLE_ADMIN','ROLE_guest')")
    @GetMapping("/result_collection/{campagneId}")
    public ResultCollection getResultCollection(@PathVariable("campagneId") Long campagneId) {
        return collectionRepo.findFirstByIdCampagne(campagneId);
    }

}
