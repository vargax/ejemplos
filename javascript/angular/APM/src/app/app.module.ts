import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProductListComponent } from './products/product-list.component';
import {ConvertToSpacesPipe} from './shared/convert-to-spaces.pipe';
import {StartComponent} from './shared/start.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {ProductDetailComponent} from './products/product-detail.component';
import {WelcomeComponent} from './home/welcome.component';
import {ProductDetailGuard} from "./products/product-detail.guard";

@NgModule({
    declarations: [
        AppComponent,
        ProductListComponent,
        ConvertToSpacesPipe,
        StartComponent,
        ProductDetailComponent,
        WelcomeComponent
    ],
  imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot([
            {path: 'products', component: ProductListComponent},
            {
                path: 'products/:id',
                canActivate: [ProductDetailGuard],
                component: ProductDetailComponent
            },
            {path: 'welcome', component: WelcomeComponent},
            {path: '', redirectTo: 'welcome', pathMatch: 'full'},
            {path: '**', redirectTo: 'welcome', pathMatch: 'full'}
        ])
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
