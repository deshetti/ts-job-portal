package gov.telangana.jobportal.service.mapper;

import gov.telangana.jobportal.domain.*;
import gov.telangana.jobportal.service.dto.Job_categoryDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Job_category and its DTO Job_categoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Job_categoryMapper {

    Job_categoryDTO job_categoryToJob_categoryDTO(Job_category job_category);

    List<Job_categoryDTO> job_categoriesToJob_categoryDTOs(List<Job_category> job_categories);

    Job_category job_categoryDTOToJob_category(Job_categoryDTO job_categoryDTO);

    List<Job_category> job_categoryDTOsToJob_categories(List<Job_categoryDTO> job_categoryDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Job_category job_categoryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Job_category job_category = new Job_category();
        job_category.setId(id);
        return job_category;
    }
    

}
