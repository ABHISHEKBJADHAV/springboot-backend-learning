import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { JobPost } from '../../DTO/job-post.model';
import { JobService } from '../../services/job';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.html',
  styleUrls: ['./home.scss']
})
export class HomeComponent implements OnInit {
  searchTerm: String = "";
  jobPosts: JobPost[] = [];

  constructor(private jobService: JobService, private router: Router) { }

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.jobService.getAllJobs().subscribe(data => this.jobPosts = data);
  }

  viewJobDetails(postId: number): void {
    this.router.navigate(['/job-details', postId]);
  }

  goToUpdateJob(postId: number): void {
    this.router.navigate(['/update-job', postId]);
  }

  deleteJob(postId: number): void {
    this.jobService.deleteJob(postId).subscribe(() => this.loadJobs());
  }

  goToAddJob(): void {
    this.router.navigate(['/add-job']);
  }

  onSearch() {
    if (this.searchTerm == "") this.loadJobs();
    else this.jobService.searchJob(this.searchTerm).subscribe((filteredJobPosts) => this.jobPosts = filteredJobPosts)
  }
}
