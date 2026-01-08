import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EComService } from '../../services/e-com-service';

@Component({
  selector: 'app-main-layout',
  standalone: false,
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.scss'
})
export class MainLayout implements OnInit {
  searchTerm: string = "";

  constructor(private router: Router, private eComService: EComService) { }

  ngOnInit(): void { }

  searchProduct() {
    console.log("Search triggered for:", this.searchTerm);
    if (this.searchTerm.trim()) {
      this.router.navigate(['/search'], { queryParams: { "keyword": this.searchTerm.trim() } });
    }
    else {
      this.router.navigate(['/']);
    }
  }

  clearSearch() {
    this.searchTerm = "";
    this.router.navigate(['/']);
  }

  navigateToAddProduct() {
    this.router.navigate(['/add-product']);
  }
}
