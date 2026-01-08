import { Routes } from '@angular/router';
import { HomePage } from './components/home-page/home-page';
import { UpdateProductPage } from './components/update-product-page/update-product-page';
import { ProductInfo } from './components/product-info/product-info';
import { AddProduct } from './components/add-product/add-product';

export const routes: Routes = [
    { path: '', component: HomePage },
    { path: 'search', component: HomePage },
    { path: 'update-product/:id', component: UpdateProductPage },
    { path: 'add-product', component: AddProduct },
    { path: 'product-info/:id', component: ProductInfo },
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
