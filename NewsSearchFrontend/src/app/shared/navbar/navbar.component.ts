import { Component, OnInit } from '@angular/core';
import {NewsService} from '../../services/news.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(private _service:NewsService){}
  ngOnInit(): void {
    // this._service.getNews().subscribe((news)=>{
    //   console.log(news);
      
    // })
  }

}
