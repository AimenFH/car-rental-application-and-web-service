import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-search-car',
  templateUrl: './search-car.component.html',
  styleUrls: ['./search-car.component.scss']
})
export class SearchCarComponent {

  cars: any = [];
  isSpinning = false;
  validateForm: FormGroup;
  listOfBrands = ["BMW", "AUDI", "FERRARI", "TESLA", "VOLVO", "TOYOTA", "HONDA", "FORD", "NISSAN", "HYUNDAI", "LEXUS", "KIA"];
  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG"];
  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Manual", "Automatic"];

  constructor(private fb: FormBuilder,
    private adminService: AdminService) {
    this.validateForm = this.fb.group({
      brand: [null],
      type: [null],
      color: [null],
      transmission: [null]
    })
  }

  searchCar() {
    this.cars = [];
    this.isSpinning = true;
    this.adminService.searchCar(this.validateForm.value).subscribe((res) => {
      this.isSpinning = false;
      console.log(res);
      res.carDtoList.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
      });
    })
  }

}
