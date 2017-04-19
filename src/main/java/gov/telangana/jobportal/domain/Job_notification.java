package gov.telangana.jobportal.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Job_notification.
 */
@Entity
@Table(name = "job_notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "job_notification")
public class Job_notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "position_title", length = 100, nullable = false)
    private String position_title;

    @NotNull
    @Column(name = "notification_date", nullable = false)
    private LocalDate notification_date;

    @NotNull
    @Size(max = 100)
    @Column(name = "job_location", length = 100, nullable = false)
    private String job_location;

    @NotNull
    @Column(name = "no_of_vacancies", nullable = false)
    private Integer no_of_vacancies;

    @Size(max = 100)
    @Column(name = "age_limit", length = 100)
    private String age_limit;

    @Size(max = 100)
    @Column(name = "education_limit", length = 100)
    private String education_limit;

    @Size(max = 100)
    @Column(name = "salary", length = 100)
    private String salary;

    @Size(max = 100)
    @Column(name = "reservation", length = 100)
    private String reservation;

    @Column(name = "application_deadline")
    private LocalDate application_deadline;

    @NotNull
    @Size(max = 100)
    @Column(name = "jhi_organization", length = 100, nullable = false)
    private String organization;

    @Size(max = 100)
    @Column(name = "duration", length = 100)
    private String duration;

    @Lob
    @Column(name = "notification_link")
    private String notification_link;

    @Lob
    @Column(name = "application_link")
    private String application_link;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @NotNull
    private Job_type job_type;

    @ManyToOne
    private Job_category job_category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition_title() {
        return position_title;
    }

    public Job_notification position_title(String position_title) {
        this.position_title = position_title;
        return this;
    }

    public void setPosition_title(String position_title) {
        this.position_title = position_title;
    }

    public LocalDate getNotification_date() {
        return notification_date;
    }

    public Job_notification notification_date(LocalDate notification_date) {
        this.notification_date = notification_date;
        return this;
    }

    public void setNotification_date(LocalDate notification_date) {
        this.notification_date = notification_date;
    }

    public String getJob_location() {
        return job_location;
    }

    public Job_notification job_location(String job_location) {
        this.job_location = job_location;
        return this;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public Integer getNo_of_vacancies() {
        return no_of_vacancies;
    }

    public Job_notification no_of_vacancies(Integer no_of_vacancies) {
        this.no_of_vacancies = no_of_vacancies;
        return this;
    }

    public void setNo_of_vacancies(Integer no_of_vacancies) {
        this.no_of_vacancies = no_of_vacancies;
    }

    public String getAge_limit() {
        return age_limit;
    }

    public Job_notification age_limit(String age_limit) {
        this.age_limit = age_limit;
        return this;
    }

    public void setAge_limit(String age_limit) {
        this.age_limit = age_limit;
    }

    public String getEducation_limit() {
        return education_limit;
    }

    public Job_notification education_limit(String education_limit) {
        this.education_limit = education_limit;
        return this;
    }

    public void setEducation_limit(String education_limit) {
        this.education_limit = education_limit;
    }

    public String getSalary() {
        return salary;
    }

    public Job_notification salary(String salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getReservation() {
        return reservation;
    }

    public Job_notification reservation(String reservation) {
        this.reservation = reservation;
        return this;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public LocalDate getApplication_deadline() {
        return application_deadline;
    }

    public Job_notification application_deadline(LocalDate application_deadline) {
        this.application_deadline = application_deadline;
        return this;
    }

    public void setApplication_deadline(LocalDate application_deadline) {
        this.application_deadline = application_deadline;
    }

    public String getOrganization() {
        return organization;
    }

    public Job_notification organization(String organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDuration() {
        return duration;
    }

    public Job_notification duration(String duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNotification_link() {
        return notification_link;
    }

    public Job_notification notification_link(String notification_link) {
        this.notification_link = notification_link;
        return this;
    }

    public void setNotification_link(String notification_link) {
        this.notification_link = notification_link;
    }

    public String getApplication_link() {
        return application_link;
    }

    public Job_notification application_link(String application_link) {
        this.application_link = application_link;
        return this;
    }

    public void setApplication_link(String application_link) {
        this.application_link = application_link;
    }

    public String getDescription() {
        return description;
    }

    public Job_notification description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job_type getJob_type() {
        return job_type;
    }

    public Job_notification job_type(Job_type job_type) {
        this.job_type = job_type;
        return this;
    }

    public void setJob_type(Job_type job_type) {
        this.job_type = job_type;
    }

    public Job_category getJob_category() {
        return job_category;
    }

    public Job_notification job_category(Job_category job_category) {
        this.job_category = job_category;
        return this;
    }

    public void setJob_category(Job_category job_category) {
        this.job_category = job_category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job_notification job_notification = (Job_notification) o;
        if (job_notification.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, job_notification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Job_notification{" +
            "id=" + id +
            ", position_title='" + position_title + "'" +
            ", notification_date='" + notification_date + "'" +
            ", job_location='" + job_location + "'" +
            ", no_of_vacancies='" + no_of_vacancies + "'" +
            ", age_limit='" + age_limit + "'" +
            ", education_limit='" + education_limit + "'" +
            ", salary='" + salary + "'" +
            ", reservation='" + reservation + "'" +
            ", application_deadline='" + application_deadline + "'" +
            ", organization='" + organization + "'" +
            ", duration='" + duration + "'" +
            ", notification_link='" + notification_link + "'" +
            ", application_link='" + application_link + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
