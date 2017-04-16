package gov.telangana.jobportal.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Job_type entity.
 */
public class Job_typeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Job_typeDTO job_typeDTO = (Job_typeDTO) o;

        if ( ! Objects.equals(id, job_typeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Job_typeDTO{" +
            "id=" + id +
            ", type='" + type + "'" +
            '}';
    }
}
