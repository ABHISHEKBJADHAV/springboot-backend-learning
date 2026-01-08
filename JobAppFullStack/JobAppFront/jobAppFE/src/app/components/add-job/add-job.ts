import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JobService } from '../../services/job';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-job',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-job.html',
  styleUrl: './add-job.scss'
})
export class AddJob {
  jobForm: FormGroup;
  techOptions: string[] = ['Java', 'Python', 'Angular', 'Spring Boot', 'AWS', 'Docker'];

  constructor(private fb: FormBuilder, private jobService: JobService) {
    this.jobForm = this.fb.group({
      postId: [0, Validators.required],
      postProfile: ['', Validators.required],
      postDesc: ['', Validators.required],
      reqExperience: [0, Validators.required],
      postTechStack: [[], Validators.required] // initialize as array
    });
  }

  onSubmit(): void {
    const jobPost = this.jobForm.value; // postTechStack is already an array
    this.jobService.addJob(jobPost).subscribe(() => {
      alert('Job added successfully!');
      this.jobForm.reset();
    });
  }

}
