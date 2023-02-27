import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { API_URL } from '../app.constants';
import { User } from './user.model';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';



export interface AuthResponse {
  username: string;
  jwtAuthToken: string;
  serverCurrentTime: number;
  tokenExpirationTime: number;
}

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  userName:any;
  errorMessage = 'Invalid Username or Password';
  user = new BehaviorSubject<User | null>(null);
  timeout = new Subject<boolean>();
  private tokenExirationTimer: any;
  constructor(
    private http: HttpClient,
    private router: Router
  ) { }



  isUserLoggedIn() {
   return this.tokenExirationTimer;
  }

  executeAuthenticationService(inputFields: {username: string, password: string}) {

    return this.http.post<AuthResponse>(`${API_URL}/login`, inputFields)
      .pipe(
        catchError(this.handleError),
        tap((response) => {
          this.handleAuthentication(
            response['username'],
            response['jwtAuthToken'],
            response['serverCurrentTime'],
            response['tokenExpirationTime']
          );
        })
      )
  }

  handleAuthentication(
    username: string,
    jwtAuthToken: string,
    serverCurrentTime: number,
    tokenExpirationTime: number
  ) {
    const user = new User(
      username,
      jwtAuthToken,
      serverCurrentTime,
      tokenExpirationTime
    );
    this.storeUser(user);
    this.autoLogout(tokenExpirationTime - serverCurrentTime);
    this.user.next(user);
    this.userName=username;
  }


  logout() {
    this.user.next(null);
    this.router.navigate(['./home']);
    this.removeUser();

    if (this.tokenExirationTimer) {
      clearTimeout(this.tokenExirationTimer);
    }
    this.tokenExirationTimer = null;

  }

  autoLogout(expirationDuration: number) {
    this.tokenExirationTimer = setTimeout(() => {
      this.timeout.next(true);
      this.logout();
    }, expirationDuration);
  }

  handleError(errorResponse: HttpErrorResponse) {
    

    if (!errorResponse.error || !errorResponse.error.error) {
      return throwError(this.errorMessage);
    }

    if (errorResponse.error.message) {
      if (errorResponse.error.message === 'No value present')
        this.errorMessage = 'Invalid Username';
      else this.errorMessage = 'Invalid Username or Password';
    }
    return throwError(this.errorMessage);
  }
  storeUser(user: User) {
    sessionStorage.setItem('userData', JSON.stringify(user));
  }

  removeUser() {
    sessionStorage.removeItem('userData');
  }

}


