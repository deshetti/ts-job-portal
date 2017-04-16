import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { Job_typeComponent } from './job-type.component';
import { Job_typeDetailComponent } from './job-type-detail.component';
import { Job_typePopupComponent } from './job-type-dialog.component';
import { Job_typeDeletePopupComponent } from './job-type-delete-dialog.component';

import { Principal } from '../../shared';

export const job_typeRoute: Routes = [
  {
    path: 'job-type',
    component: Job_typeComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_type.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'job-type/:id',
    component: Job_typeDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_type.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const job_typePopupRoute: Routes = [
  {
    path: 'job-type-new',
    component: Job_typePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_type.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'job-type/:id/edit',
    component: Job_typePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_type.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'job-type/:id/delete',
    component: Job_typeDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_type.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
