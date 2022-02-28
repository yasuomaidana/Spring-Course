import { Component, Input, OnInit } from '@angular/core';
import { faArrowUp,faArrowDown } from '@fortawesome/free-solid-svg-icons';
import { PostModel } from 'src/app/shared/models/post-model';

@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.scss']
})
export class VoteButtonComponent implements OnInit {

  @Input() post: PostModel;

  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  upvoteColor = "";
  downvoteColor = "";

  constructor() { }

  ngOnInit(): void {
  }

  upvotePost(){}

  downvotePost(){}
}
