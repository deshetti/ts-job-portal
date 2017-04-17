import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService, DataUtils } from 'ng-jhipster';

import { Job_notification } from './job-notification.model';
import { Job_notificationPopupService } from './job-notification-popup.service';
import { Job_notificationService } from './job-notification.service';
import { Job_type, Job_typeService } from '../job-type';

@Component({
    selector: 'jhi-job-notification-dialog',
    templateUrl: './job-notification-dialog.component.html'
})
export class Job_notificationDialogComponent implements OnInit {

    job_notification: Job_notification;
    authorities: any[];
    isSaving: boolean;

    job_types: Job_type[];
            constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private dataUtils: DataUtils,
        private alertService: AlertService,
        private job_notificationService: Job_notificationService,
        private job_typeService: Job_typeService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['job_notification']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.job_typeService.query().subscribe(
            (res: Response) => { this.job_types = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, job_notification, field, isImage) {
        if (event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                job_notification[field] = base64Data;
                job_notification[`${field}ContentType`] = file.type;
            });
        }
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.job_notification.id !== undefined) {
            this.job_notificationService.update(this.job_notification)
                .subscribe((res: Job_notification) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.job_notificationService.create(this.job_notification)
                .subscribe((res: Job_notification) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Job_notification) {
        this.eventManager.broadcast({ name: 'job_notificationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackJob_typeById(index: number, item: Job_type) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-job-notification-popup',
    template: ''
})
export class Job_notificationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private job_notificationPopupService: Job_notificationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.job_notificationPopupService
                    .open(Job_notificationDialogComponent, params['id']);
            } else {
                this.modalRef = this.job_notificationPopupService
                    .open(Job_notificationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
