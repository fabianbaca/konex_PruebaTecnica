import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sale } from '../models/sale.model';

const baseUrl = 'http://localhost:8080/api/Sale';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Sale[]> {
    return this.http.get<Sale[]>(`${baseUrl}/`);
  }

  create(data: Sale): Observable<any> {
    return this.http.post(`${baseUrl}/`, data);
  }

  findAllByDateSaleBetween(startDate: string, endDate:string): Observable<any> {
    return this.http.get(`${baseUrl}/betweenDate?start=${startDate}&end=${endDate}`);
  }

}
