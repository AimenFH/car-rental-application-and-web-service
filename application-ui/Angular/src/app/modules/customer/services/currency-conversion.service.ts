import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyConversionService {
  private apiUrl = 'https://soap-currency-converter.azurewebsites.net/';

  constructor(private http: HttpClient) {}

  convertCurrency(fromCurrency: string, toCurrency: string, amount: number): Observable<string> {
    const soapBody = `<?xml version="1.0" encoding="utf-8"?>
      <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:tns="urn:currency_converter">
        <soap:Body>
          <tns:convert_currency>
            <tns:from_currency>${fromCurrency}</tns:from_currency>
            <tns:to_currency>${toCurrency}</tns:to_currency>
            <tns:amount>${amount}</tns:amount>
          </tns:convert_currency>
        </soap:Body>
      </soap:Envelope>`;

    const headers = new HttpHeaders({
      'Content-Type': 'text/xml; charset=utf-8',
      'SOAPAction': 'convert_currency', // this has to match the SOAP operation name
    });

    return this.http.post<string>(this.apiUrl, soapBody, { headers, responseType: 'text' as 'json' });
  }
}
