package gov.telangana.jobportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.telangana.jobportal.domain.Job_notification;
import gov.telangana.jobportal.web.domain.CentralJobCount;
import gov.telangana.jobportal.web.domain.OtherJobCount;
import gov.telangana.jobportal.web.domain.StateJobCount;

/**
 * Spring Data JPA repository for the Job_notification entity.
 */
@SuppressWarnings("unused")
public interface Job_notificationRepository extends JpaRepository<Job_notification,Long> {

    @Query("SELECT NEW gov.telangana.jobportal.web.domain.CentralJobCount(sum(jn.no_of_vacancies)) " +
        "FROM Job_notification jn JOIN jn.job_type jt " +
        "WHERE jt.type='Central Government Job' ")
    public CentralJobCount retrieveCentralJobCount();


    @Query("SELECT NEW gov.telangana.jobportal.web.domain.StateJobCount(sum(jn.no_of_vacancies)) " +
        "FROM Job_notification jn JOIN jn.job_type jt " +
        "WHERE jt.type='State Government Job' ")
    public StateJobCount retrieveStateJobCount();


    @Query("SELECT NEW gov.telangana.jobportal.web.domain.OtherJobCount(sum(jn.no_of_vacancies)) " +
        "FROM Job_notification jn JOIN jn.job_type jt " +
        "WHERE jt.type='Other' ")
    public OtherJobCount retrieveOtherJobCount();

    @Query("SELECT jn FROM Job_notification jn JOIN jn.job_type jt WHERE jt.type = :type")
    public Page<Job_notification> findByJobType_Type(@Param("type") String type, Pageable pageable);

}
