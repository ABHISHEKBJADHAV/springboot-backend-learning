import { Component, Input, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Product } from '../../dto/product';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { Router } from '@angular/router';
import { EComService } from '../../services/e-com-service';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, CurrencyPipe, CommonModule],
  templateUrl: './product-card.html',
  styleUrl: './product-card.scss'
})
export class ProductCard implements OnInit {
  @Input() product!: Product;

  constructor(private route: Router, private service: EComService) { }
  ngOnInit(): void {
  }

  showDetails(): void {
    console.log("Navigating to product details");
    this.route.navigate(['/product-info', this.product.id]);
  }
  addToCart(): void {
    this.service.addToCart(this.product.id, 1).subscribe();
    console.log("Procuct added to cart: ", this.product);
  }
}
