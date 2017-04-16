package gov.telangana.jobportal.web.rest;

import gov.telangana.jobportal.JobportalApp;

import gov.telangana.jobportal.domain.Job_notification;
import gov.telangana.jobportal.domain.Job_type;
import gov.telangana.jobportal.repository.Job_notificationRepository;
import gov.telangana.jobportal.service.Job_notificationService;
import gov.telangana.jobportal.repository.search.Job_notificationSearchRepository;
import gov.telangana.jobportal.service.dto.Job_notificationDTO;
import gov.telangana.jobportal.service.mapper.Job_notificationMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Job_notificationResource REST controller.
 *
 * @see Job_notificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobportalApp.class)
public class Job_notificationResourceIntTest {

    private static final String DEFAULT_POSITION_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_POSITION_TITLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NOTIFICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NOTIFICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_JOB_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_JOB_LOCATION = "BBBBBBBBBB";

    private static final Long DEFAULT_POST_NAME = 1L;
    private static final Long UPDATED_POST_NAME = 2L;

    private static final Long DEFAULT_DESCRIPTION = 1L;
    private static final Long UPDATED_DESCRIPTION = 2L;

    private static final Integer DEFAULT_NO_OF_VACANCIES = 1;
    private static final Integer UPDATED_NO_OF_VACANCIES = 2;

    private static final String DEFAULT_AGE_LIMIT = "AAAAAAAAAA";
    private static final String UPDATED_AGE_LIMIT = "BBBBBBBBBB";

    private static final String DEFAULT_EDUCATION_LIMIT = "AAAAAAAAAA";
    private static final String UPDATED_EDUCATION_LIMIT = "BBBBBBBBBB";

    private static final String DEFAULT_SALARY = "AAAAAAAAAA";
    private static final String UPDATED_SALARY = "BBBBBBBBBB";

    private static final String DEFAULT_RESERVATION = "AAAAAAAAAA";
    private static final String UPDATED_RESERVATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPLICATION_DEADLINE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLICATION_DEADLINE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NOTIFICATION_LINK = 1L;
    private static final Long UPDATED_NOTIFICATION_LINK = 2L;

    private static final Long DEFAULT_APPLICATION_LINK = 1L;
    private static final Long UPDATED_APPLICATION_LINK = 2L;

    private static final String DEFAULT_ORGANIZATION = "AAAAAAAAAA";
    private static final String UPDATED_ORGANIZATION = "BBBBBBBBBB";

    @Autowired
    private Job_notificationRepository job_notificationRepository;

    @Autowired
    private Job_notificationMapper job_notificationMapper;

    @Autowired
    private Job_notificationService job_notificationService;

    @Autowired
    private Job_notificationSearchRepository job_notificationSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJob_notificationMockMvc;

    private Job_notification job_notification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Job_notificationResource job_notificationResource = new Job_notificationResource(job_notificationService);
        this.restJob_notificationMockMvc = MockMvcBuilders.standaloneSetup(job_notificationResource)
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
    public static Job_notification createEntity(EntityManager em) {
        Job_notification job_notification = new Job_notification()
            .position_title(DEFAULT_POSITION_TITLE)
            .notification_date(DEFAULT_NOTIFICATION_DATE)
            .job_location(DEFAULT_JOB_LOCATION)
            .post_name(DEFAULT_POST_NAME)
            .description(DEFAULT_DESCRIPTION)
            .no_of_vacancies(DEFAULT_NO_OF_VACANCIES)
            .age_limit(DEFAULT_AGE_LIMIT)
            .education_limit(DEFAULT_EDUCATION_LIMIT)
            .salary(DEFAULT_SALARY)
            .reservation(DEFAULT_RESERVATION)
            .application_deadline(DEFAULT_APPLICATION_DEADLINE)
            .notification_link(DEFAULT_NOTIFICATION_LINK)
            .application_link(DEFAULT_APPLICATION_LINK)
            .organization(DEFAULT_ORGANIZATION);
        // Add required entity
        Job_type job_type = Job_typeResourceIntTest.createEntity(em);
        em.persist(job_type);
        em.flush();
        job_notification.setJob_type(job_type);
        return job_notification;
    }

    @Before
    public void initTest() {
        job_notificationSearchRepository.deleteAll();
        job_notification = createEntity(em);
    }

    @Test
    @Transactional
    public void createJob_notification() throws Exception {
        int databaseSizeBeforeCreate = job_notificationRepository.findAll().size();

        // Create the Job_notification
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);
        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Job_notification in the database
        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeCreate + 1);
        Job_notification testJob_notification = job_notificationList.get(job_notificationList.size() - 1);
        assertThat(testJob_notification.getPosition_title()).isEqualTo(DEFAULT_POSITION_TITLE);
        assertThat(testJob_notification.getNotification_date()).isEqualTo(DEFAULT_NOTIFICATION_DATE);
        assertThat(testJob_notification.getJob_location()).isEqualTo(DEFAULT_JOB_LOCATION);
        assertThat(testJob_notification.getPost_name()).isEqualTo(DEFAULT_POST_NAME);
        assertThat(testJob_notification.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testJob_notification.getNo_of_vacancies()).isEqualTo(DEFAULT_NO_OF_VACANCIES);
        assertThat(testJob_notification.getAge_limit()).isEqualTo(DEFAULT_AGE_LIMIT);
        assertThat(testJob_notification.getEducation_limit()).isEqualTo(DEFAULT_EDUCATION_LIMIT);
        assertThat(testJob_notification.getSalary()).isEqualTo(DEFAULT_SALARY);
        assertThat(testJob_notification.getReservation()).isEqualTo(DEFAULT_RESERVATION);
        assertThat(testJob_notification.getApplication_deadline()).isEqualTo(DEFAULT_APPLICATION_DEADLINE);
        assertThat(testJob_notification.getNotification_link()).isEqualTo(DEFAULT_NOTIFICATION_LINK);
        assertThat(testJob_notification.getApplication_link()).isEqualTo(DEFAULT_APPLICATION_LINK);
        assertThat(testJob_notification.getOrganization()).isEqualTo(DEFAULT_ORGANIZATION);

        // Validate the Job_notification in Elasticsearch
        Job_notification job_notificationEs = job_notificationSearchRepository.findOne(testJob_notification.getId());
        assertThat(job_notificationEs).isEqualToComparingFieldByField(testJob_notification);
    }

    @Test
    @Transactional
    public void createJob_notificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = job_notificationRepository.findAll().size();

        // Create the Job_notification with an existing ID
        job_notification.setId(1L);
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPosition_titleIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_notificationRepository.findAll().size();
        // set the field null
        job_notification.setPosition_title(null);

        // Create the Job_notification, which fails.
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotification_dateIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_notificationRepository.findAll().size();
        // set the field null
        job_notification.setNotification_date(null);

        // Create the Job_notification, which fails.
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJob_locationIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_notificationRepository.findAll().size();
        // set the field null
        job_notification.setJob_location(null);

        // Create the Job_notification, which fails.
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_notificationRepository.findAll().size();
        // set the field null
        job_notification.setDescription(null);

        // Create the Job_notification, which fails.
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNo_of_vacanciesIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_notificationRepository.findAll().size();
        // set the field null
        job_notification.setNo_of_vacancies(null);

        // Create the Job_notification, which fails.
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrganizationIsRequired() throws Exception {
        int databaseSizeBeforeTest = job_notificationRepository.findAll().size();
        // set the field null
        job_notification.setOrganization(null);

        // Create the Job_notification, which fails.
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        restJob_notificationMockMvc.perform(post("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJob_notifications() throws Exception {
        // Initialize the database
        job_notificationRepository.saveAndFlush(job_notification);

        // Get all the job_notificationList
        restJob_notificationMockMvc.perform(get("/api/job-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job_notification.getId().intValue())))
            .andExpect(jsonPath("$.[*].position_title").value(hasItem(DEFAULT_POSITION_TITLE.toString())))
            .andExpect(jsonPath("$.[*].notification_date").value(hasItem(DEFAULT_NOTIFICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].job_location").value(hasItem(DEFAULT_JOB_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].post_name").value(hasItem(DEFAULT_POST_NAME.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.intValue())))
            .andExpect(jsonPath("$.[*].no_of_vacancies").value(hasItem(DEFAULT_NO_OF_VACANCIES)))
            .andExpect(jsonPath("$.[*].age_limit").value(hasItem(DEFAULT_AGE_LIMIT.toString())))
            .andExpect(jsonPath("$.[*].education_limit").value(hasItem(DEFAULT_EDUCATION_LIMIT.toString())))
            .andExpect(jsonPath("$.[*].salary").value(hasItem(DEFAULT_SALARY.toString())))
            .andExpect(jsonPath("$.[*].reservation").value(hasItem(DEFAULT_RESERVATION.toString())))
            .andExpect(jsonPath("$.[*].application_deadline").value(hasItem(DEFAULT_APPLICATION_DEADLINE.toString())))
            .andExpect(jsonPath("$.[*].notification_link").value(hasItem(DEFAULT_NOTIFICATION_LINK.intValue())))
            .andExpect(jsonPath("$.[*].application_link").value(hasItem(DEFAULT_APPLICATION_LINK.intValue())))
            .andExpect(jsonPath("$.[*].organization").value(hasItem(DEFAULT_ORGANIZATION.toString())));
    }

    @Test
    @Transactional
    public void getJob_notification() throws Exception {
        // Initialize the database
        job_notificationRepository.saveAndFlush(job_notification);

        // Get the job_notification
        restJob_notificationMockMvc.perform(get("/api/job-notifications/{id}", job_notification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(job_notification.getId().intValue()))
            .andExpect(jsonPath("$.position_title").value(DEFAULT_POSITION_TITLE.toString()))
            .andExpect(jsonPath("$.notification_date").value(DEFAULT_NOTIFICATION_DATE.toString()))
            .andExpect(jsonPath("$.job_location").value(DEFAULT_JOB_LOCATION.toString()))
            .andExpect(jsonPath("$.post_name").value(DEFAULT_POST_NAME.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.intValue()))
            .andExpect(jsonPath("$.no_of_vacancies").value(DEFAULT_NO_OF_VACANCIES))
            .andExpect(jsonPath("$.age_limit").value(DEFAULT_AGE_LIMIT.toString()))
            .andExpect(jsonPath("$.education_limit").value(DEFAULT_EDUCATION_LIMIT.toString()))
            .andExpect(jsonPath("$.salary").value(DEFAULT_SALARY.toString()))
            .andExpect(jsonPath("$.reservation").value(DEFAULT_RESERVATION.toString()))
            .andExpect(jsonPath("$.application_deadline").value(DEFAULT_APPLICATION_DEADLINE.toString()))
            .andExpect(jsonPath("$.notification_link").value(DEFAULT_NOTIFICATION_LINK.intValue()))
            .andExpect(jsonPath("$.application_link").value(DEFAULT_APPLICATION_LINK.intValue()))
            .andExpect(jsonPath("$.organization").value(DEFAULT_ORGANIZATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJob_notification() throws Exception {
        // Get the job_notification
        restJob_notificationMockMvc.perform(get("/api/job-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJob_notification() throws Exception {
        // Initialize the database
        job_notificationRepository.saveAndFlush(job_notification);
        job_notificationSearchRepository.save(job_notification);
        int databaseSizeBeforeUpdate = job_notificationRepository.findAll().size();

        // Update the job_notification
        Job_notification updatedJob_notification = job_notificationRepository.findOne(job_notification.getId());
        updatedJob_notification
            .position_title(UPDATED_POSITION_TITLE)
            .notification_date(UPDATED_NOTIFICATION_DATE)
            .job_location(UPDATED_JOB_LOCATION)
            .post_name(UPDATED_POST_NAME)
            .description(UPDATED_DESCRIPTION)
            .no_of_vacancies(UPDATED_NO_OF_VACANCIES)
            .age_limit(UPDATED_AGE_LIMIT)
            .education_limit(UPDATED_EDUCATION_LIMIT)
            .salary(UPDATED_SALARY)
            .reservation(UPDATED_RESERVATION)
            .application_deadline(UPDATED_APPLICATION_DEADLINE)
            .notification_link(UPDATED_NOTIFICATION_LINK)
            .application_link(UPDATED_APPLICATION_LINK)
            .organization(UPDATED_ORGANIZATION);
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(updatedJob_notification);

        restJob_notificationMockMvc.perform(put("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isOk());

        // Validate the Job_notification in the database
        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeUpdate);
        Job_notification testJob_notification = job_notificationList.get(job_notificationList.size() - 1);
        assertThat(testJob_notification.getPosition_title()).isEqualTo(UPDATED_POSITION_TITLE);
        assertThat(testJob_notification.getNotification_date()).isEqualTo(UPDATED_NOTIFICATION_DATE);
        assertThat(testJob_notification.getJob_location()).isEqualTo(UPDATED_JOB_LOCATION);
        assertThat(testJob_notification.getPost_name()).isEqualTo(UPDATED_POST_NAME);
        assertThat(testJob_notification.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testJob_notification.getNo_of_vacancies()).isEqualTo(UPDATED_NO_OF_VACANCIES);
        assertThat(testJob_notification.getAge_limit()).isEqualTo(UPDATED_AGE_LIMIT);
        assertThat(testJob_notification.getEducation_limit()).isEqualTo(UPDATED_EDUCATION_LIMIT);
        assertThat(testJob_notification.getSalary()).isEqualTo(UPDATED_SALARY);
        assertThat(testJob_notification.getReservation()).isEqualTo(UPDATED_RESERVATION);
        assertThat(testJob_notification.getApplication_deadline()).isEqualTo(UPDATED_APPLICATION_DEADLINE);
        assertThat(testJob_notification.getNotification_link()).isEqualTo(UPDATED_NOTIFICATION_LINK);
        assertThat(testJob_notification.getApplication_link()).isEqualTo(UPDATED_APPLICATION_LINK);
        assertThat(testJob_notification.getOrganization()).isEqualTo(UPDATED_ORGANIZATION);

        // Validate the Job_notification in Elasticsearch
        Job_notification job_notificationEs = job_notificationSearchRepository.findOne(testJob_notification.getId());
        assertThat(job_notificationEs).isEqualToComparingFieldByField(testJob_notification);
    }

    @Test
    @Transactional
    public void updateNonExistingJob_notification() throws Exception {
        int databaseSizeBeforeUpdate = job_notificationRepository.findAll().size();

        // Create the Job_notification
        Job_notificationDTO job_notificationDTO = job_notificationMapper.job_notificationToJob_notificationDTO(job_notification);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJob_notificationMockMvc.perform(put("/api/job-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job_notificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Job_notification in the database
        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJob_notification() throws Exception {
        // Initialize the database
        job_notificationRepository.saveAndFlush(job_notification);
        job_notificationSearchRepository.save(job_notification);
        int databaseSizeBeforeDelete = job_notificationRepository.findAll().size();

        // Get the job_notification
        restJob_notificationMockMvc.perform(delete("/api/job-notifications/{id}", job_notification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean job_notificationExistsInEs = job_notificationSearchRepository.exists(job_notification.getId());
        assertThat(job_notificationExistsInEs).isFalse();

        // Validate the database is empty
        List<Job_notification> job_notificationList = job_notificationRepository.findAll();
        assertThat(job_notificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchJob_notification() throws Exception {
        // Initialize the database
        job_notificationRepository.saveAndFlush(job_notification);
        job_notificationSearchRepository.save(job_notification);

        // Search the job_notification
        restJob_notificationMockMvc.perform(get("/api/_search/job-notifications?query=id:" + job_notification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job_notification.getId().intValue())))
            .andExpect(jsonPath("$.[*].position_title").value(hasItem(DEFAULT_POSITION_TITLE.toString())))
            .andExpect(jsonPath("$.[*].notification_date").value(hasItem(DEFAULT_NOTIFICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].job_location").value(hasItem(DEFAULT_JOB_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].post_name").value(hasItem(DEFAULT_POST_NAME.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.intValue())))
            .andExpect(jsonPath("$.[*].no_of_vacancies").value(hasItem(DEFAULT_NO_OF_VACANCIES)))
            .andExpect(jsonPath("$.[*].age_limit").value(hasItem(DEFAULT_AGE_LIMIT.toString())))
            .andExpect(jsonPath("$.[*].education_limit").value(hasItem(DEFAULT_EDUCATION_LIMIT.toString())))
            .andExpect(jsonPath("$.[*].salary").value(hasItem(DEFAULT_SALARY.toString())))
            .andExpect(jsonPath("$.[*].reservation").value(hasItem(DEFAULT_RESERVATION.toString())))
            .andExpect(jsonPath("$.[*].application_deadline").value(hasItem(DEFAULT_APPLICATION_DEADLINE.toString())))
            .andExpect(jsonPath("$.[*].notification_link").value(hasItem(DEFAULT_NOTIFICATION_LINK.intValue())))
            .andExpect(jsonPath("$.[*].application_link").value(hasItem(DEFAULT_APPLICATION_LINK.intValue())))
            .andExpect(jsonPath("$.[*].organization").value(hasItem(DEFAULT_ORGANIZATION.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Job_notification.class);
    }
}
