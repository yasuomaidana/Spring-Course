import { Component, OnInit } from '@angular/core';
import { PostModel } from 'src/app/shared/models/post-model';
import { PostService } from 'src/app/shared/post/post.service';
import { faArrowUp,faArrowDown,faComments } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  posts$:Array<PostModel>=[];
  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  faComments = faComments;
  upvoteColor = "";
  downvoteColor = "";

  constructor(private postService:PostService) { }

  ngOnInit(): void {
    this.postService.getAllPost().subscribe(posts=>{
      this.posts$=posts;
    })
  }

  upvotePost(){}
  downvotePost(){}
  goToPost(postId:Number){}
}
