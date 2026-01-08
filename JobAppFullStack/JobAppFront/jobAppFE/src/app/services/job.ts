import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobPost } from '../DTO/job-post.model';

@Injectable({
  providedIn: 'root'
})
export class JobService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAllJobs(): Observable<JobPost[]> {
    return this.http.get<JobPost[]>(`${this.baseUrl}/jobPosts`);
  }

  getJobById(id: number): Observable<JobPost> {
    return this.http.get<JobPost>(`${this.baseUrl}/jobPost/${id}`);
  }

  addJob(jobPost: JobPost): Observable<JobPost> {
    return this.http.post<JobPost>(`${this.baseUrl}/jobPost`, jobPost);
  }

  updateJob(jobPost: JobPost): Observable<JobPost> {
    return this.http.put<JobPost>(`${this.baseUrl}/jobPost`, jobPost);
  }

  deleteJob(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/jobPost/${id}`);
  }

  searchJob(keyword: String): Observable<JobPost[]> {
    return this.http.get<JobPost[]>(`${this.baseUrl}/jobPost/search/${keyword}`);
  }

}
