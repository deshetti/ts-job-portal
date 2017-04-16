import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Job_notification } from './job-notification.model';
import { Job_notificationService } from './job-notification.service';

@Component({
    selector: 'jhi-job-notification-detail',
    templateUrl: './job-notification-detail.component.html'
})
export class Job_notificationDetailComponent implements OnInit, OnDestroy {

    job_notification: Job_notification;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private job_notificationService: Job_notificationService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['job_notification']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJob_notifications();
    }

    load(id) {
        this.job_notificationService.find(id).subscribe((job_notification) => {
            this.job_notification = job_notification;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJob_notifications() {
        this.eventSubscriber = this.eventManager.subscribe('job_notificationListModification', (response) => this.load(this.job_notification.id));
    }
}
