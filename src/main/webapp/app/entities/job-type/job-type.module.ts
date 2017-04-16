import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobportalSharedModule } from '../../shared';
import {
    Job_typeService,
    Job_typePopupService,
    Job_typeComponent,
    Job_typeDetailComponent,
    Job_typeDialogComponent,
    Job_typePopupComponent,
    Job_typeDeletePopupComponent,
    Job_typeDeleteDialogComponent,
    job_typeRoute,
    job_typePopupRoute,
} from './';

const ENTITY_STATES = [
    ...job_typeRoute,
    ...job_typePopupRoute,
];

@NgModule({
    imports: [
        JobportalSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        Job_typeComponent,
        Job_typeDetailComponent,
        Job_typeDialogComponent,
        Job_typeDeleteDialogComponent,
        Job_typePopupComponent,
        Job_typeDeletePopupComponent,
    ],
    entryComponents: [
        Job_typeComponent,
        Job_typeDialogComponent,
        Job_typePopupComponent,
        Job_typeDeleteDialogComponent,
        Job_typeDeletePopupComponent,
    ],
    providers: [
        Job_typeService,
        Job_typePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobportalJob_typeModule {}
