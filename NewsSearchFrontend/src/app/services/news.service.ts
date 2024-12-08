import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable,Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  URL:string = '';
  keyword:string = "";
  private baseUrl = "http://localhost:8080";
  private keywordSubject = new Subject<string>();
  private offlineModeSubject = new Subject<boolean>();
  offlineMode:boolean = false;
  constructor(private _http: HttpClient) { 
    this.updateKeyword('')
  }

  updateKeyword(newKeyword:string) {
    this.keyword = newKeyword;
    this.URL = `${this.baseUrl}/news/search?keyword=${this.keyword}`;
    this.keywordSubject.next(this.keyword);
  }

  setOfflineMode(isOffline: boolean): Observable<any> {
    this.offlineMode = isOffline;
    this.updateURL();
    this.offlineModeSubject.next(this.offlineMode);
    return this.getNews();
  }

  private updateURL(): void {
    this.URL = `${this.baseUrl}/news/search?keyword=${this.keyword}`;
    if (this.offlineMode) {
      this.URL += `&offlineMode=true`;
    }
  }

  getKeywordObservable(): Observable<string> {
    return this.keywordSubject.asObservable();
  }

  getOfflineModeObservable(): Observable<boolean> {
    return this.offlineModeSubject.asObservable();
  }

  getNews(params?: any) : Observable<any> {
    const page = params?.page ?? 1;
    return this._http.get(this.URL+`&page=${page}`);
  }
}
