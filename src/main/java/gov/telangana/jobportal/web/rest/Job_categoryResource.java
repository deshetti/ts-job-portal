package gov.telangana.jobportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import gov.telangana.jobportal.service.Job_categoryService;
import gov.telangana.jobportal.web.rest.util.HeaderUtil;
import gov.telangana.jobportal.web.rest.util.PaginationUtil;
import gov.telangana.jobportal.service.dto.Job_categoryDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Job_category.
 */
@RestController
@RequestMapping("/api")
public class Job_categoryResource {

    private final Logger log = LoggerFactory.getLogger(Job_categoryResource.class);

    private static final String ENTITY_NAME = "job_category";
        
    private final Job_categoryService job_categoryService;

    public Job_categoryResource(Job_categoryService job_categoryService) {
        this.job_categoryService = job_categoryService;
    }

    /**
     * POST  /job-categories : Create a new job_category.
     *
     * @param job_categoryDTO the job_categoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new job_categoryDTO, or with status 400 (Bad Request) if the job_category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-categories")
    @Timed
    public ResponseEntity<Job_categoryDTO> createJob_category(@Valid @RequestBody Job_categoryDTO job_categoryDTO) throws URISyntaxException {
        log.debug("REST request to save Job_category : {}", job_categoryDTO);
        if (job_categoryDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new job_category cannot already have an ID")).body(null);
        }
        Job_categoryDTO result = job_categoryService.save(job_categoryDTO);
        return ResponseEntity.created(new URI("/api/job-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-categories : Updates an existing job_category.
     *
     * @param job_categoryDTO the job_categoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated job_categoryDTO,
     * or with status 400 (Bad Request) if the job_categoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the job_categoryDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-categories")
    @Timed
    public ResponseEntity<Job_categoryDTO> updateJob_category(@Valid @RequestBody Job_categoryDTO job_categoryDTO) throws URISyntaxException {
        log.debug("REST request to update Job_category : {}", job_categoryDTO);
        if (job_categoryDTO.getId() == null) {
            return createJob_category(job_categoryDTO);
        }
        Job_categoryDTO result = job_categoryService.save(job_categoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, job_categoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-categories : get all the job_categories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of job_categories in body
     */
    @GetMapping("/job-categories")
    @Timed
    public ResponseEntity<List<Job_categoryDTO>> getAllJob_categories(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Job_categories");
        Page<Job_categoryDTO> page = job_categoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /job-categories/:id : get the "id" job_category.
     *
     * @param id the id of the job_categoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the job_categoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-categories/{id}")
    @Timed
    public ResponseEntity<Job_categoryDTO> getJob_category(@PathVariable Long id) {
        log.debug("REST request to get Job_category : {}", id);
        Job_categoryDTO job_categoryDTO = job_categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(job_categoryDTO));
    }

    /**
     * DELETE  /job-categories/:id : delete the "id" job_category.
     *
     * @param id the id of the job_categoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteJob_category(@PathVariable Long id) {
        log.debug("REST request to delete Job_category : {}", id);
        job_categoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/job-categories?query=:query : search for the job_category corresponding
     * to the query.
     *
     * @param query the query of the job_category search 
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/job-categories")
    @Timed
    public ResponseEntity<List<Job_categoryDTO>> searchJob_categories(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Job_categories for query {}", query);
        Page<Job_categoryDTO> page = job_categoryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/job-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
