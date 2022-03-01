import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-subreddit-side-bar',
  templateUrl: './subreddit-side-bar.component.html',
  styleUrls: ['./subreddit-side-bar.component.scss']
})
export class SubredditSideBarComponent implements OnInit {

  displayViewAll:boolean=false;
  subreddits=null;
  constructor() { }

  ngOnInit(): void {
  }

}
