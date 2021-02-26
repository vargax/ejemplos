import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';

import { AddProjectDialogComponent } from './add-project-dialog.component';
import { AddProjectUserDialogComponent } from './add-project-user-dialog.component';
import { AdminRoutingModule } from './admin-routing.module';
import { DeleteDialogComponent } from './delete-dialog.component';
import { ManagePermissionsComponent } from './manage-permissions.component';
import { ManageProjectsComponent } from './manage-projects.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    MatFormFieldModule,
    MatDialogModule,
    MatButtonModule,
    MatToolbarModule,
    MatTableModule,
    MatInputModule,
    MatSelectModule,
    AdminRoutingModule
  ],
  exports: [],
  declarations: [
    ManageProjectsComponent,
    ManagePermissionsComponent,
    AddProjectDialogComponent,
    DeleteDialogComponent,
    AddProjectUserDialogComponent
  ],
  providers: [],
  entryComponents: [
    AddProjectDialogComponent,
    DeleteDialogComponent,
    AddProjectUserDialogComponent
  ]
})
export class AdminModule {}
