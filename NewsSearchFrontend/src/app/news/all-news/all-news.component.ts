import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-all-news',
  templateUrl: './all-news.component.html',
  styleUrls: ['./all-news.component.css']
})
export class AllNewsComponent implements OnInit, OnDestroy {
  constructor(private _service: NewsService) { }
  headlines:any = [];
  currentPage: number = 1;
  totalPages: number = 100;
  loading: boolean = false;
  loadingTime: number = 0;
  previousPage: number = 0;
  nextPage: number = 2;
  private subscription: Subscription = new Subscription();
  ngOnInit(): void {
    this.fetchNews();
    this.subscription = this._service.getKeywordObservable().subscribe(() => {
      this.fetchNews();
    });
    this.subscription = this._service.getOfflineModeObservable().subscribe(() => {
      this.fetchNews();
    })

  }

  fetchNews(): void {
    const params = {
      page: this.currentPage.toString(),
    }
    this.loading = true;
    this._service.getNews(params).subscribe((news) => {
      this.headlines = news.articles;
      this.totalPages = news.totalPages;
      this.loadingTime = news.timeTakenMs;
      this.loading = false;
      this.previousPage = news.prevPage || 0;
      this.nextPage = news.nextPage;
    },
    (error) => {
      console.log(error);
      this.loading = false;
      
    }
  )
  }
  changePage(newPage: number) {
    if (newPage > 0 && newPage <= this.totalPages) {
      this.currentPage = newPage;
      this.fetchNews();
    }
  }
  ngOnDestroy(): void {
    if(this.subscription){
      this.subscription.unsubscribe();
    }
  }
}
