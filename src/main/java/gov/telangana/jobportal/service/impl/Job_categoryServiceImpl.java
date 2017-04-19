package gov.telangana.jobportal.service.impl;

import gov.telangana.jobportal.service.Job_categoryService;
import gov.telangana.jobportal.domain.Job_category;
import gov.telangana.jobportal.repository.Job_categoryRepository;
import gov.telangana.jobportal.repository.search.Job_categorySearchRepository;
import gov.telangana.jobportal.service.dto.Job_categoryDTO;
import gov.telangana.jobportal.service.mapper.Job_categoryMapper;
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
 * Service Implementation for managing Job_category.
 */
@Service
@Transactional
public class Job_categoryServiceImpl implements Job_categoryService{

    private final Logger log = LoggerFactory.getLogger(Job_categoryServiceImpl.class);
    
    private final Job_categoryRepository job_categoryRepository;

    private final Job_categoryMapper job_categoryMapper;

    private final Job_categorySearchRepository job_categorySearchRepository;

    public Job_categoryServiceImpl(Job_categoryRepository job_categoryRepository, Job_categoryMapper job_categoryMapper, Job_categorySearchRepository job_categorySearchRepository) {
        this.job_categoryRepository = job_categoryRepository;
        this.job_categoryMapper = job_categoryMapper;
        this.job_categorySearchRepository = job_categorySearchRepository;
    }

    /**
     * Save a job_category.
     *
     * @param job_categoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Job_categoryDTO save(Job_categoryDTO job_categoryDTO) {
        log.debug("Request to save Job_category : {}", job_categoryDTO);
        Job_category job_category = job_categoryMapper.job_categoryDTOToJob_category(job_categoryDTO);
        job_category = job_categoryRepository.save(job_category);
        Job_categoryDTO result = job_categoryMapper.job_categoryToJob_categoryDTO(job_category);
        job_categorySearchRepository.save(job_category);
        return result;
    }

    /**
     *  Get all the job_categories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job_categoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Job_categories");
        Page<Job_category> result = job_categoryRepository.findAll(pageable);
        return result.map(job_category -> job_categoryMapper.job_categoryToJob_categoryDTO(job_category));
    }

    /**
     *  Get one job_category by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Job_categoryDTO findOne(Long id) {
        log.debug("Request to get Job_category : {}", id);
        Job_category job_category = job_categoryRepository.findOne(id);
        Job_categoryDTO job_categoryDTO = job_categoryMapper.job_categoryToJob_categoryDTO(job_category);
        return job_categoryDTO;
    }

    /**
     *  Delete the  job_category by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Job_category : {}", id);
        job_categoryRepository.delete(id);
        job_categorySearchRepository.delete(id);
    }

    /**
     * Search for the job_category corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job_categoryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Job_categories for query {}", query);
        Page<Job_category> result = job_categorySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(job_category -> job_categoryMapper.job_categoryToJob_categoryDTO(job_category));
    }
}
