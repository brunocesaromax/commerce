import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ProductService {

    productUrl: string;

    constructor(private httpClient: HttpClient) {
        this.productUrl = `${environment.apiUrl}/product`;
    }

    list() {
        return this.httpClient.get<any[]>(`${this.productUrl}`);
    }

    save(product: any) {
        return this.httpClient.post(this.productUrl, product);
    }

    delete(product: any) {
        return this.httpClient.delete(this.productUrl + '/' + product.id);
    }
}
