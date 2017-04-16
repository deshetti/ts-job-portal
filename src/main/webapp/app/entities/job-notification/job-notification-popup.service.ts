import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Job_notification } from './job-notification.model';
import { Job_notificationService } from './job-notification.service';
@Injectable()
export class Job_notificationPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private job_notificationService: Job_notificationService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.job_notificationService.find(id).subscribe((job_notification) => {
                if (job_notification.notification_date) {
                    job_notification.notification_date = {
                        year: job_notification.notification_date.getFullYear(),
                        month: job_notification.notification_date.getMonth() + 1,
                        day: job_notification.notification_date.getDate()
                    };
                }
                if (job_notification.application_deadline) {
                    job_notification.application_deadline = {
                        year: job_notification.application_deadline.getFullYear(),
                        month: job_notification.application_deadline.getMonth() + 1,
                        day: job_notification.application_deadline.getDate()
                    };
                }
                this.job_notificationModalRef(component, job_notification);
            });
        } else {
            return this.job_notificationModalRef(component, new Job_notification());
        }
    }

    job_notificationModalRef(component: Component, job_notification: Job_notification): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.job_notification = job_notification;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
