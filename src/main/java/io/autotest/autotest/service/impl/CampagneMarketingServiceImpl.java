package io.autotest.autotest.service.impl;

import io.autotest.autotest.common.LaunchCampagne;
import io.autotest.autotest.common.MailMessage;
import io.autotest.autotest.dao.ICampagneMarketing;
import io.autotest.autotest.dao.IResultCollectionRepo;
import io.autotest.autotest.dao.ItestIterationDao;
import io.autotest.autotest.entities.CampagneMarketing;
import io.autotest.autotest.entities.ResultCollection;
import io.autotest.autotest.entities.TestIteration;
import io.autotest.autotest.security.services.JasperService;
import io.autotest.autotest.service.ICampagneMarketingService;
import io.autotest.autotest.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CampagneMarketingServiceImpl implements ICampagneMarketingService {

    private final ICampagneMarketing campagneRepo;
    private final IUserService userService;
    private final IResultCollectionRepo resultCollectionRepo;
    private final ItestIterationDao iterationDao;
    private final JasperService jasperService;
    @Value("${smtp.user}")
    private String smtpUser;

    @Override
    public String launchCampagne(LaunchCampagne launchCampagne) {
        try {
            HashMap<String, String> reportData = new HashMap<>();
            TestIteration iteration = iterationDao.findByIterationName(launchCampagne.getIterationName());
            CampagneMarketing campagneMarketing = campagneRepo.findById(launchCampagne.getCampagneId()).orElse(null);
            CampagneMarketing result = campagneRepo.fetchMarketingCampagne(launchCampagne.getCampagneId(), launchCampagne.getCampagneName());
            reportData.put("campagneName", campagneMarketing.getName());
            reportData.put("iterationName", iteration.getIterationName());
            reportData.put("campId", String.valueOf(launchCampagne.getCampagneId()));
            reportData.put("deliveryVersion", String.valueOf(iteration.getDeliveryversion()));
            reportData.put("deliveryDate", String.valueOf(iteration.getStartdeliverydate()));
            reportData.put("status", String.valueOf(iteration.getState()));
            reportData.put("testDuration", String.valueOf(iteration.getTestduration()));
            reportData.put("prepStatus", "Ok");
            reportData.put("execStatus", "Ok");
            reportData.put("backStatus", "Ok");

            ResultCollection resultCollection = resultCollectionRepo.findFirstByIdCampagne(launchCampagne.getCampagneId());
            reportData.put("rcStatus", resultCollection.getExecutionStatus());
            reportData.put("succSms", String.valueOf(resultCollection.getSucceededSmsNbr()));
            reportData.put("koSms", String.valueOf(resultCollection.getKoSmsNbr()));
            reportData.put("totalSms", String.valueOf(resultCollection.getKoSmsNbr() + resultCollection.getSucceededSmsNbr()));

            if (launchCampagne.isDownload()) {
                iteration.setState("Finished");
                iterationDao.save(iteration);
                return jasperService.generateBase64Pdf(reportData);
            } else {
                File reportBase64 = jasperService.generatePdf(reportData, "");

                MailMessage message = new MailMessage();
                message.setSender(smtpUser);
                String mailContent = "Please find attached a report for the test campaign <strong>" +
                        campagneMarketing.getName() + "</strong> <br>" +
                        "<strong>PS: This is an autogenerated email from the application server, please do not reply</strong>";
                message.setSubject("Rapport de test Campagne: ".concat(campagneMarketing.getName()));
                message.setContent(mailContent);
                message.setAttachment(reportBase64);
                userService.notifyUsersByMail(message);
                iteration.setState("Finished");
                iterationDao.save(iteration);
                return "true";
            }
        } catch (Exception e) {
            log.error("An error has been occurred when trying to launch campagne");
            e.printStackTrace();
        }
        return "false";
    }

    @Override
    public List<CampagneMarketing> getAll() {
        return campagneRepo.findAll();
    }

    @Override
    public void processFile(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            //skip header line
            reader.readLine();
            List<CampagneMarketing> campaigns = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                CampagneMarketing campaign = new CampagneMarketing();
                campaign.setId(Long.valueOf(values[0]));
                campaign.setName(values[1]);
                campaign.setDescription(values[2]);
                campaign.setDate_start(values[3]);
                campaign.setDate_end(values[4]);
                campaign.setCanal_type(values[5]);
                campaign.setExecution_type(values[6]);
                campaigns.add(campaign);
            }
            if (!campaigns.isEmpty()) {
                campagneRepo.saveAll(campaigns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        campagneRepo.deleteById(id);
    }
}
