import { ProductService } from '../service/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

    products: Array<any>;
    product = {name: null, description: null, price: null};
    msgError = null;
    msgSuccess = null;

    constructor(private productService: ProductService) {
    }

    ngOnInit() {
        this.list();
    }

    list() {
        this.productService.list().subscribe(dados => this.products = dados);
    }

    save() {
        this.productService.save(this.product)
            .subscribe(() => {
                    this.product = {name: null, description: null, price: null};
                    this.list();
                    this.msgSuccess = 'Product created successfully.';
                },
                response => {
                    this.msgError = 'Name and price of product cannot be empty.';
                }
            );

        this.msgError = null;
        this.msgSuccess = null;
    }

    delete(product: any) {
        this.productService.delete(product)
            .subscribe(() => {
                    this.list();
                    this.msgSuccess = 'Product deleted successfully.';
                },
                response => {
                    this.msgError = 'Could not delete product.';
                }
            );

        this.msgError = null;
        this.msgSuccess = null;
    }

    update(product: any) {
        this.product = product;
    }

    cancel() {
        this.product = {name: null, description: null, price: null};
    }
}
