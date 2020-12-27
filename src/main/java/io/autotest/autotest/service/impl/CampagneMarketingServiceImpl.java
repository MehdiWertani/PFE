package io.autotest.autotest.service.impl;

import io.autotest.autotest.common.LaunchCampagne;
import io.autotest.autotest.common.MailMessage;
import io.autotest.autotest.dao.ICampagneMarketing;
import io.autotest.autotest.dao.IResultCollectionRepo;
import io.autotest.autotest.entities.CampagneMarketing;
import io.autotest.autotest.entities.ResultCollection;
import io.autotest.autotest.service.ICampagneMarketingService;
import io.autotest.autotest.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CampagneMarketingServiceImpl implements ICampagneMarketingService {

    private final ICampagneMarketing campagneRepo;
    private final IUserService userService;
    private final IResultCollectionRepo resultCollectionRepo;
    @Value("${smtp.user}")
    private String smtpUser;

    @Override
    public boolean launchCampagne(LaunchCampagne launchCampagne) {
        try {
            CampagneMarketing campagneMarketing = campagneRepo.findById(launchCampagne.getCampagneId()).orElse(null);
            CampagneMarketing result = campagneRepo.fetchMarketingCampagne(launchCampagne.getCampagneId(), launchCampagne.getCampagneName());
            ResultCollection resultCollection = resultCollectionRepo.findFirstByIdCampagne(launchCampagne.getCampagneId());
            MailMessage message = new MailMessage();
            message.setSender(smtpUser);
            String mailContent = "<h3>Result collection : </h3> <br> <strong>Status : " +
                    resultCollection.getExecutionStatus() + "</strong> <br> " +
                    "<strong>Succeeded SMS: " + resultCollection.getSucceededSmsNbr() + "</strong> <br>" +
                    "<strong>Failed SMS: " + resultCollection.getKoSmsNbr() + "</strong>";
            message.setSubject("Rapport de test Campagne: ".concat(campagneMarketing.getName()));
            message.setContent(mailContent);
            userService.notifyUsersByMail(message);
            return true;
        } catch (Exception e) {
            log.error("An error has been occurred when trying to launch campagne");
            e.printStackTrace();
        }
        return false;
    }
}
