package gov.telangana.jobportal.service.mapper;

import gov.telangana.jobportal.domain.*;
import gov.telangana.jobportal.service.dto.Job_notificationDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Job_notification and its DTO Job_notificationDTO.
 */
@Mapper(componentModel = "spring", uses = {Job_typeMapper.class, Job_categoryMapper.class, })
public interface Job_notificationMapper {

    @Mapping(source = "job_type.id", target = "job_typeId")
    @Mapping(source = "job_type.type", target = "job_typeType")
    @Mapping(source = "job_category.id", target = "job_categoryId")
    @Mapping(source = "job_category.category", target = "job_categoryCategory")
    Job_notificationDTO job_notificationToJob_notificationDTO(Job_notification job_notification);

    List<Job_notificationDTO> job_notificationsToJob_notificationDTOs(List<Job_notification> job_notifications);

    @Mapping(source = "job_typeId", target = "job_type")
    @Mapping(source = "job_categoryId", target = "job_category")
    Job_notification job_notificationDTOToJob_notification(Job_notificationDTO job_notificationDTO);

    List<Job_notification> job_notificationDTOsToJob_notifications(List<Job_notificationDTO> job_notificationDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Job_notification job_notificationFromId(Long id) {
        if (id == null) {
            return null;
        }
        Job_notification job_notification = new Job_notification();
        job_notification.setId(id);
        return job_notification;
    }
    

}
