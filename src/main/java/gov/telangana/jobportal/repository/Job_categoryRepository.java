package gov.telangana.jobportal.repository;

import gov.telangana.jobportal.domain.Job_category;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Job_category entity.
 */
@SuppressWarnings("unused")
public interface Job_categoryRepository extends JpaRepository<Job_category,Long> {

}
