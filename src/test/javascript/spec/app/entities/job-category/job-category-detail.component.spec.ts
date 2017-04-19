import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { JobportalTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { Job_categoryDetailComponent } from '../../../../../../main/webapp/app/entities/job-category/job-category-detail.component';
import { Job_categoryService } from '../../../../../../main/webapp/app/entities/job-category/job-category.service';
import { Job_category } from '../../../../../../main/webapp/app/entities/job-category/job-category.model';

describe('Component Tests', () => {

    describe('Job_category Management Detail Component', () => {
        let comp: Job_categoryDetailComponent;
        let fixture: ComponentFixture<Job_categoryDetailComponent>;
        let service: Job_categoryService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JobportalTestModule],
                declarations: [Job_categoryDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    Job_categoryService,
                    EventManager
                ]
            }).overrideComponent(Job_categoryDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Job_categoryDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Job_categoryService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Job_category(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.job_category).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
