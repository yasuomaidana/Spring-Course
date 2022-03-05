import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CommentPayload } from '../payloads/comment.payload';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor(private http:HttpClient) { }

  getAllCommentsForPost(postId: number):Observable<CommentPayload[]> {
    return this.http.get<CommentPayload[]>(environment.backendHost+'/comments/by-post/' + postId);
  }

  postComment(commentPayload: CommentPayload):Observable<any> {
    return this.http.post<any>(environment.backendHost+'/comments/', commentPayload);
  }

  getAllCommentsByUser(name: string):Observable<CommentPayload[]> {
    return this.http.get<CommentPayload[]>(environment.backendHost+"/comments/by-user/" + name);
  }
}
