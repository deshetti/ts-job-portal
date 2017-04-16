package gov.telangana.jobportal.repository;

import gov.telangana.jobportal.domain.Job_type;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Job_type entity.
 */
@SuppressWarnings("unused")
public interface Job_typeRepository extends JpaRepository<Job_type,Long> {

}
