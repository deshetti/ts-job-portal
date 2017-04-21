package gov.telangana.jobportal.web.domain;

import java.time.LocalDate;

/**
 * Created by Sravan on 4/21/2017.
 */
public class JobSummary {

    private long id;
    private String positionTitle;
    private int vacancies;
    private LocalDate applicationDeadline;
    private String educationLimit;

    public JobSummary(long id, String positionTitle, int vacancies, LocalDate applicationDeadline, String educationLimit) {
        this.id = id;
        this.positionTitle = positionTitle;
        this.vacancies = vacancies;
        this.applicationDeadline = applicationDeadline;
        this.educationLimit = educationLimit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public String getEducationLimit() {
        return educationLimit;
    }

    public void setEducationLimit(String educationLimit) {
        this.educationLimit = educationLimit;
    }
}
