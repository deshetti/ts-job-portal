package gov.telangana.jobportal.service;

import gov.telangana.jobportal.service.dto.Job_categoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Job_category.
 */
public interface Job_categoryService {

    /**
     * Save a job_category.
     *
     * @param job_categoryDTO the entity to save
     * @return the persisted entity
     */
    Job_categoryDTO save(Job_categoryDTO job_categoryDTO);

    /**
     *  Get all the job_categories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Job_categoryDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" job_category.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Job_categoryDTO findOne(Long id);

    /**
     *  Delete the "id" job_category.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the job_category corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Job_categoryDTO> search(String query, Pageable pageable);
}
