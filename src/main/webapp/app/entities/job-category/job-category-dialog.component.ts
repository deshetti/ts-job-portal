import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Job_category } from './job-category.model';
import { Job_categoryPopupService } from './job-category-popup.service';
import { Job_categoryService } from './job-category.service';

@Component({
    selector: 'jhi-job-category-dialog',
    templateUrl: './job-category-dialog.component.html'
})
export class Job_categoryDialogComponent implements OnInit {

    job_category: Job_category;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private job_categoryService: Job_categoryService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['job_category']);
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
        if (this.job_category.id !== undefined) {
            this.job_categoryService.update(this.job_category)
                .subscribe((res: Job_category) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.job_categoryService.create(this.job_category)
                .subscribe((res: Job_category) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Job_category) {
        this.eventManager.broadcast({ name: 'job_categoryListModification', content: 'OK'});
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
    selector: 'jhi-job-category-popup',
    template: ''
})
export class Job_categoryPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private job_categoryPopupService: Job_categoryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.job_categoryPopupService
                    .open(Job_categoryDialogComponent, params['id']);
            } else {
                this.modalRef = this.job_categoryPopupService
                    .open(Job_categoryDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
