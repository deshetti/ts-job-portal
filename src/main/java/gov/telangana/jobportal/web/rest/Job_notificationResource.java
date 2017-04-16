package gov.telangana.jobportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import gov.telangana.jobportal.service.Job_notificationService;
import gov.telangana.jobportal.web.rest.util.HeaderUtil;
import gov.telangana.jobportal.web.rest.util.PaginationUtil;
import gov.telangana.jobportal.service.dto.Job_notificationDTO;
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
 * REST controller for managing Job_notification.
 */
@RestController
@RequestMapping("/api")
public class Job_notificationResource {

    private final Logger log = LoggerFactory.getLogger(Job_notificationResource.class);

    private static final String ENTITY_NAME = "job_notification";
        
    private final Job_notificationService job_notificationService;

    public Job_notificationResource(Job_notificationService job_notificationService) {
        this.job_notificationService = job_notificationService;
    }

    /**
     * POST  /job-notifications : Create a new job_notification.
     *
     * @param job_notificationDTO the job_notificationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new job_notificationDTO, or with status 400 (Bad Request) if the job_notification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-notifications")
    @Timed
    public ResponseEntity<Job_notificationDTO> createJob_notification(@Valid @RequestBody Job_notificationDTO job_notificationDTO) throws URISyntaxException {
        log.debug("REST request to save Job_notification : {}", job_notificationDTO);
        if (job_notificationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new job_notification cannot already have an ID")).body(null);
        }
        Job_notificationDTO result = job_notificationService.save(job_notificationDTO);
        return ResponseEntity.created(new URI("/api/job-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-notifications : Updates an existing job_notification.
     *
     * @param job_notificationDTO the job_notificationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated job_notificationDTO,
     * or with status 400 (Bad Request) if the job_notificationDTO is not valid,
     * or with status 500 (Internal Server Error) if the job_notificationDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-notifications")
    @Timed
    public ResponseEntity<Job_notificationDTO> updateJob_notification(@Valid @RequestBody Job_notificationDTO job_notificationDTO) throws URISyntaxException {
        log.debug("REST request to update Job_notification : {}", job_notificationDTO);
        if (job_notificationDTO.getId() == null) {
            return createJob_notification(job_notificationDTO);
        }
        Job_notificationDTO result = job_notificationService.save(job_notificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, job_notificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-notifications : get all the job_notifications.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of job_notifications in body
     */
    @GetMapping("/job-notifications")
    @Timed
    public ResponseEntity<List<Job_notificationDTO>> getAllJob_notifications(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Job_notifications");
        Page<Job_notificationDTO> page = job_notificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-notifications");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /job-notifications/:id : get the "id" job_notification.
     *
     * @param id the id of the job_notificationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the job_notificationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-notifications/{id}")
    @Timed
    public ResponseEntity<Job_notificationDTO> getJob_notification(@PathVariable Long id) {
        log.debug("REST request to get Job_notification : {}", id);
        Job_notificationDTO job_notificationDTO = job_notificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(job_notificationDTO));
    }

    /**
     * DELETE  /job-notifications/:id : delete the "id" job_notification.
     *
     * @param id the id of the job_notificationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-notifications/{id}")
    @Timed
    public ResponseEntity<Void> deleteJob_notification(@PathVariable Long id) {
        log.debug("REST request to delete Job_notification : {}", id);
        job_notificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/job-notifications?query=:query : search for the job_notification corresponding
     * to the query.
     *
     * @param query the query of the job_notification search 
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/job-notifications")
    @Timed
    public ResponseEntity<List<Job_notificationDTO>> searchJob_notifications(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Job_notifications for query {}", query);
        Page<Job_notificationDTO> page = job_notificationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/job-notifications");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
