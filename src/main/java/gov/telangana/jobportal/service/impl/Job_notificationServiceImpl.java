package gov.telangana.jobportal.service.impl;

import gov.telangana.jobportal.service.Job_notificationService;
import gov.telangana.jobportal.domain.Job_notification;
import gov.telangana.jobportal.repository.Job_notificationRepository;
import gov.telangana.jobportal.repository.search.Job_notificationSearchRepository;
import gov.telangana.jobportal.service.dto.Job_notificationDTO;
import gov.telangana.jobportal.service.mapper.Job_notificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Job_notification.
 */
@Service
@Transactional
public class Job_notificationServiceImpl implements Job_notificationService{

    private final Logger log = LoggerFactory.getLogger(Job_notificationServiceImpl.class);
    
    private final Job_notificationRepository job_notificationRepository;

    private final Job_notificationMapper job_notificationMapper;

    private final Job_notificationSearchRepository job_notificationSearchRepository;

    public Job_notificationServiceImpl(Job_notificationRepository job_notificationRepository, Job_notificationMapper job_notificationMapper, Job_notificationSearchRepository job_notificationSearchRepository) {
        this.job_notificationRepository = job_notificationRepository;
        this.job_notificationMapper = job_notificationMapper;
        this.job_notificationSearchRepository = job_notificationSearchRepository;
    }

    /**
     * Save a job_notification.
     *
     * @param job_notificationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Job_notificationDTO save(Job_notificationDTO job_notificationDTO) {
        log.debug("Request to save Job_notification : {}", job_notificationDTO);
        Job_notification job_notification = job_notificationMapper.job_notificationDTOToJob_notification(job_notificationDTO);
        job_notification = job_notificationRepository.save(job_notification);
        Job_notificationDTO result = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);
        job_notificationSearchRepository.save(job_notification);
        return result;
    }

    /**
     *  Get all the job_notifications.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job_notificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Job_notifications");
        Page<Job_notification> result = job_notificationRepository.findAll(pageable);
        return result.map(job_notification -> job_notificationMapper.job_notificationToJob_notificationDTO(job_notification));
    }

    /**
     *  Get one job_notification by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Job_notificationDTO findOne(Long id) {
        log.debug("Request to get Job_notification : {}", id);
        Job_notification job_notification = job_notificationRepository.findOne(id);
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);
        return job_notificationDTO;
    }

    /**
     *  Delete the  job_notification by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Job_notification : {}", id);
        job_notificationRepository.delete(id);
        job_notificationSearchRepository.delete(id);
    }

    /**
     * Search for the job_notification corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job_notificationDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Job_notifications for query {}", query);
        Page<Job_notification> result = job_notificationSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(job_notification -> job_notificationMapper.job_notificationToJob_notificationDTO(job_notification));
    }
}
