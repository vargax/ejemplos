import { Injectable } from '@angular/core';
import {UserManager, User} from 'oidc-client';
import {Constants} from '../constants';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Manages OAuth low-level protocol details
  private _userManager: UserManager;
  // Encapsulates clint-side info about signed-in user (tokens, user profile info exposed as claims, tokens expiration timestamp)
  private _user: User;
  // A private rxjs Subject to raise and event when login state changes...
  private _loginChangedSubject = new Subject<boolean>();
  // A public observable produced by the Subject...
  loginChanged = this._loginChangedSubject.asObservable();

  constructor() {
    const stsSettings = {
      authority: Constants.stsAuthority,
      client_id: Constants.clientId,
      redirect_uri: `${Constants.clientRoot}signin-callback`,
      scope: 'openid profile projects-api',
      response_type: 'code', // for PKCE (set to 'id_token token' to use implicit flow instead of PKCE)
      post_logout_redirect_uri: `${Constants.clientRoot}signout-callback`,
      // metadata: {
      //   issuer: `${Constants.stsAuthority}`,
      //   authorization_endpoint: `${Constants.stsAuthority}authorize?audience=${Constants.audience}`,
      //   jwks_uri: `${Constants.stsAuthority}.well-known/jwks.json`,
      //   token_endpoint: `${Constants.stsAuthority}oauth/token`,
      //   userinfo_endpoint: `${Constants.stsAuthority}userinfo`,
      //   end_session_endpoint: `${Constants.stsAuthority}v2/logout?client_id=${Constants.clientId}&returnTo=${encodeURI(Constants.clientRoot)}signout-callback`
      // }
    };
    this._userManager = new UserManager(stsSettings);
  }

  login() {
    return this._userManager.signinRedirect();
  }

  isLoggedIn(): Promise<boolean> {
    return this._userManager.getUser().then(user => {
      const userCurrent = !!user && !user.expired;
      if (this._user !== user) {
        this._loginChangedSubject.next(userCurrent);
      }
      this._user = user;
      return userCurrent;
    });
  }

  // Returns a promise that produces a user
  completeLogin(): Promise<User> {
    return this._userManager.signinRedirectCallback().then(
        user => {
          this._user = user;
          this._loginChangedSubject.next(!!user && !user.expired); // Fire the loginChange observable to any code that's subscribed
          return user;
        }
    );
  }

  logout() {
    this._userManager.signoutRedirect();
  }

  completeLogout(): Promise<User> {
    this._user = null;
    return this._userManager.signinRedirectCallback();
  }

  getAccessToken(): Promise<String> {
    return this._userManager.getUser().then(user => {
      if (!!user && !user.expired) {
        return user.access_token;
      }
      else {
        return null;
      }
    })
  }

}
