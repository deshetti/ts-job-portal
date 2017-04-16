import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Job_notification } from './job-notification.model';
import { Job_notificationPopupService } from './job-notification-popup.service';
import { Job_notificationService } from './job-notification.service';

@Component({
    selector: 'jhi-job-notification-delete-dialog',
    templateUrl: './job-notification-delete-dialog.component.html'
})
export class Job_notificationDeleteDialogComponent {

    job_notification: Job_notification;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private job_notificationService: Job_notificationService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['job_notification']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.job_notificationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'job_notificationListModification',
                content: 'Deleted an job_notification'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-job-notification-delete-popup',
    template: ''
})
export class Job_notificationDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private job_notificationPopupService: Job_notificationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.job_notificationPopupService
                .open(Job_notificationDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
