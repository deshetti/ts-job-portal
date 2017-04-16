import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { JobportalTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { Job_notificationDetailComponent } from '../../../../../../main/webapp/app/entities/job-notification/job-notification-detail.component';
import { Job_notificationService } from '../../../../../../main/webapp/app/entities/job-notification/job-notification.service';
import { Job_notification } from '../../../../../../main/webapp/app/entities/job-notification/job-notification.model';

describe('Component Tests', () => {

    describe('Job_notification Management Detail Component', () => {
        let comp: Job_notificationDetailComponent;
        let fixture: ComponentFixture<Job_notificationDetailComponent>;
        let service: Job_notificationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JobportalTestModule],
                declarations: [Job_notificationDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    Job_notificationService,
                    EventManager
                ]
            }).overrideComponent(Job_notificationDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Job_notificationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Job_notificationService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Job_notification(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.job_notification).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
