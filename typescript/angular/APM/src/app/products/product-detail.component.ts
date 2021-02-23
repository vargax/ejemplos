import { Component, OnInit } from '@angular/core';
import {IProduct} from './product';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from './product.service';

@Component({
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  pageTitle = 'Product Detail';
  product: IProduct;

  constructor(private route: ActivatedRoute, private router: Router, private productService: ProductService) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.productService.getProduct(id).subscribe( {
      next: product => this.product = product
    });
  }

  onBack(): void {
    this.router.navigate(['/products']);
  }

}
