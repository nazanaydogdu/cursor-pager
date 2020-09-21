package com.kboxglobal.naydogdu.assignment.util;

import com.kboxglobal.naydogdu.assignment.entity.View;
import com.kboxglobal.naydogdu.assignment.entity.ViewArchive;
import com.kboxglobal.naydogdu.assignment.repository.ViewArchiveRepository;
import com.kboxglobal.naydogdu.assignment.repository.ViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArchivingJob {
    private static final Logger log = LoggerFactory.getLogger(ArchivingJob.class);

    private final ViewRepository viewRepository;

    private final ViewArchiveRepository viewArchiveRepository;

    public ArchivingJob(ViewRepository viewRepository, ViewArchiveRepository viewArchiveRepository) {
        this.viewRepository = viewRepository;
        this.viewArchiveRepository = viewArchiveRepository;
    }

    @Scheduled(fixedRate = 1 * 60 * 60 * 1000)//hourly
    public void archiveOldData() {
        List<View> oldViews = viewRepository.findAllByDateBefore(DateUtil.addDays(-30));
        if (oldViews != null && oldViews.size() > 0) {
            List<ViewArchive> viewArchiveList = new ArrayList<>();

            oldViews.stream().forEach(view ->
                    viewArchiveList.add(new ViewArchive(view.getViewer(), view.getViewee(), view.getDate())));

            if (viewArchiveList.size() == oldViews.size()) {
                viewArchiveRepository.saveAll(viewArchiveList);
                log.info("Moved " + viewArchiveList.size() + "records to View Archive table.");
                viewRepository.deleteAll(oldViews);
                log.info("Deleted " + oldViews.size() + "records from View table.");
            }
        }
    }
}
