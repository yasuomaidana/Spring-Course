import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VotePayload } from '../payloads/vote.payload';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor(private http:HttpClient) { }

  vote(votePayload:VotePayload):Observable<any>{
    return this.http.post(environment.backendHost+"/votes",votePayload);
  }
}
