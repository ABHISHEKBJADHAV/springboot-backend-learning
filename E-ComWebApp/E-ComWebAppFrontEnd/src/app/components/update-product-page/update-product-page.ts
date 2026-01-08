import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../dto/product';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EComService } from '../../services/e-com-service';

@Component({
  selector: 'app-update-product-page',
  standalone: false,
  templateUrl: './update-product-page.html',
  styleUrl: './update-product-page.scss'
})
export class UpdateProductPage implements OnInit {
  product?: Product;
  productForm: FormGroup;
  selectedImage?: File;

  constructor(private route: ActivatedRoute, private fb: FormBuilder, private router: Router,
    private service: EComService) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      brand: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      category: ['', Validators.required],
      releaseDate: ['', Validators.required],
      productAvailable: [true],
      stockQuantity: ['', [Validators.required, Validators.min(0)]],
      image: [null]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const productId = Number(params.get('id'));
      console.log("Update product with ID: ", productId);
      this.service.getProductById(productId).subscribe(data => {
        this.product = data;
        this.productForm.patchValue({
          name: this.product.name,
          brand: this.product.brand,
          description: this.product.description,
          price: this.product.price,
          category: this.product.category,
          releaseDate: this.product.releaseDate,
          productAvailable: this.product.productAvailable,
          stockQuantity: this.product.stockQuantity
        });
      });
    });
  }

  onImageSelected(event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file && (file.type === 'image/jpeg' || file.type === 'image/png')) {
      this.selectedImage = file;
      this.productForm.patchValue({ image: file });
    }
  }

  onSubmit(): void {
    if (this.productForm.valid && this.product) {
      const formData = new FormData();

      const updatedProduct = {
        ...this.product,
        ...this.productForm.value
      };

      delete updatedProduct.image;

      formData.append('product', new Blob([JSON.stringify(updatedProduct)], { type: 'application/json' }));

      if (this.selectedImage) {
        formData.append('file', this.selectedImage);
      }

      this.service.updateProduct(formData, this.product.id).subscribe(() => {
        console.log("Product updated successfully");
        this.router.navigate(['/']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['']);
    console.log("Update cancelled");
  }


}
