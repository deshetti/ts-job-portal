package gov.telangana.jobportal.repository.search;

import gov.telangana.jobportal.domain.Job_type;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Job_type entity.
 */
public interface Job_typeSearchRepository extends ElasticsearchRepository<Job_type, Long> {
}
