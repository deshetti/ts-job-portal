import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Job_category } from './job-category.model';
import { Job_categoryService } from './job-category.service';
@Injectable()
export class Job_categoryPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private job_categoryService: Job_categoryService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.job_categoryService.find(id).subscribe((job_category) => {
                this.job_categoryModalRef(component, job_category);
            });
        } else {
            return this.job_categoryModalRef(component, new Job_category());
        }
    }

    job_categoryModalRef(component: Component, job_category: Job_category): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.job_category = job_category;
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
