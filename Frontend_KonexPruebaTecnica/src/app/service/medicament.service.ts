import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Medicament } from '../models/medicament.model';

const baseUrl = 'http://localhost:8080/api/Medicament';

@Injectable({
  providedIn: 'root'
})
export class MedicamentService {
  
  constructor(private http: HttpClient) { }

  getAll(): Observable<Medicament[]> {
    return this.http.get<Medicament[]>(`${baseUrl}/`);
  }

  get(id: any): Observable<Medicament> {
    return this.http.get<Medicament>(`${baseUrl}/${id}`);
  }

  create(medicament: Medicament, dateManufacture: string, dateExpiration: string ): Observable<any> {
    const data = {...medicament, dateManufacture: dateManufacture, dateExpiration: dateExpiration}
    return this.http.post(`${baseUrl}/`, data);
  }

  update(id: any, medicament: Medicament): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, medicament);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  findByName(name: string): Observable<Medicament[]> {
    return this.http.get<Medicament[]>(`${baseUrl}?name=${name}`);
  }


}
