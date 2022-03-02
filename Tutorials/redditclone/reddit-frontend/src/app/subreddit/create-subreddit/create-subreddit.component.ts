import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SubredditModel } from 'src/app/shared/models/subreddit-model';
import { SubredditService } from 'src/app/shared/subreddit/subreddit.service';

@Component({
  selector: 'app-create-subreddit',
  templateUrl: './create-subreddit.component.html',
  styleUrls: ['./create-subreddit.component.scss']
})
export class CreateSubredditComponent implements OnInit {
  createSubredditForm: FormGroup;
  subredditModel: SubredditModel={
    subRedditName:"",
    description:""
  };

  constructor(private router: Router,
    private subredditService: SubredditService) {
      this.createSubredditForm = new FormGroup({
        title: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required)
      });
   }

  ngOnInit(): void {
  }

  discard() {
    this.router.navigateByUrl('/');
  }

  createSubreddit() {
    this.subredditModel.subRedditName = this.createSubredditForm.get('title')?.value;;
    this.subredditModel.description = this.createSubredditForm.get('description')?.value;
    this.subredditService.createSubreddit(this.subredditModel).subscribe(data => {
      this.router.navigateByUrl('/');
    }, (error) => {
      console.log('Error occurred');
    })
  }

}
