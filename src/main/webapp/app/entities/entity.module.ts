import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JobportalJob_notificationModule } from './job-notification/job-notification.module';
import { JobportalJob_typeModule } from './job-type/job-type.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JobportalJob_notificationModule,
        JobportalJob_typeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobportalEntityModule {}
