import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams, BaseRequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Job_notification } from './job-notification.model';
import { DateUtils } from 'ng-jhipster';
@Injectable()
export class Job_notificationService {

    private resourceUrl = 'api/job-notifications';
    private resourceSearchUrl = 'api/_search/job-notifications';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    create(job_notification: Job_notification): Observable<Job_notification> {
        const copy: Job_notification = Object.assign({}, job_notification);
        copy.notification_date = this.dateUtils
            .convertLocalDateToServer(job_notification.notification_date);
        copy.application_deadline = this.dateUtils
            .convertLocalDateToServer(job_notification.application_deadline);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(job_notification: Job_notification): Observable<Job_notification> {
        const copy: Job_notification = Object.assign({}, job_notification);
        copy.notification_date = this.dateUtils
            .convertLocalDateToServer(job_notification.notification_date);
        copy.application_deadline = this.dateUtils
            .convertLocalDateToServer(job_notification.application_deadline);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Job_notification> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            jsonResponse.notification_date = this.dateUtils
                .convertLocalDateFromServer(jsonResponse.notification_date);
            jsonResponse.application_deadline = this.dateUtils
                .convertLocalDateFromServer(jsonResponse.application_deadline);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<Response> {
        const options = this.createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: any) => this.convertResponse(res))
        ;
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<Response> {
        const options = this.createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res))
        ;
    }


    private convertResponse(res: any): any {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            jsonResponse[i].notification_date = this.dateUtils
                .convertLocalDateFromServer(jsonResponse[i].notification_date);
            jsonResponse[i].application_deadline = this.dateUtils
                .convertLocalDateFromServer(jsonResponse[i].application_deadline);
        }
        res._body = jsonResponse;
        return res;
    }

    private createRequestOption(req?: any): BaseRequestOptions {
        const options: BaseRequestOptions = new BaseRequestOptions();
        if (req) {
            const params: URLSearchParams = new URLSearchParams();
            params.set('page', req.page);
            params.set('size', req.size);
            if (req.sort) {
                params.paramsMap.set('sort', req.sort);
            }
            params.set('query', req.query);

            options.search = params;
        }
        return options;
    }
}
