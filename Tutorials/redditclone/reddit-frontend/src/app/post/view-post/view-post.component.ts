import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { throwError } from 'rxjs';
import { PostModel } from 'src/app/shared/models/post-model';
import { PostService } from 'src/app/shared/post/post.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.scss']
})
export class ViewPostComponent implements OnInit {

  postId: number;
  post$: PostModel={
    id: 0,
    postName: '',
    description: '',
    subRedditName: ''
  };
  constructor(private postService: PostService, private activateRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.postId = this.activateRoute.snapshot.params.id;
    this.postService.getPost(this.postId).subscribe(data => {
      this.post$ = data;
    }, error => {
      throwError(error);
    });
  }

}
