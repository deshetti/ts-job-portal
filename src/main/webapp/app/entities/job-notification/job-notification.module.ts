import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobportalSharedModule } from '../../shared';
import {
    Job_notificationService,
    Job_notificationPopupService,
    Job_notificationComponent,
    Job_notificationDetailComponent,
    Job_notificationDialogComponent,
    Job_notificationPopupComponent,
    Job_notificationDeletePopupComponent,
    Job_notificationDeleteDialogComponent,
    job_notificationRoute,
    job_notificationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...job_notificationRoute,
    ...job_notificationPopupRoute,
];

@NgModule({
    imports: [
        JobportalSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        Job_notificationComponent,
        Job_notificationDetailComponent,
        Job_notificationDialogComponent,
        Job_notificationDeleteDialogComponent,
        Job_notificationPopupComponent,
        Job_notificationDeletePopupComponent,
    ],
    entryComponents: [
        Job_notificationComponent,
        Job_notificationDialogComponent,
        Job_notificationPopupComponent,
        Job_notificationDeleteDialogComponent,
        Job_notificationDeletePopupComponent,
    ],
    providers: [
        Job_notificationService,
        Job_notificationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobportalJob_notificationModule {}
