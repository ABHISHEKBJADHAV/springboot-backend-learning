import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EComService } from '../../services/e-com-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  standalone: false,
  templateUrl: './add-product.html',
  styleUrls: ['./add-product.scss']
})
export class AddProduct implements OnInit {
  productForm: FormGroup;
  selectedImage?: File;

  constructor(private fb: FormBuilder, private service: EComService, private router: Router) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      brand: ['', Validators.required],
      description: ['', Validators.required],
      category: ['', Validators.required],
      releaseDate: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      stockQuantity: ['', [Validators.required, Validators.min(0)]],
      productAvailable: [false, Validators.required],
      image: [null, Validators.required]
    });
  }

  debugProductAvalibility() {
    console.log('Product Availability:', this.productForm.get('productAvailable')?.value);
  }

  ngOnInit(): void {
  }

  onImageSelected(event: Event): void {
    console.log('Image selection event:', event);
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file && (file.type === 'image/jpeg' || file.type === 'image/png')) {
      this.selectedImage = file;
      this.productForm.patchValue({ image: file });
    }
  }

  onSubmit(): void {
    if (this.productForm.valid && this.selectedImage) {
      const formData = new FormData();

      // Append JSON fields
      const productData = { ...this.productForm.value };
      // delete productData.id;
      delete productData.image;
      formData.append('product', new Blob([JSON.stringify(productData)], { type: 'application/json' }));

      // Append image file
      formData.append('file', this.selectedImage);

      this.service.addProduct(formData).subscribe(() => {
        console.log('Product added successfully');
        this.router.navigate(['/']);
      });
    }
  }
}
