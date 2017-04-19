import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Job_category } from './job-category.model';
import { Job_categoryPopupService } from './job-category-popup.service';
import { Job_categoryService } from './job-category.service';

@Component({
    selector: 'jhi-job-category-delete-dialog',
    templateUrl: './job-category-delete-dialog.component.html'
})
export class Job_categoryDeleteDialogComponent {

    job_category: Job_category;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private job_categoryService: Job_categoryService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['job_category']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.job_categoryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'job_categoryListModification',
                content: 'Deleted an job_category'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-job-category-delete-popup',
    template: ''
})
export class Job_categoryDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private job_categoryPopupService: Job_categoryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.job_categoryPopupService
                .open(Job_categoryDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
