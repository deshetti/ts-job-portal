package gov.telangana.jobportal.service;

import gov.telangana.jobportal.service.dto.Job_notificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Job_notification.
 */
public interface Job_notificationService {

    /**
     * Save a job_notification.
     *
     * @param job_notificationDTO the entity to save
     * @return the persisted entity
     */
    Job_notificationDTO save(Job_notificationDTO job_notificationDTO);

    /**
     *  Get all the job_notifications.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Job_notificationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" job_notification.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Job_notificationDTO findOne(Long id);

    /**
     *  Delete the "id" job_notification.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the job_notification corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Job_notificationDTO> search(String query, Pageable pageable);
}
