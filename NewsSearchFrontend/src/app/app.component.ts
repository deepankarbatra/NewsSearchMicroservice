import { Component } from '@angular/core';
import { NewsService } from './services/news.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'NewsSearch';
  searchKeyword:string = '';
  offlineMode: boolean=false;
  constructor(private newService: NewsService) {}

  onSearch(event: Event): void {
    event.preventDefault();
    if(this.searchKeyword.trim()){
      this.newService.updateKeyword(this.searchKeyword);
    } else {
      this.newService.updateKeyword('');
    }
  }
  onOfflineToggle(): void {
    this.newService.setOfflineMode(this.offlineMode).subscribe(
      (response) => {
        console.log('Offline mode updated successfully', response);
      },
      (error) => {
        console.error('Error updating offline mode', error);
        this.offlineMode = !this.offlineMode;
      }
    );
  }
}
