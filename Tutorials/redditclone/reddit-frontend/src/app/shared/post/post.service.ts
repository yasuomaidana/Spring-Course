import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PostModel } from '../models/post-model';
import { CreatePostPayload } from '../payloads/create-post.payload';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http:HttpClient) { }

  getAllPost():Observable<Array<PostModel>>{
    return this.http.get<Array<PostModel>>(
      environment.backendHost+"/posts");
  }

  createPost(postPayload: CreatePostPayload): Observable<any> {
    return this.http.post(environment.backendHost+"/posts", postPayload);
  }

  getPost(postId: number):Observable<PostModel> {
    return this.http.get<PostModel>(
      environment.backendHost+`/posts/${postId}`);
  }
}
