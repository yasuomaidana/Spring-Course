import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLoggedIn:boolean=false;
  username:string;

  constructor(private authService:AuthService, private router:Router,private toastr:ToastrService) {
    this.authService.loggedIn.subscribe((data:boolean)=>{
      this.isLoggedIn=data;
      this.toastr.success('Login Successful');
    });
    this.authService.username.subscribe((data:string)=>this.username=data);
   }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  goToUserProfile(){
    this.router.navigateByUrl("/user-profile/"+this.authService.getUserName());
  }
  logout(){
    this.authService.logout();
    this.router.navigateByUrl('').then(() => {
      window.location.reload();
    })
  }
}
