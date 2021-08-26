import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class SaleService {

    saleUrl: string;

    constructor(private httpClient: HttpClient) {
        this.saleUrl = `${environment.apiUrl}/sale`;
    }

    list() {
        return this.httpClient.get<any[]>(`${this.saleUrl}`);
    }

    save(sale: any) {
        return this.httpClient.post(this.saleUrl, sale);
    }

    delete(sale: any) {
        return this.httpClient.delete(this.saleUrl + '/' + sale.id);
    }
}

