import { NgModule } from '@angular/core';
import {ProductListComponent} from './product-list.component';
import {ProductDetailComponent} from './product-detail.component';
import {RouterModule} from '@angular/router';
import {ProductDetailGuard} from './product-detail.guard';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
      ProductListComponent,
      ProductDetailComponent
  ],
  imports: [
      SharedModule,
      RouterModule.forChild([
        {path: 'products', component: ProductListComponent},
        {
          path: 'products/:id',
          canActivate: [ProductDetailGuard],
          component: ProductDetailComponent
        },
      ])
  ]
})
export class ProductModule { }
