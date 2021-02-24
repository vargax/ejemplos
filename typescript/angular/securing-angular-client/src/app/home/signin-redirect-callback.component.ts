import { Component, OnInit } from '@angular/core';
import {AuthService} from '../core/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signin-redirect-callback',
  template: `<div></div>`
})
// Mock component to handle redirection
export class SigninRedirectCallbackComponent implements OnInit {

  constructor(private _authService: AuthService, private _router: Router) { }

  ngOnInit() {
    this._authService.completeLogin().then(user => {
      // Navigate to the root view and remove the signin redirect component (this) from the back navigation stack
      this._router.navigate(['/'], {replaceUrl: true});
    });
  }

}
