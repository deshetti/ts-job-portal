package gov.telangana.jobportal.web.rest;

import gov.telangana.jobportal.JobportalApp;

import gov.telangana.jobportal.domain.Job_category;
import gov.telangana.jobportal.repository.Job_categoryRepository;
import gov.telangana.jobportal.service.Job_categoryService;
import gov.telangana.jobportal.repository.search.Job_categorySearchRepository;
import gov.telangana.jobportal.service.dto.Job_categoryDTO;
import gov.telangana.jobportal.service.mapper.Job_categoryMapper;
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
 * Test class for the Job_categoryResource REST controller.
 *
 * @see Job_categoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobportalApp.class)
public class Job_categoryResourceIntTest {

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_ICON_URL = "AAAAAAAAAA";
    private static final String UPDATED_ICON_URL = "BBBBBBBBBB";

    @Autowired
    private Job_categoryRepository job_categoryRepository;

    @Autowired
    private Job_categoryMapper job_categoryMapper;

    @Autowired
    private Job_categoryService job_categoryService;

    @Autowired
    private Job_categorySearchRepository job_categorySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJob_categoryMockMvc;

    private Job_category job_category;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Job_categoryResource job_categoryResource = new Job_categoryResource(job_categoryService);
        this.restJob_categoryMockMvc = MockMvcBuilders.standaloneSetup(job_categoryResource)
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
    public static Job_category createEntity(EntityManager em) {
        Job_category job_category = new Job_category()
            .category(DEFAULT_CATEGORY)
            .icon_url(DEFAULT_ICON_URL);
        return job_category;
    }

    @Before
    public void initTest() {
        job_categorySearchRepository.deleteAll();
        job_category = createEntity(em);
    }

    @Test
    @Transactional
    public void createJob_category() throws Exception {
        int databaseSizeBeforeCreate = job_categoryRepository.findAll().size();

        // Create the Job_category
        Job_categoryDTO job_categoryDTO = job_categoryMapper.job_categoryToJob_categoryDTO(job_category);
        restJob_categoryMockMvc.perform(post("/api/job-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_categoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Job_category in the database
        List<Job_category> job_categoryList = job_categoryRepository.findAll();
        assertThat(job_categoryList).hasSize(databaseSizeBeforeCreate + 1);
        Job_category testJob_category = job_categoryList.get(job_categoryList.size() - 1);
        assertThat(testJob_category.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testJob_category.getIcon_url()).isEqualTo(DEFAULT_ICON_URL);

        // Validate the Job_category in Elasticsearch
        Job_category job_categoryEs = job_categorySearchRepository.findOne(testJob_category.getId());
        assertThat(job_categoryEs).isEqualToComparingFieldByField(testJob_category);
    }

    @Test
    @Transactional
    public void createJob_categoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = job_categoryRepository.findAll().size();

        // Create the Job_category with an existing ID
        job_category.setId(1L);
        Job_categoryDTO job_categoryDTO = job_categoryMapper.job_categoryToJob_categoryDTO(job_category);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJob_categoryMockMvc.perform(post("/api/job-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_categoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Job_category> job_categoryList = job_categoryRepository.findAll();
        assertThat(job_categoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_categoryRepository.findAll().size();
        // set the field null
        job_category.setCategory(null);

        // Create the Job_category, which fails.
        Job_categoryDTO job_categoryDTO = job_categoryMapper.job_categoryToJob_categoryDTO(job_category);

        restJob_categoryMockMvc.perform(post("/api/job-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_categoryDTO)))
            .andExpect(status().isBadRequest());

        List<Job_category> job_categoryList = job_categoryRepository.findAll();
        assertThat(job_categoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJob_categories() throws Exception {
        // Initialize the database
        job_categoryRepository.saveAndFlush(job_category);

        // Get all the job_categoryList
        restJob_categoryMockMvc.perform(get("/api/job-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job_category.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].icon_url").value(hasItem(DEFAULT_ICON_URL.toString())));
    }

    @Test
    @Transactional
    public void getJob_category() throws Exception {
        // Initialize the database
        job_categoryRepository.saveAndFlush(job_category);

        // Get the job_category
        restJob_categoryMockMvc.perform(get("/api/job-categories/{id}", job_category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(job_category.getId().intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.icon_url").value(DEFAULT_ICON_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJob_category() throws Exception {
        // Get the job_category
        restJob_categoryMockMvc.perform(get("/api/job-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJob_category() throws Exception {
        // Initialize the database
        job_categoryRepository.saveAndFlush(job_category);
        job_categorySearchRepository.save(job_category);
        int databaseSizeBeforeUpdate = job_categoryRepository.findAll().size();

        // Update the job_category
        Job_category updatedJob_category = job_categoryRepository.findOne(job_category.getId());
        updatedJob_category
            .category(UPDATED_CATEGORY)
            .icon_url(UPDATED_ICON_URL);
        Job_categoryDTO job_categoryDTO = job_categoryMapper.job_categoryToJob_categoryDTO(updatedJob_category);

        restJob_categoryMockMvc.perform(put("/api/job-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_categoryDTO)))
            .andExpect(status().isOk());

        // Validate the Job_category in the database
        List<Job_category> job_categoryList = job_categoryRepository.findAll();
        assertThat(job_categoryList).hasSize(databaseSizeBeforeUpdate);
        Job_category testJob_category = job_categoryList.get(job_categoryList.size() - 1);
        assertThat(testJob_category.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testJob_category.getIcon_url()).isEqualTo(UPDATED_ICON_URL);

        // Validate the Job_category in Elasticsearch
        Job_category job_categoryEs = job_categorySearchRepository.findOne(testJob_category.getId());
        assertThat(job_categoryEs).isEqualToComparingFieldByField(testJob_category);
    }

    @Test
    @Transactional
    public void updateNonExistingJob_category() throws Exception {
        int databaseSizeBeforeUpdate = job_categoryRepository.findAll().size();

        // Create the Job_category
        Job_categoryDTO job_categoryDTO = job_categoryMapper.job_categoryToJob_categoryDTO(job_category);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJob_categoryMockMvc.perform(put("/api/job-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_categoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Job_category in the database
        List<Job_category> job_categoryList = job_categoryRepository.findAll();
        assertThat(job_categoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJob_category() throws Exception {
        // Initialize the database
        job_categoryRepository.saveAndFlush(job_category);
        job_categorySearchRepository.save(job_category);
        int databaseSizeBeforeDelete = job_categoryRepository.findAll().size();

        // Get the job_category
        restJob_categoryMockMvc.perform(delete("/api/job-categories/{id}", job_category.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean job_categoryExistsInEs = job_categorySearchRepository.exists(job_category.getId());
        assertThat(job_categoryExistsInEs).isFalse();

        // Validate the database is empty
        List<Job_category> job_categoryList = job_categoryRepository.findAll();
        assertThat(job_categoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchJob_category() throws Exception {
        // Initialize the database
        job_categoryRepository.saveAndFlush(job_category);
        job_categorySearchRepository.save(job_category);

        // Search the job_category
        restJob_categoryMockMvc.perform(get("/api/_search/job-categories?query=id:" + job_category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job_category.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].icon_url").value(hasItem(DEFAULT_ICON_URL.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Job_category.class);
    }
}
