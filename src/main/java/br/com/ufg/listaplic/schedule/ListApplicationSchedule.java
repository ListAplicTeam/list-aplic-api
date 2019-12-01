package br.com.ufg.listaplic.schedule;

import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.service.ListApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListApplicationSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListApplicationSchedule.class);

    @Autowired
    private ListApplicationService listApplicationService;

    /**
     * Run every 1 minute
     */
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void verifyListApplicationStatus() {
        LOGGER.info("Verifying list application status");

        List<ListApplication> applications = listApplicationService.findAll();

        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Araguaina"));

        List<ListApplication> startedLists = applications.stream()
                .filter(listApplication ->
                                now.isAfter(listApplication.getStartDate())
                                && now.isBefore(listApplication.getFinalDate())
                                && !listApplication.getStatus().isEmAndamento())
                .map(listApplication -> {
                    listApplication.setStatus(ApplicationListStatus.EM_ANDAMENTO);
                    return listApplicationService.save(listApplication);
                })
                .collect(Collectors.toList());

        LOGGER.info("Starting lists: {} ", startedLists);

        List<ListApplication> finalizedLists = applications.stream()
                .filter(listApplication -> now.isAfter(listApplication.getFinalDate()) && !listApplication.getStatus().isEncerrada())
                .map(listApplication -> {
                    listApplication.setStatus(ApplicationListStatus.ENCERRADA);
                    return listApplicationService.save(listApplication);
                })
                .collect(Collectors.toList());

        LOGGER.info("Closing lists: {} ", finalizedLists);
    }

}
