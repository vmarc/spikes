import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../domain/api-response';
import { SubsetNetworksPage } from '../domain/subset-networks-page';

@Injectable()
export class NetworksService {
  constructor(private http: HttpClient) {}

  networks(): Observable<ApiResponse<SubsetNetworksPage>> {
    return this.http.get('/api/nl/cycling/networks');
  }
}
