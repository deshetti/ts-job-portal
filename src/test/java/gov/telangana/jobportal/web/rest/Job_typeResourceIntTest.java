package gov.telangana.jobportal.web.rest;

import gov.telangana.jobportal.JobportalApp;

import gov.telangana.jobportal.domain.Job_type;
import gov.telangana.jobportal.repository.Job_typeRepository;
import gov.telangana.jobportal.service.Job_typeService;
import gov.telangana.jobportal.repository.search.Job_typeSearchRepository;
import gov.telangana.jobportal.service.dto.Job_typeDTO;
import gov.telangana.jobportal.service.mapper.Job_typeMapper;
import gov.telangana.jobportal.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Job_typeResource REST controller.
 *
 * @see Job_typeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobportalApp.class)
public class Job_typeResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private Job_typeRepository job_typeRepository;

    @Autowired
    private Job_typeMapper job_typeMapper;

    @Autowired
    private Job_typeService job_typeService;

    @Autowired
    private Job_typeSearchRepository job_typeSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJob_typeMockMvc;

    private Job_type job_type;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Job_typeResource job_typeResource = new Job_typeResource(job_typeService);
        this.restJob_typeMockMvc = MockMvcBuilders.standaloneSetup(job_typeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Job_type createEntity(EntityManager em) {
        Job_type job_type = new Job_type()
            .type(DEFAULT_TYPE);
        return job_type;
    }

    @Before
    public void initTest() {
        job_typeSearchRepository.deleteAll();
        job_type = createEntity(em);
    }

    @Test
    @Transactional
    public void createJob_type() throws Exception {
        int databaseSizeBeforeCreate = job_typeRepository.findAll().size();

        // Create the Job_type
        Job_typeDTO job_typeDTO = job_typeMapper.job_typeToJob_typeDTO(job_type);
        restJob_typeMockMvc.perform(post("/api/job-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Job_type in the database
        List<Job_type> job_typeList = job_typeRepository.findAll();
        assertThat(job_typeList).hasSize(databaseSizeBeforeCreate + 1);
        Job_type testJob_type = job_typeList.get(job_typeList.size() - 1);
        assertThat(testJob_type.getType()).isEqualTo(DEFAULT_TYPE);

        // Validate the Job_type in Elasticsearch
        Job_type job_typeEs = job_typeSearchRepository.findOne(testJob_type.getId());
        assertThat(job_typeEs).isEqualToComparingFieldByField(testJob_type);
    }

    @Test
    @Transactional
    public void createJob_typeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = job_typeRepository.findAll().size();

        // Create the Job_type with an existing ID
        job_type.setId(1L);
        Job_typeDTO job_typeDTO = job_typeMapper.job_typeToJob_typeDTO(job_type);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJob_typeMockMvc.perform(post("/api/job-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_typeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Job_type> job_typeList = job_typeRepository.findAll();
        assertThat(job_typeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_typeRepository.findAll().size();
        // set the field null
        job_type.setType(null);

        // Create the Job_type, which fails.
        Job_typeDTO job_typeDTO = job_typeMapper.job_typeToJob_typeDTO(job_type);

        restJob_typeMockMvc.perform(post("/api/job-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_typeDTO)))
            .andExpect(status().isBadRequest());

        List<Job_type> job_typeList = job_typeRepository.findAll();
        assertThat(job_typeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJob_types() throws Exception {
        // Initialize the database
        job_typeRepository.saveAndFlush(job_type);

        // Get all the job_typeList
        restJob_typeMockMvc.perform(get("/api/job-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job_type.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getJob_type() throws Exception {
        // Initialize the database
        job_typeRepository.saveAndFlush(job_type);

        // Get the job_type
        restJob_typeMockMvc.perform(get("/api/job-types/{id}", job_type.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(job_type.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJob_type() throws Exception {
        // Get the job_type
        restJob_typeMockMvc.perform(get("/api/job-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJob_type() throws Exception {
        // Initialize the database
        job_typeRepository.saveAndFlush(job_type);
        job_typeSearchRepository.save(job_type);
        int databaseSizeBeforeUpdate = job_typeRepository.findAll().size();

        // Update the job_type
        Job_type updatedJob_type = job_typeRepository.findOne(job_type.getId());
        updatedJob_type
            .type(UPDATED_TYPE);
        Job_typeDTO job_typeDTO = job_typeMapper.job_typeToJob_typeDTO(updatedJob_type);

        restJob_typeMockMvc.perform(put("/api/job-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_typeDTO)))
            .andExpect(status().isOk());

        // Validate the Job_type in the database
        List<Job_type> job_typeList = job_typeRepository.findAll();
        assertThat(job_typeList).hasSize(databaseSizeBeforeUpdate);
        Job_type testJob_type = job_typeList.get(job_typeList.size() - 1);
        assertThat(testJob_type.getType()).isEqualTo(UPDATED_TYPE);

        // Validate the Job_type in Elasticsearch
        Job_type job_typeEs = job_typeSearchRepository.findOne(testJob_type.getId());
        assertThat(job_typeEs).isEqualToComparingFieldByField(testJob_type);
    }

    @Test
    @Transactional
    public void updateNonExistingJob_type() throws Exception {
        int databaseSizeBeforeUpdate = job_typeRepository.findAll().size();

        // Create the Job_type
        Job_typeDTO job_typeDTO = job_typeMapper.job_typeToJob_typeDTO(job_type);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJob_typeMockMvc.perform(put("/api/job-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Job_type in the database
        List<Job_type> job_typeList = job_typeRepository.findAll();
        assertThat(job_typeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJob_type() throws Exception {
        // Initialize the database
        job_typeRepository.saveAndFlush(job_type);
        job_typeSearchRepository.save(job_type);
        int databaseSizeBeforeDelete = job_typeRepository.findAll().size();

        // Get the job_type
        restJob_typeMockMvc.perform(delete("/api/job-types/{id}", job_type.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean job_typeExistsInEs = job_typeSearchRepository.exists(job_type.getId());
        assertThat(job_typeExistsInEs).isFalse();

        // Validate the database is empty
        List<Job_type> job_typeList = job_typeRepository.findAll();
        assertThat(job_typeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchJob_type() throws Exception {
        // Initialize the database
        job_typeRepository.saveAndFlush(job_type);
        job_typeSearchRepository.save(job_type);

        // Search the job_type
        restJob_typeMockMvc.perform(get("/api/_search/job-types?query=id:" + job_type.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job_type.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Job_type.class);
    }
}
