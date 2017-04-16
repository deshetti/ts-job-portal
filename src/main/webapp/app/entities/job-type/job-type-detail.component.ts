import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Job_type } from './job-type.model';
import { Job_typeService } from './job-type.service';

@Component({
    selector: 'jhi-job-type-detail',
    templateUrl: './job-type-detail.component.html'
})
export class Job_typeDetailComponent implements OnInit, OnDestroy {

    job_type: Job_type;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private job_typeService: Job_typeService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['job_type']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJob_types();
    }

    load(id) {
        this.job_typeService.find(id).subscribe((job_type) => {
            this.job_type = job_type;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJob_types() {
        this.eventSubscriber = this.eventManager.subscribe('job_typeListModification', (response) => this.load(this.job_type.id));
    }
}
