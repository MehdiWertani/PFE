package io.autotest.autotest.controller;

import io.autotest.autotest.common.ExecutionResponse;
import io.autotest.autotest.common.LaunchCampagne;
import io.autotest.autotest.common.ResponseModel;
import io.autotest.autotest.dao.IResultCollectionRepo;
import io.autotest.autotest.entities.CampagneMarketing;
import io.autotest.autotest.entities.ResultCollection;
import io.autotest.autotest.service.ICampagneMarketingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/campagne")
@RequiredArgsConstructor
public class CampagneController {

    private final ICampagneMarketingService campganeService;
    private final IResultCollectionRepo collectionRepo;

    @PostMapping("/prepare")
    public String prepareCampaign() throws IOException {
        return campganeService.prepareCampaign();
    }

    @PostMapping("/execute/{id}")
    public ExecutionResponse executeCampaign(@PathVariable("id") Long id) {
        return campganeService.executeCampaign(id);
    }

    @PostMapping("/launch/{id}")
    public void launchCampaign(@PathVariable("id") Long id,
                               @RequestParam("nbr") Long totalSmsNbr,
                               @RequestParam("name") String iterationName) {
        campganeService.launchCampaign(id, totalSmsNbr, iterationName);
    }

    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    @PostMapping("/launch_campagne")
    public ResponseModel launchCampagne(@RequestBody LaunchCampagne launchCampagne) {
        return new ResponseModel(campganeService.launchCampagne(launchCampagne));
    }

    @PreAuthorize("hasAnyRole('ROLE_COLLABORATOR','ROLE_DELIVERY_MANAGER','ROLE_ADMIN')")
    @GetMapping("/result_collection/{campagneId}")
    public ResultCollection getResultCollection(@PathVariable("campagneId") Long campagneId) {
        return collectionRepo.findFirstByIdCampagne(campagneId);
    }

    @PostMapping("/upload")
    public void uploadCampaignData(@RequestParam MultipartFile file) {
        campganeService.processFile(file);
    }

    @GetMapping("/all")
    public List<CampagneMarketing> getAll() {
        return campganeService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCampaign(@PathVariable("id") Long id) {
        campganeService.deleteById(id);
    }

}
