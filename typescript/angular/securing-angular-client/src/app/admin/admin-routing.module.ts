import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ManageProjectsComponent } from './manage-projects.component';
import { ManagePermissionsComponent } from './manage-permissions.component';

const routes: Routes = [
  { path: 'admin', component: ManageProjectsComponent },
  { path: 'admin/manage-permissions/:projectId', component: ManagePermissionsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule { }

export const routedComponents = [ManageProjectsComponent];