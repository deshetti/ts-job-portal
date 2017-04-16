import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Job_type } from './job-type.model';
import { Job_typePopupService } from './job-type-popup.service';
import { Job_typeService } from './job-type.service';

@Component({
    selector: 'jhi-job-type-delete-dialog',
    templateUrl: './job-type-delete-dialog.component.html'
})
export class Job_typeDeleteDialogComponent {

    job_type: Job_type;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private job_typeService: Job_typeService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['job_type']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.job_typeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'job_typeListModification',
                content: 'Deleted an job_type'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-job-type-delete-popup',
    template: ''
})
export class Job_typeDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private job_typePopupService: Job_typePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.job_typePopupService
                .open(Job_typeDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
