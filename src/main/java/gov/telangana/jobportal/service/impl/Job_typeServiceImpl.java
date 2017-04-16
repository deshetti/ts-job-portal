package gov.telangana.jobportal.service.impl;

import gov.telangana.jobportal.service.Job_typeService;
import gov.telangana.jobportal.domain.Job_type;
import gov.telangana.jobportal.repository.Job_typeRepository;
import gov.telangana.jobportal.repository.search.Job_typeSearchRepository;
import gov.telangana.jobportal.service.dto.Job_typeDTO;
import gov.telangana.jobportal.service.mapper.Job_typeMapper;
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
 * Service Implementation for managing Job_type.
 */
@Service
@Transactional
public class Job_typeServiceImpl implements Job_typeService{

    private final Logger log = LoggerFactory.getLogger(Job_typeServiceImpl.class);
    
    private final Job_typeRepository job_typeRepository;

    private final Job_typeMapper job_typeMapper;

    private final Job_typeSearchRepository job_typeSearchRepository;

    public Job_typeServiceImpl(Job_typeRepository job_typeRepository, Job_typeMapper job_typeMapper, Job_typeSearchRepository job_typeSearchRepository) {
        this.job_typeRepository = job_typeRepository;
        this.job_typeMapper = job_typeMapper;
        this.job_typeSearchRepository = job_typeSearchRepository;
    }

    /**
     * Save a job_type.
     *
     * @param job_typeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Job_typeDTO save(Job_typeDTO job_typeDTO) {
        log.debug("Request to save Job_type : {}", job_typeDTO);
        Job_type job_type = job_typeMapper.job_typeDTOToJob_type(job_typeDTO);
        job_type = job_typeRepository.save(job_type);
        Job_typeDTO result = job_typeMapper.job_typeToJob_typeDTO(job_type);
        job_typeSearchRepository.save(job_type);
        return result;
    }

    /**
     *  Get all the job_types.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job_typeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Job_types");
        Page<Job_type> result = job_typeRepository.findAll(pageable);
        return result.map(job_type -> job_typeMapper.job_typeToJob_typeDTO(job_type));
    }

    /**
     *  Get one job_type by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Job_typeDTO findOne(Long id) {
        log.debug("Request to get Job_type : {}", id);
        Job_type job_type = job_typeRepository.findOne(id);
        Job_typeDTO job_typeDTO = job_typeMapper.job_typeToJob_typeDTO(job_type);
        return job_typeDTO;
    }

    /**
     *  Delete the  job_type by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Job_type : {}", id);
        job_typeRepository.delete(id);
        job_typeSearchRepository.delete(id);
    }

    /**
     * Search for the job_type corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job_typeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Job_types for query {}", query);
        Page<Job_type> result = job_typeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(job_type -> job_typeMapper.job_typeToJob_typeDTO(job_type));
    }
}
