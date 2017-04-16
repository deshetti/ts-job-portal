import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { JobportalTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { Job_typeDetailComponent } from '../../../../../../main/webapp/app/entities/job-type/job-type-detail.component';
import { Job_typeService } from '../../../../../../main/webapp/app/entities/job-type/job-type.service';
import { Job_type } from '../../../../../../main/webapp/app/entities/job-type/job-type.model';

describe('Component Tests', () => {

    describe('Job_type Management Detail Component', () => {
        let comp: Job_typeDetailComponent;
        let fixture: ComponentFixture<Job_typeDetailComponent>;
        let service: Job_typeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JobportalTestModule],
                declarations: [Job_typeDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    Job_typeService,
                    EventManager
                ]
            }).overrideComponent(Job_typeDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Job_typeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Job_typeService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Job_type(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.job_type).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
