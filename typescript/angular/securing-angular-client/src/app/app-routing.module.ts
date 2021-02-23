import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { ProjectListComponent } from './projects/project-list.component';
import { ProjectComponent } from './projects/project.component';
import { ContactUsComponent } from './home/contact-us.component';

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'contact-us', component: ContactUsComponent },
    { path: 'projects', component: ProjectListComponent},
    { path: 'project/:projectId', component: ProjectComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
