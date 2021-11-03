import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { SignupRequestPayload } from "../signup/signup-request.payload";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { LoginRequestPayload } from '../login/login-request.payload';
import { LoginResponse } from '../login/login-response.payload';
import { LocalStorageService } from 'ngx-webstorage';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient,
    private localStorage:LocalStorageService) { }

  signup(signupRequestPayload:SignupRequestPayload):Observable<any>{
    return this.httpClient.post(
      environment.backendHost+"/auth/signup",
      signupRequestPayload,
      {responseType:'text'});
  }

  login(loginRequestPayload:LoginRequestPayload):Observable<boolean>{
    return this.httpClient.post<LoginResponse>(
      environment.backendHost+"/auth/login",
      loginRequestPayload
    ).pipe(map(data=>{
      this.localStorage.store('authenticationToken',data.authenticationToken);
      this.localStorage.store('username',data.username);
      this.localStorage.store('refreshToken',data.refreshToken);
      this.localStorage.store('expiresAt',data.expiresAt);
      return true;
    }));
  }
}
