import { Component, OnInit } from '@angular/core';
import { SubredditModel } from 'src/app/shared/models/subreddit-model';
import { SubredditService } from 'src/app/shared/subreddit/subreddit.service';

@Component({
  selector: 'app-subreddit-side-bar',
  templateUrl: './subreddit-side-bar.component.html',
  styleUrls: ['./subreddit-side-bar.component.scss']
})
export class SubredditSideBarComponent implements OnInit {

  displayViewAll:boolean;
  subReddits:Array<SubredditModel>;
  constructor(private subRedditService:SubredditService) { }

  ngOnInit(): void {
    this.subRedditService.getAllSubreddit()
    .subscribe(data=>{
      console.log("entra", data);
      if(data.length>4){
        this.subReddits = data.splice(0,3);
        this.displayViewAll = true;
      }
      else this.subReddits=data;
    });
    console.log(this.subReddits);
  }

}
