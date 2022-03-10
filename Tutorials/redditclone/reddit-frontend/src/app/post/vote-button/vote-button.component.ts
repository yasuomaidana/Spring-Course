import { Component, Input, OnInit } from '@angular/core';
import { faArrowUp,faArrowDown } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/shared/auth/auth.service';
import { PostModel } from 'src/app/shared/models/post-model';
import { VotePayload } from 'src/app/shared/payloads/vote.payload';
import { VoteType } from 'src/app/shared/payloads/votetype';
import { PostService } from 'src/app/shared/post/post.service';
import { VoteService } from 'src/app/shared/vote/vote.service';

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
  votePayload:VotePayload={voteType:undefined,postId:undefined}

  constructor(private voteService:VoteService,
    private authService:AuthService,
    private postService:PostService,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.updateVoteDetails();
  }

  upvotePost(){
    this.votePayload.voteType = VoteType.UPVOTE;
    this.vote()
  }

  downvotePost(){
    this.votePayload.voteType = VoteType.DOWNVOTE;
    this.vote()
  }

  vote() {
    this.votePayload.postId = this.post.id;
    this.voteService.vote(this.votePayload)
    .subscribe(()=>this.updateVoteDetails(),error=>{
      this.toastr.error(error.error.message);
      throw(error);
    });
  }

  updateVoteDetails() {
    this.postService.getPost(this.post.id).subscribe(post=>this.post=post);
  }

}
