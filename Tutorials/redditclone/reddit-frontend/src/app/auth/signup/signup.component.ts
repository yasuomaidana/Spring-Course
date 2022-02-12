import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { SignupRequestPayload } from './signup-request.payload';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  signupRequestPayload: SignupRequestPayload;
  signupForm: FormGroup;

  constructor(private authService:AuthService,
    private router:Router,
    private toastr:ToastrService) {
    this.signupRequestPayload={ username:'',email:'',password:''};
    this.signupForm= new FormGroup({
      username: new FormControl('',Validators.required),
      email: new FormControl('',[Validators.required, Validators.email]),
      password: new FormControl('',Validators.required)
    });
  }

  ngOnInit(): void {

  }
  signup(){
    this.signupRequestPayload.email = this.signupForm.get('email')?.value;
    this.signupRequestPayload.password = this.signupForm.get('password')?.value;
    this.signupRequestPayload.username = this.signupForm.get('username')?.value;
    this.authService.signup(this.signupRequestPayload)
    .subscribe(data=>{
      this.router.navigate(['/login'],
      {queryParams:{registered:'true'}});
    },()=>{
      this.toastr.error('Registration Failed! Please try again')
    });
  }
}
