import { ClientsService } from '../service/clients.service';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
    selector: 'app-clients',
    templateUrl: './clients.component.html',
    styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

    clients: Array<any>;
    client = {name: null, email: null, dateBirth: null};
    msgError = null;
    msgSuccess = null;

    constructor(private clientService: ClientsService, private datePipe: DatePipe) {
    }

    ngOnInit() {
        this.list();
    }

    list() {
        this.clientService.list().subscribe(dados =>
            dados.forEach(element => {
                    element.dateBirth = this.datePipe.transform(element.dateBirth, 'yyyy-MM-dd');
                },
                this.clients = dados
            ));
    }

    save() {
        this.clientService.save(this.client)
            .subscribe(() => {
                    this.client = {name: null, email: null, dateBirth: null};
                    this.list();
                    this.msgSuccess = 'Client created successfully.';
                },
                response => {
                    this.msgError = 'Name and e-mail of client cannot be empty.';
                }
            );

        this.msgError = null;
        this.msgSuccess = null;
    }

    delete(client: any) {
        this.clientService.delete(client)
            .subscribe(() => {
                    this.list();
                    this.msgSuccess = 'Client created successfully.';
                },
                response => {
                    this.msgError = 'Could not delete client.';
                }
            );

        this.msgError = null;
        this.msgSuccess = null;
    }

    update(client: any) {
        this.client = client;
        client.dateBirth = this.datePipe.transform(client.dateBirth, 'yyyy-MM-dd');
    }

    cancel() {
        this.client = {name: null, email: null, dateBirth: null};
    }
}
