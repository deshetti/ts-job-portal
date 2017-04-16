import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Job_type } from './job-type.model';
import { Job_typePopupService } from './job-type-popup.service';
import { Job_typeService } from './job-type.service';

@Component({
    selector: 'jhi-job-type-dialog',
    templateUrl: './job-type-dialog.component.html'
})
export class Job_typeDialogComponent implements OnInit {

    job_type: Job_type;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private job_typeService: Job_typeService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['job_type']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.job_type.id !== undefined) {
            this.job_typeService.update(this.job_type)
                .subscribe((res: Job_type) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.job_typeService.create(this.job_type)
                .subscribe((res: Job_type) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Job_type) {
        this.eventManager.broadcast({ name: 'job_typeListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-job-type-popup',
    template: ''
})
export class Job_typePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private job_typePopupService: Job_typePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.job_typePopupService
                    .open(Job_typeDialogComponent, params['id']);
            } else {
                this.modalRef = this.job_typePopupService
                    .open(Job_typeDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
