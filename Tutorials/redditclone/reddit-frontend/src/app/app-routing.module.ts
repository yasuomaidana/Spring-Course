import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { UserProfileComponent } from './auth/user-profile/user-profile.component';
import { HomeComponent } from './home/home/home.component';
import { CreatePostComponent } from './post/create-post/create-post.component';
import { ViewPostComponent } from './post/view-post/view-post.component';
import { CreateSubredditComponent } from './subreddit/create-subreddit/create-subreddit.component';
import { ListSubredditsComponent } from './subreddit/list-subreddits/list-subreddits.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'signup', component:SignupComponent },
  {path:'login',component:LoginComponent},
  {path:'user-profile/:name',component:UserProfileComponent},
  {path:'create-post',component:CreatePostComponent},
  {path:'create-subreddit',component:CreateSubredditComponent},
  {path:'list-subreddits',component:ListSubredditsComponent},
  {path:'subreddits',redirectTo:'list-subreddits'},
  {path:'view-post/:id',component:ViewPostComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
