import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobportalSharedModule } from '../../shared';
import {
    Job_categoryService,
    Job_categoryPopupService,
    Job_categoryComponent,
    Job_categoryDetailComponent,
    Job_categoryDialogComponent,
    Job_categoryPopupComponent,
    Job_categoryDeletePopupComponent,
    Job_categoryDeleteDialogComponent,
    job_categoryRoute,
    job_categoryPopupRoute,
} from './';

const ENTITY_STATES = [
    ...job_categoryRoute,
    ...job_categoryPopupRoute,
];

@NgModule({
    imports: [
        JobportalSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        Job_categoryComponent,
        Job_categoryDetailComponent,
        Job_categoryDialogComponent,
        Job_categoryDeleteDialogComponent,
        Job_categoryPopupComponent,
        Job_categoryDeletePopupComponent,
    ],
    entryComponents: [
        Job_categoryComponent,
        Job_categoryDialogComponent,
        Job_categoryPopupComponent,
        Job_categoryDeleteDialogComponent,
        Job_categoryDeletePopupComponent,
    ],
    providers: [
        Job_categoryService,
        Job_categoryPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobportalJob_categoryModule {}
