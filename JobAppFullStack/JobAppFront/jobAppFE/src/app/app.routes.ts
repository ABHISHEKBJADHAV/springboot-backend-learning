import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home';
import { AddJob } from './components/add-job/add-job';
import { UpdateJob } from './components/update-job/update-job';
import { JobDetailsCard } from './components/job-details-card/job-details-card';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'add-job', component: AddJob },
    { path: 'update-job/:id', component: UpdateJob },
    { path: 'job-details/:jobId', component: JobDetailsCard },
    // { path: 'update-job/:id', component: JobUpdateComponent } // optional for later
];
