import { Component, OnInit } from '@angular/core';
import { throwError } from 'rxjs';
import { SubredditModel } from 'src/app/shared/models/subreddit-model';
import { SubredditService } from 'src/app/shared/subreddit/subreddit.service';

@Component({
  selector: 'app-list-subreddits',
  templateUrl: './list-subreddits.component.html',
  styleUrls: ['./list-subreddits.component.scss']
})
export class ListSubredditsComponent implements OnInit {
  subreddits: Array<SubredditModel>;

  constructor(private subredditService: SubredditService) { }

  ngOnInit(): void {
    this.subredditService.getAllSubreddit().subscribe(data => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    });
  }

}
