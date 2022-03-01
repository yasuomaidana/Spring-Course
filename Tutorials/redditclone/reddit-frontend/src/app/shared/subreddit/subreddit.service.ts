import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SubredditModel } from '../models/subreddit-model';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  constructor(private http:HttpClient) { }

  getAllSubreddit():Observable<Array<SubredditModel>>{
    return this.http
    .get<Array<SubredditModel>>(environment.backendHost+"/subreddit");
  }
}
