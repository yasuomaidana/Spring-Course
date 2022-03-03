import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { throwError } from 'rxjs';
import { CommentService } from 'src/app/shared/comment/comment.service';
import { PostModel } from 'src/app/shared/models/post-model';
import { CommentPayload } from 'src/app/shared/payloads/comment.payload';
import { PostService } from 'src/app/shared/post/post.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.scss']
})
export class ViewPostComponent implements OnInit {

  postId: number;
  post: PostModel={
    id: 0,
    postName: '',
    description: '',
    subRedditName: ''
  };
  commentForm: FormGroup;
  commentPayload: CommentPayload;
  comments: CommentPayload[];
  constructor(private postService: PostService,
    private activateRoute: ActivatedRoute,
    private commentService: CommentService,
    private router: Router) {
    this.postId = this.activateRoute.snapshot.params.id;
    this.commentForm = new FormGroup({
      text: new FormControl('', Validators.required)
    });
    this.commentPayload = {
      text: '',
      postId: this.postId.toString()
    };
  }

  ngOnInit(): void {
    this.getPostById();
    this.getCommentsForPost();
  }

  postComment() {
    this.commentPayload.text = this.commentForm.get('text')?.value;
    this.commentService.postComment(this.commentPayload).subscribe(data => {
      this.commentForm.get('text')?.setValue('');
      this.getCommentsForPost();
    }, error => {
      throwError(error);
    })
  }

  private getPostById() {
    this.postService.getPost(this.postId).subscribe(data => {
      this.post = data;
    }, error => {
      throwError(error);
    });
  }

  private getCommentsForPost() {
    this.commentService.getAllCommentsForPost(this.postId).subscribe(data => {
      this.comments = data;
    }, error => {
      throwError(error);
    });
  }
}
