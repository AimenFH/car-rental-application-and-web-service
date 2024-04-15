import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.scss']
})
export class UpdateCarComponent {

  carId: number = this.activatedRoute.snapshot.params["id"];
  updateForm!: FormGroup;
  isSpinning = false;
  listOfOption: Array<{ label: string; value: string }> = [];
  listOfBrands = ["BMW", "AUDI", "FERRARI", "TESLA", "VOLVO", "TOYOTA", "HONDA", "FORD", "NISSAN", "HYUNDAI", "LEXUS", "KIA"];
  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG"];
  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Manual", "Automatic"];
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  imageChanged: boolean = false;
  existingImage: string | null = null;

  constructor(private service: AdminService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router) {
    this.updateForm = this.fb.group({
      name: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      transmission: [null, Validators.required],
      color: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      modelYear: [null, Validators.required],
    })
    this.getCarById();
  }

  getCarById() {
    this.isSpinning= true;
    this.service.getCarById(this.carId).subscribe((res) => {
      console.log(res);
      this.isSpinning= false;
      const carDto = res;
      this.existingImage = 'data:image/jpeg;base64,' + res.returnedImage;
      this.updateForm.patchValue(carDto);
    })
  }

  updateCar() {
    this.isSpinning = true;
    const formData: FormData = new FormData();
    if (this.imageChanged && this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
    formData.append('brand', this.updateForm.get('brand').value);
    formData.append('name', this.updateForm.get('name').value);
    formData.append('type', this.updateForm.get('type').value);
    formData.append('color', this.updateForm.get('color').value);
    // formData.append('modelYear', this.updateForm.get('modelYear').value);

    const modelYear: string | number = this.updateForm.get('modelYear').value;
    formData.append('modelYear', modelYear.toString());

    formData.append('transmission', this.updateForm.get('transmission').value);
    formData.append('description', this.updateForm.get('description').value);
    formData.append('price', this.updateForm.get('price').value);
    this.service.updateCar(this.carId, formData).subscribe((res) => {
      console.log(res);
      this.isSpinning = false;
      this.notification.success("Success", "Car updated successfully", { nzDuration: 5000 });
      this.router.navigateByUrl("/admin/dashboard");
    }, error => {
      this.isSpinning = false;
      this.notification.error("ERROR", "Error while updating car", { nzDuration: 5000 });
    })
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.imageChanged = true;
    this.existingImage = null;
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

}
