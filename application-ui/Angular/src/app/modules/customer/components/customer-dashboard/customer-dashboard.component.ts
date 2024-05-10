import {Component, OnInit} from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CurrencyConversionService } from '../../services/currency-conversion.service';


@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.scss']
})
export class CustomerDashboardComponent implements OnInit {
  cars: any = [];
  convertedPrices: { [id: number]: number } = {};
  selectedCurrency = 'USD';
  isVisible: boolean = false;

  constructor(
    private customerService: CustomerService,
    private message: NzMessageService,
    private currencyConversionService: CurrencyConversionService,
  ) {
    this.getAllCars();
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.isVisible = true;
    }, 1000); // 5 seconds
  }


  getAllCars() {
    this.customerService.getAlCars().subscribe((res) => {
      res.forEach((element: any) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
        this.convertedPrices[element.id] = element.price; // Initially, it's the same
      });
    });
  }

  onCurrencyChange() {
    this.cars.forEach((car: any) => {
      this.currencyConversionService.convertCurrency('USD', this.selectedCurrency, car.price).subscribe(
        (response: any) => {
          // Log the full response for troubleshooting
          console.log('Received SOAP Response:', response);

          // Parse the SOAP XML response
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(response, 'application/xml');

          // Use XPath to find the conversion string regardless of namespace
          const namespaceResolver = (prefix: string) => {
            if (prefix === 'tns') {
              return 'urn:currency_converter';
            } else if (prefix === 'soap11env') {
              return 'http://schemas.xmlsoap.org/soap/envelope/';
            }
            return null;
          };

          const xpathResult = xmlDoc.evaluate(
            "//tns:convert_currencyResult//tns:string",
            xmlDoc,
            namespaceResolver,
            XPathResult.STRING_TYPE,
            null
          );

          const resultText = xpathResult.stringValue;
          console.log('Parsed Result Text:', resultText);

          // Use regex to find the numeric conversion value
          const match = resultText.match(/Converted .* to ([0-9]+\.?[0-9]*)/);
          const convertedAmount = match ? parseFloat(match[1]) : 0;
          console.log('Parsed Converted Amount:', convertedAmount);

          // Update the converted price
          this.convertedPrices[car.id] = convertedAmount;
        },
        (error) => {
          console.error('Conversion error:', error);
          this.message.error('Currency conversion failed.');
        }
      );
    });
  }

  convertPrice(carId: number): number {
    return this.convertedPrices[carId] || 0;
  }
}
