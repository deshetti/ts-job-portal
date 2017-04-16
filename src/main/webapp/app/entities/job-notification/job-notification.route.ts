import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { Job_notificationComponent } from './job-notification.component';
import { Job_notificationDetailComponent } from './job-notification-detail.component';
import { Job_notificationPopupComponent } from './job-notification-dialog.component';
import { Job_notificationDeletePopupComponent } from './job-notification-delete-dialog.component';

import { Principal } from '../../shared';

export const job_notificationRoute: Routes = [
  {
    path: 'job-notification',
    component: Job_notificationComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_notification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'job-notification/:id',
    component: Job_notificationDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_notification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const job_notificationPopupRoute: Routes = [
  {
    path: 'job-notification-new',
    component: Job_notificationPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_notification.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'job-notification/:id/edit',
    component: Job_notificationPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_notification.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'job-notification/:id/delete',
    component: Job_notificationDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jobportalApp.job_notification.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
