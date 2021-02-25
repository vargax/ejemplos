import { Injectable } from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {from, Observable} from 'rxjs';
import {CoreModule} from './core.module';
import {AuthService} from './auth.service';
import {Constants} from '../constants';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
  providedIn: CoreModule
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private _authService: AuthService, private _router: Router) { }

  // This method will intercept all HTTP client calls to add the access token (if required)
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Only add the access token to the calls that go to my API
    if (req.url.startsWith(Constants.apiRoot)) {
      return from(this._authService.getAccessToken().then(token => {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
        const authReq = req.clone({headers});
        return next.handle(authReq).pipe(tap(_ => {}, error => {
          const respError = error as HttpErrorResponse;
          if (respError && (respError.status === 401 || respError.status === 403)) {
            this._router.navigate(['/unauthorized']);
          }
        })).toPromise();
      }));
    }
    else {
      return next.handle(req); // Pass the request without adding Authorization Headers
    }

  }
}
