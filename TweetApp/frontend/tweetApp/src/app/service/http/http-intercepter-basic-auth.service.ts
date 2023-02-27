import { HttpInterceptor, HttpHandler, HttpRequest,  HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { exhaustMap, take } from 'rxjs/operators';
import { BasicAuthenticationService } from '../basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor{
  [x: string]: any;

  constructor(
    private basicAuthenticationService : BasicAuthenticationService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){
    return this.basicAuthenticationService.user.pipe(
      take(1),
      exhaustMap((user) => {
        if (!user) return next.handle(request);

        const modifiedReq = request.clone({
          headers: new HttpHeaders({
            Authorization: 'Bearer ' + user.token,
          }),
        });
        return next.handle(modifiedReq);
      })
    );
  }


}
