package gov.telangana.jobportal.repository.search;

import gov.telangana.jobportal.domain.Job_notification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Job_notification entity.
 */
public interface Job_notificationSearchRepository extends ElasticsearchRepository<Job_notification, Long> {
}
