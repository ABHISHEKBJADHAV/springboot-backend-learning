import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JobService } from '../../services/job';
import { ActivatedRoute, Router } from '@angular/router';
import { JobPost } from '../../DTO/job-post.model';

@Component({
  selector: 'app-update-job',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './update-job.html',
  styleUrl: './update-job.scss'
})
export class UpdateJob implements OnInit {

  jobForm: FormGroup;
  postId!: number;
  techOptions: string[] = ['Java', 'Python', 'Angular', 'Spring Boot', 'AWS', 'Docker'];

  constructor(private fb: FormBuilder, private jobService: JobService, private route: ActivatedRoute, private router: Router) {
    this.jobForm = this.fb.group({
      postId: [{ value: 0, disabled: true }, Validators.required],
      postProfile: ['', Validators.required],
      postDesc: ['', Validators.required],
      reqExperience: [0, Validators.required],
      postTechStack: [[], Validators.required]
    });
  }

  ngOnInit(): void {
    this.postId = Number(this.route.snapshot.paramMap.get('id'));
    console.log("update job postId", this.postId);
    this.jobService.getJobById(this.postId).subscribe((job: JobPost) => {
      this.jobForm.patchValue(job);
    });
  }

  onSubmit(): void {
    const updatedJob: JobPost = {
      ...this.jobForm.getRawValue() // includes disabled postId
    };

    this.jobService.updateJob(updatedJob).subscribe(() => {
      alert('Job updated successfully!');
      this.router.navigate(['/']);
    });
  }

}
