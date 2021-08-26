import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ClientsService {

    clientUrl: string;

    constructor(private httpClient: HttpClient) {
        this.clientUrl = `${environment.apiUrl}/client`;
    }

    list() {
        return this.httpClient.get<any[]>(`${this.clientUrl}`);
    }

    save(client: any) {
        return this.httpClient.post(this.clientUrl, client);
    }

    delete(client: any) {
        return this.httpClient.delete(this.clientUrl + '/' + client.id);
    }

}
