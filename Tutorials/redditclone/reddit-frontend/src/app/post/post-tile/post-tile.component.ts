import { Component, OnInit } from '@angular/core';
import { PostModel } from 'src/app/shared/models/post-model';
import { PostService } from 'src/app/shared/post/post.service';
import { faComments }from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-post-tile',
  templateUrl: './post-tile.component.html',
  styleUrls: ['./post-tile.component.scss']
})
export class PostTileComponent implements OnInit {
  posts$:Array<PostModel>=[];
  faComments = faComments;
  constructor(private postService:PostService) { }

  ngOnInit(): void {
    this.postService.getAllPost().subscribe(posts=>{
      this.posts$=posts;
    });
  }

  goToPost(postId:Number){}
}
