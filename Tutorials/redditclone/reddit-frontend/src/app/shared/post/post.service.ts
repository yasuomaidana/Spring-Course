import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PostModel } from '../models/post-model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http:HttpClient) { }

  getAllPost():Observable<Array<PostModel>>{
    return this.http.get<Array<PostModel>>(
      environment.backendHost+"/posts");
  }
}
