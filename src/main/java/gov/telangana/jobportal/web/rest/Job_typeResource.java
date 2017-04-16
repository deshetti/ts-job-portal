package gov.telangana.jobportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import gov.telangana.jobportal.service.Job_typeService;
import gov.telangana.jobportal.web.rest.util.HeaderUtil;
import gov.telangana.jobportal.web.rest.util.PaginationUtil;
import gov.telangana.jobportal.service.dto.Job_typeDTO;
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
 * REST controller for managing Job_type.
 */
@RestController
@RequestMapping("/api")
public class Job_typeResource {

    private final Logger log = LoggerFactory.getLogger(Job_typeResource.class);

    private static final String ENTITY_NAME = "job_type";
        
    private final Job_typeService job_typeService;

    public Job_typeResource(Job_typeService job_typeService) {
        this.job_typeService = job_typeService;
    }

    /**
     * POST  /job-types : Create a new job_type.
     *
     * @param job_typeDTO the job_typeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new job_typeDTO, or with status 400 (Bad Request) if the job_type has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-types")
    @Timed
    public ResponseEntity<Job_typeDTO> createJob_type(@Valid @RequestBody Job_typeDTO job_typeDTO) throws URISyntaxException {
        log.debug("REST request to save Job_type : {}", job_typeDTO);
        if (job_typeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new job_type cannot already have an ID")).body(null);
        }
        Job_typeDTO result = job_typeService.save(job_typeDTO);
        return ResponseEntity.created(new URI("/api/job-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-types : Updates an existing job_type.
     *
     * @param job_typeDTO the job_typeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated job_typeDTO,
     * or with status 400 (Bad Request) if the job_typeDTO is not valid,
     * or with status 500 (Internal Server Error) if the job_typeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-types")
    @Timed
    public ResponseEntity<Job_typeDTO> updateJob_type(@Valid @RequestBody Job_typeDTO job_typeDTO) throws URISyntaxException {
        log.debug("REST request to update Job_type : {}", job_typeDTO);
        if (job_typeDTO.getId() == null) {
            return createJob_type(job_typeDTO);
        }
        Job_typeDTO result = job_typeService.save(job_typeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, job_typeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-types : get all the job_types.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of job_types in body
     */
    @GetMapping("/job-types")
    @Timed
    public ResponseEntity<List<Job_typeDTO>> getAllJob_types(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Job_types");
        Page<Job_typeDTO> page = job_typeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /job-types/:id : get the "id" job_type.
     *
     * @param id the id of the job_typeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the job_typeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-types/{id}")
    @Timed
    public ResponseEntity<Job_typeDTO> getJob_type(@PathVariable Long id) {
        log.debug("REST request to get Job_type : {}", id);
        Job_typeDTO job_typeDTO = job_typeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(job_typeDTO));
    }

    /**
     * DELETE  /job-types/:id : delete the "id" job_type.
     *
     * @param id the id of the job_typeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteJob_type(@PathVariable Long id) {
        log.debug("REST request to delete Job_type : {}", id);
        job_typeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/job-types?query=:query : search for the job_type corresponding
     * to the query.
     *
     * @param query the query of the job_type search 
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/job-types")
    @Timed
    public ResponseEntity<List<Job_typeDTO>> searchJob_types(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Job_types for query {}", query);
        Page<Job_typeDTO> page = job_typeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/job-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
