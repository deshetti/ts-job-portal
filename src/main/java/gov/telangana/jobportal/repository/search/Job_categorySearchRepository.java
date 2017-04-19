package gov.telangana.jobportal.repository.search;

import gov.telangana.jobportal.domain.Job_category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Job_category entity.
 */
public interface Job_categorySearchRepository extends ElasticsearchRepository<Job_category, Long> {
}
