package gov.telangana.jobportal.service;

import gov.telangana.jobportal.service.dto.Job_typeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Job_type.
 */
public interface Job_typeService {

    /**
     * Save a job_type.
     *
     * @param job_typeDTO the entity to save
     * @return the persisted entity
     */
    Job_typeDTO save(Job_typeDTO job_typeDTO);

    /**
     *  Get all the job_types.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Job_typeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" job_type.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Job_typeDTO findOne(Long id);

    /**
     *  Delete the "id" job_type.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the job_type corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Job_typeDTO> search(String query, Pageable pageable);
}
