import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    if (token) {
      req = req.clone({
         // Modify the request here ,add headers
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    // Pass the modified request to the next interceptor or the backend
    return next.handle(req);
  }
}
