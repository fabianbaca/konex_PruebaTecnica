import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sale } from '../models/sale.model';
import { environment } from '../environment/environment ';


@Injectable({
  providedIn: 'root'
})
export class SaleService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Sale[]> {
    return this.http.get<Sale[]>(`${environment.API_URL}/api/Sale/`);
  }

  create(data: Sale): Observable<any> {
    return this.http.post(`${environment.API_URL}/api/Sale/`, data);
  }

  findAllByDateSaleBetween(startDate: string, endDate:string): Observable<any> {
    return this.http.get(`${environment.API_URL}/api/Sale/betweenDate?start=${startDate}&end=${endDate}`);
  }

}
