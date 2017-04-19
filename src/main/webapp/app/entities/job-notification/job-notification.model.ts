export class Job_notification {
    constructor(
        public id?: number,
        public position_title?: string,
        public notification_date?: any,
        public job_location?: string,
        public no_of_vacancies?: number,
        public age_limit?: string,
        public education_limit?: string,
        public salary?: string,
        public reservation?: string,
        public application_deadline?: any,
        public organization?: string,
        public duration?: string,
        public notification_link?: any,
        public application_link?: any,
        public description?: any,
        public job_typeId?: number,
        public job_categoryId?: number,
    ) {
    }
}
