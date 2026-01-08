import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../dto/product';

@Injectable({
  providedIn: 'root'
})
export class EComService {

  baseUrl: string = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + 'products');
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(this.baseUrl + 'product/' + id);
  }

  addProduct(formData: FormData) {
    return this.http.post(this.baseUrl + 'product', formData);
  }

  updateProduct(formData: FormData, productId: number) {
    return this.http.put(this.baseUrl + 'product/' + productId, formData);
  }

  deleteProduct(id: number) {
    return this.http.delete(this.baseUrl + 'product/' + id);
  }

  getProductsBySearch(keyword: String): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + 'products/search?keyword=' + keyword);
  }

  addToCart(productId: number, quantity: number) {
    return this.http.post(this.baseUrl + 'cart/add', { productId, quantity });
  }

}
