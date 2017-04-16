package gov.telangana.jobportal.repository;

import gov.telangana.jobportal.domain.Job_notification;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Job_notification entity.
 */
@SuppressWarnings("unused")
public interface Job_notificationRepository extends JpaRepository<Job_notification,Long> {

}
