import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { App } from './app';
import { HomePage } from './components/home-page/home-page';
import { routes } from './app.routes';
import { CommonModule } from '@angular/common';
import { ProductCard } from './components/product-card/product-card';
import { ProductInfo } from './components/product-info/product-info';
import { UpdateProductPage } from './components/update-product-page/update-product-page';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MainLayout } from './components/main-layout/main-layout';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from './shared/material/material-module';
import { AddProduct } from './components/add-product/add-product';

@NgModule({
    declarations: [
        App,
        HomePage,
        ProductInfo,
        UpdateProductPage,
        AddProduct,
        MainLayout
    ],
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        ProductCard,
        ReactiveFormsModule,
        MaterialModule,
        HttpClientModule,
        RouterModule.forRoot(routes)
    ],
    bootstrap: [App]
})
export class AppModule { }
