export class Job_notification {
    constructor(
        public id?: number,
        public position_title?: string,
        public notification_date?: any,
        public job_location?: string,
        public post_name?: number,
        public description?: number,
        public no_of_vacancies?: number,
        public age_limit?: string,
        public education_limit?: string,
        public salary?: string,
        public reservation?: string,
        public application_deadline?: any,
        public notification_link?: number,
        public application_link?: number,
        public organization?: string,
        public job_typeId?: number,
    ) {
    }
}
