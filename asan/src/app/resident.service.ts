import { Resident } from './resident';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
    providedIn: 'root'
})

export class ResidentService {
    private apiServerUrl = 'http://localhost:8080/api/v1';

    constructor(private http: HttpClient) {}

    public getResidents(): Observable<Resident[]> {
        return this.http.get<any>(`${this.apiServerUrl}/resident`);
    }

    public registerNewResident(resident: Resident): Observable<Resident[]> {
        return this.http.post<any>(`${this.apiServerUrl}/resident`, resident);
    }

    public updateResident(resident: Resident): Observable<Resident> {
        return this.http.put<Resident>(`${this.apiServerUrl}/resident`, resident);
    }

    public deleteResident(residentId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/resident/${residentId}`);
    }

}