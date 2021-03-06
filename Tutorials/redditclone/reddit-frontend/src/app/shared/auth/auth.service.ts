import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { SignupRequestPayload } from "../payloads/signup-request.payload";
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { LoginRequestPayload } from '../payloads/login-request.payload';
import { LoginResponse } from '../payloads/login-response.payload';
import { LocalStorageService } from 'ngx-webstorage';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  @Output() loggedIn:EventEmitter<boolean> = new EventEmitter();
  @Output() username:EventEmitter<string> = new EventEmitter();
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

      this.loggedIn.emit(true);
      this.username.emit(data.username);
      return true;
    }));
  }

  getJwtToken(){
    return this.localStorage.retrieve('authenticationToken');
  }

  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }

  refreshToken() {
    const refrehsTokenPayload = {
      refreshToken: this.getRefreshToken(),
      username: this.getUserName()
    };
    return this.httpClient.post<LoginResponse>(
      environment.backendHost+"/auth/refresh/token",
      refrehsTokenPayload
    ).pipe(tap(response=>{
      this.localStorage.store('authenticationToken',response.authenticationToken);
      this.localStorage.store('expiresAt',response.expiresAt)
    }));
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }

  logout(){
    this.httpClient.post(environment.backendHost+"/auth/logout",
    this.getRefreshToken,{responseType:'text'})
    .subscribe(data=>console.log(data),error=>throwError(error));
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
  }
}
