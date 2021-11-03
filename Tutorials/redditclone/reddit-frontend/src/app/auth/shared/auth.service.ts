import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { SignupRequestPayload } from "../signup/signup-request.payload";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient) { }

  signup(signupRequestPayload:SignupRequestPayload):Observable<any>{
    return this.httpClient.post(
      environment.backendHost+"/auth/signup",
      signupRequestPayload,
      {responseType:'text'});
  }
}
