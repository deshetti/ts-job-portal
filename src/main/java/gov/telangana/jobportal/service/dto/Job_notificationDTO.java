package gov.telangana.jobportal.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Job_notification entity.
 */
public class Job_notificationDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String position_title;

    @NotNull
    private LocalDate notification_date;

    @NotNull
    @Size(max = 100)
    private String job_location;

    @NotNull
    private Integer no_of_vacancies;

    @Size(max = 100)
    private String age_limit;

    @Size(max = 100)
    private String education_limit;

    @Size(max = 100)
    private String salary;

    @Size(max = 100)
    private String reservation;

    private LocalDate application_deadline;

    @NotNull
    @Size(max = 100)
    private String organization;

    @Size(max = 100)
    private String duration;

    @Lob
    private String notification_link;

    @Lob
    private String application_link;

    @NotNull
    @Lob
    private String description;

    private Long job_typeId;

    private String job_typeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPosition_title() {
        return position_title;
    }

    public void setPosition_title(String position_title) {
        this.position_title = position_title;
    }
    public LocalDate getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(LocalDate notification_date) {
        this.notification_date = notification_date;
    }
    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }
    public Integer getNo_of_vacancies() {
        return no_of_vacancies;
    }

    public void setNo_of_vacancies(Integer no_of_vacancies) {
        this.no_of_vacancies = no_of_vacancies;
    }
    public String getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(String age_limit) {
        this.age_limit = age_limit;
    }
    public String getEducation_limit() {
        return education_limit;
    }

    public void setEducation_limit(String education_limit) {
        this.education_limit = education_limit;
    }
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }
    public LocalDate getApplication_deadline() {
        return application_deadline;
    }

    public void setApplication_deadline(LocalDate application_deadline) {
        this.application_deadline = application_deadline;
    }
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getNotification_link() {
        return notification_link;
    }

    public void setNotification_link(String notification_link) {
        this.notification_link = notification_link;
    }
    public String getApplication_link() {
        return application_link;
    }

    public void setApplication_link(String application_link) {
        this.application_link = application_link;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getJob_typeId() {
        return job_typeId;
    }

    public void setJob_typeId(Long job_typeId) {
        this.job_typeId = job_typeId;
    }

    public String getJob_typeType() {
        return job_typeType;
    }

    public void setJob_typeType(String job_typeType) {
        this.job_typeType = job_typeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Job_notificationDTO job_notificationDTO = (Job_notificationDTO) o;

        if ( ! Objects.equals(id, job_notificationDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Job_notificationDTO{" +
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
