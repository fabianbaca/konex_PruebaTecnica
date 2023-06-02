import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Medicament } from '../models/medicament.model';
import { environment } from '../environment/environment ';

@Injectable({
  providedIn: 'root'
})
export class MedicamentService {
  
  constructor(private http: HttpClient) { }

  getAll(): Observable<Medicament[]> {
    return this.http.get<Medicament[]>(`${environment.API_URL}/api/Medicament/`);
  }

  get(id: any): Observable<Medicament> {
    return this.http.get<Medicament>(`${environment.API_URL}/api/Medicament/${id}`);
  }

  create(medicament: Medicament, dateManufacture: string, dateExpiration: string ): Observable<any> {
    const data = {...medicament, dateManufacture: dateManufacture, dateExpiration: dateExpiration}
    return this.http.post(`${environment.API_URL}/api/Medicament/`, data);
  }

  update(id: any, medicament: Medicament): Observable<any> {
    return this.http.put(`${environment.API_URL}/api/Medicament/${id}`, medicament);
  }

  delete(id: any): Observable<any> {
    console.log(id)
    return this.http.delete(`${environment.API_URL}/api/Medicament/${id}`);
  }

  findByName(name: string): Observable<Medicament[]> {
    return this.http.get<Medicament[]>(`${environment.API_URL}/api/Medicament/?name=${name}`);
  }


}
