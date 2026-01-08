import { Component, OnInit } from '@angular/core';
import { JobService } from '../../services/job';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { JobPost } from '../../DTO/job-post.model';

@Component({
  selector: 'app-job-details-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './job-details-card.html',
  styleUrl: './job-details-card.scss'
})
export class JobDetailsCard implements OnInit {
  job: JobPost = {
    postId: 0,
    postProfile: '',
    postDesc: '',
    reqExperience: 0,
    postTechStack: []
  }

  constructor(private service: JobService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const jobId: number = Number(this.route.snapshot.paramMap.get('jobId'))
    this.service.getJobById(jobId).subscribe((job: JobPost) => {
      this.job = job;
    })
  }
}
