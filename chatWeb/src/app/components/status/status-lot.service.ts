import { Injectable } from '@angular/core';
import { BaseService } from '../../core/base.service';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class StatusLotService {
	private static readonly BASE_URL: string = BaseService.HOST + '/statusLot';

	constructor(
		private http: HttpClient
	) {}

	getAll(params: HttpParams = new HttpParams()): Observable<any> {
		return this.http.get(StatusLotService.BASE_URL, {params: params});
	}
}
