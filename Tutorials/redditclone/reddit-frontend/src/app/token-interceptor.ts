import { Injectable, Injector } from "@angular/core";
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { AuthService } from "./shared/auth/auth.service";
import { BehaviorSubject, Observable, throwError } from "rxjs";
import { catchError, filter, switchMap, take } from "rxjs/operators";
import { LoginResponse } from "./shared/payloads/login-response.payload";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor{

  isTokenRefreshing: boolean = false;
  refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null);
  constructor(private authService:AuthService){

  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
      if(this.noneedsToken(req.url,req.method)) return next.handle(req);
      const jwtToken = this.authService.getJwtToken();
      if (jwtToken) {
        return next.handle(this.addToken(req, jwtToken)).pipe(catchError(error => {
          if (error instanceof HttpErrorResponse
            && error.status === 403) {
              return this.handleAuthErrors(req, next);
            }
          else return throwError(error);
        }));
      }
      return next.handle(req);
  }

  private noneedsToken(url:string,method:string):boolean{
    var safepatterns:RegExp[]=[];
    //Safe gets
    if(method=="POST"){
      safepatterns = [/.+auth\//];
    }
    //Safe posts
    else if(method=="GET"){
      safepatterns = [/.+subreddit$/,/.+posts$/];
    }

    if(safepatterns.find(pattern=>pattern.test(url))) return true;

    return false;
  }
  addToken(req:HttpRequest<any>,jwtToken:any){
    return req.clone({
      headers:req.headers.set('Authorization', 'Bearer '+jwtToken)
    });
  }

  private handleAuthErrors(req: HttpRequest<any>, next: HttpHandler)
        : Observable<HttpEvent<any>> {
        if (!this.isTokenRefreshing) {
            this.isTokenRefreshing = true;
            this.refreshTokenSubject.next(null);

            return this.authService.refreshToken().pipe(
                switchMap((refreshTokenResponse: LoginResponse) => {
                    this.isTokenRefreshing = false;
                    this.refreshTokenSubject
                        .next(refreshTokenResponse.authenticationToken);
                    return next.handle(this.addToken(req,
                        refreshTokenResponse.authenticationToken));
                })
            )
        } else {
            return this.refreshTokenSubject.pipe(
                filter(result => result !== null),
                take(1),
                switchMap((res) => {
                    return next.handle(this.addToken(req,
                        this.authService.getJwtToken()))
                })
            );
        }
    }
}
