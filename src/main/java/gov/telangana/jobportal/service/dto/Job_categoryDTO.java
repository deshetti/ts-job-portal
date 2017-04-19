package gov.telangana.jobportal.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Job_category entity.
 */
public class Job_categoryDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String category;

    @Size(max = 200)
    private String icon_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Job_categoryDTO job_categoryDTO = (Job_categoryDTO) o;

        if ( ! Objects.equals(id, job_categoryDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Job_categoryDTO{" +
            "id=" + id +
            ", category='" + category + "'" +
            ", icon_url='" + icon_url + "'" +
            '}';
    }
}
