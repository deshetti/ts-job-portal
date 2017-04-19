import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { Job_categoryComponent } from './job-category.component';
import { Job_categoryDetailComponent } from './job-category-detail.component';
import { Job_categoryPopupComponent } from './job-category-dialog.component';
import { Job_categoryDeletePopupComponent } from './job-category-delete-dialog.component';

import { Principal } from '../../shared';

export const job_categoryRoute: Routes = [
  {
    path: 'job-category',
    component: Job_categoryComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_category.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'job-category/:id',
    component: Job_categoryDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_category.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const job_categoryPopupRoute: Routes = [
  {
    path: 'job-category-new',
    component: Job_categoryPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_category.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'job-category/:id/edit',
    component: Job_categoryPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_category.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'job-category/:id/delete',
    component: Job_categoryDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_category.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
