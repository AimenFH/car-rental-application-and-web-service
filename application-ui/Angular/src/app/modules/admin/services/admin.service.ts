import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';

const BASIC_URL = ["https://car-rental-mono.azurewebsites.net"];

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  postCar(carDto: any) {
    return this.http.post(BASIC_URL + "/api/admin/v1/car", carDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAlCars(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/v1/cars", {
      headers: this.createAuthorizationHeader()
    })
  }

  deleteCar(carId: number): Observable<any> {
    return this.http.delete(BASIC_URL + "/api/admin/v1/car/" + carId, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarById(carId: number): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/v1/car/" + carId, {
      headers: this.createAuthorizationHeader()
    })
  }

  updateCar(carId: number, carDto: any) {
    return this.http.put(BASIC_URL + "/api/admin/v1/car/" + carId, carDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getBookings(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/v1/car/bookings", {
      headers: this.createAuthorizationHeader()
    })
  }

  changeStatus(bookingId: number, status: string): Observable<any> {
    return this.http.get(BASIC_URL + `/api/admin/v1/car/booking/${bookingId}/${status}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  searchCar(searchCarDto: any): Observable<any> {
    return this.http.post(BASIC_URL + `/api/admin/v1/car/search`, searchCarDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization',
      'Bearer ' + StorageService.getToken()
    );
  }

}
