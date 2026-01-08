import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../dto/product';
import { EComService } from '../../services/e-com-service';

@Component({
  selector: 'app-product-info',
  standalone: false,
  templateUrl: './product-info.html',
  styleUrl: './product-info.scss'
})
export class ProductInfo implements OnInit {
  productId?: number;
  product?: Product;
  constructor(private route: ActivatedRoute, private router: Router, private service: EComService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.productId = Number(params.get('id'));
    });
    console.log(this.productId);
    if (this.productId != undefined) {
      this.service.getProductById(this.productId).subscribe(data => {
        this.product = data;
        console.log(this.product);
      });
    }
  }

  updateProduct(): void {
    this.router.navigate(['/update-product', this.productId]);
  }
  deleteProduct(): void {
    if (this.productId != undefined) {
      this.service.deleteProduct(this.productId).subscribe(() => {
        console.log('Product deleted successfully');
        this.router.navigate(['/']);
      });
    }
  }
}
