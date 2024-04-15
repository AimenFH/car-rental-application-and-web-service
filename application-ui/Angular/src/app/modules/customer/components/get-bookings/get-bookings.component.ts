import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrls: ['./get-bookings.component.scss']
})
export class GetBookingsComponent {

  isSpinning = false;
  bookedCars: any;

  constructor(private service: CustomerService) {
    this.getBookings();
  }

  getBookings() {
    this.isSpinning = true;
    this.service.getBookingsByUserId().subscribe((res) => {
      this.isSpinning = false;
      console.log(res);
      this.bookedCars = res;
    })
  }


}
