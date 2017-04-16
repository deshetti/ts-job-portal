package gov.telangana.jobportal.service.mapper;

import gov.telangana.jobportal.domain.*;
import gov.telangana.jobportal.service.dto.Job_typeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Job_type and its DTO Job_typeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Job_typeMapper {

    Job_typeDTO job_typeToJob_typeDTO(Job_type job_type);

    List<Job_typeDTO> job_typesToJob_typeDTOs(List<Job_type> job_types);

    Job_type job_typeDTOToJob_type(Job_typeDTO job_typeDTO);

    List<Job_type> job_typeDTOsToJob_types(List<Job_typeDTO> job_typeDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Job_type job_typeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Job_type job_type = new Job_type();
        job_type.setId(id);
        return job_type;
    }
    

}
