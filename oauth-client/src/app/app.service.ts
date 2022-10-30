import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable()
export class AppService {
  constructor(private http: HttpClient) {
  }

  getInfo(): Observable<string> {
    return this.http.get<string>('/api/info');
  }

  login() {
    const authorizationUrl = 'oauth2/authorization/osm';
    const successUrl = window.location.href;
    const failureUrl = window.location.origin + '/login-failure';
    window.location.href = `${authorizationUrl}?successUrl=${successUrl}&failureUrl=${failureUrl}`;
  }

  logout() {
    return this.http.get('/api/logout');
    // TODO window.location.reload(); not needed?
  }
}
