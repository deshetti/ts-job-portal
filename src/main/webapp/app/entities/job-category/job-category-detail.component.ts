import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Job_category } from './job-category.model';
import { Job_categoryService } from './job-category.service';

@Component({
    selector: 'jhi-job-category-detail',
    templateUrl: './job-category-detail.component.html'
})
export class Job_categoryDetailComponent implements OnInit, OnDestroy {

    job_category: Job_category;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private job_categoryService: Job_categoryService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['job_category']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJob_categories();
    }

    load(id) {
        this.job_categoryService.find(id).subscribe((job_category) => {
            this.job_category = job_category;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJob_categories() {
        this.eventSubscriber = this.eventManager.subscribe('job_categoryListModification', (response) => this.load(this.job_category.id));
    }
}
