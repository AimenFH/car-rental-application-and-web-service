import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';


const BASIC_URL = ["https://car-rental-mono.azurewebsites.net"];

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  getAlCars(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/customer/v1/cars", {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarById(carId: number): Observable<any> {
    return this.http.get(BASIC_URL + "/api/customer/v1/car/" + carId, {
      headers: this.createAuthorizationHeader()
    })
  }

  bookACar(carId: number, bookCarDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/customer/v1/car/book/" + carId, bookCarDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getBookingsByUserId(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/customer/v1/car/bookings/" + StorageService.getUserId(), {
      headers: this.createAuthorizationHeader()
    })
  }

  searchCar(searchCarDto: any): Observable<any> {
    return this.http.post(BASIC_URL + `/api/customer/v1/car/search`, searchCarDto, {
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
