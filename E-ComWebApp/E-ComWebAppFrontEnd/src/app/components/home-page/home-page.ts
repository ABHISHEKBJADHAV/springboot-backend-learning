import { Component, OnInit } from '@angular/core';
import { Product } from '../../dto/product';
import { EComService } from '../../services/e-com-service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home-page',
  standalone: false,
  templateUrl: './home-page.html',
  styleUrls: ['./home-page.scss']
})
export class HomePage implements OnInit {
  products: Product[] = [];
  searchTerm: string = "";

  constructor(private service: EComService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    console.log("Home page loaded...");
    this.activatedRoute.queryParams.subscribe(params => {
      this.searchTerm = params['keyword'] || "";
    });

    if (this.searchTerm.trim()) {
      this.service.getProductsBySearch(this.searchTerm).subscribe(data => {
        console.log("Products- ", data);
        this.products = data;
      });
    } else {
      this.service.getAllProducts().subscribe(data => {
        console.log("Products- ", data);
        this.products = data;
      });
    }
  }
}
