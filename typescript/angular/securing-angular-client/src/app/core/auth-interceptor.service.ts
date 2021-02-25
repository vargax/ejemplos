import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {from, Observable} from 'rxjs';
import {CoreModule} from './core.module';
import {AuthService} from './auth.service';
import {Constants} from '../constants';

@Injectable({
  providedIn: CoreModule
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private _authService: AuthService) { }

  // This method will intercept all HTTP client calls to add the access token (if required)
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Only add the access token to the calls that go to my API
    if (req.url.startsWith(Constants.apiRoot)) {
      return from(this._authService.getAccessToken().then(token => {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
        const authReq = req.clone({headers});
        return next.handle(authReq).toPromise()
      }));
    }
    else {
      return next.handle(req); // Pass the request without adding Authorization Headers
    }

  }
}
