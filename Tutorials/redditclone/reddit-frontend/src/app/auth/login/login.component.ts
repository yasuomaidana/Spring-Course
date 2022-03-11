import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../shared/auth/auth.service';
import { LoginRequestPayload } from '../../shared/payloads/login-request.payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequestPayload: LoginRequestPayload = {username:'',password:''};
  registerSuccessMessage: string ='';
  isError: boolean = false;

  constructor(private authService:AuthService,
    private activatedRoute:ActivatedRoute,
    private router:Router,
    private toastr:ToastrService) {
    this.loginForm = new FormGroup({
      username: new FormControl('',Validators.required),
      password: new FormControl('',Validators.required)
    });
   }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      params=>{
        if(params.registered !==undefined && params.registered==='true'){
          this.toastr.success('Signup Succesfully');
          this.registerSuccessMessage='Please Check your inbox for activation link and'
          +" active your account before you Login!";
        }
      }
    );
   }
  login(){
    this.loginRequestPayload.username = this.loginForm.get('username')?.value;
    this.loginRequestPayload.password = this.loginForm.get('password')?.value;
    this.authService.login(this.loginRequestPayload).subscribe(data=>{
      this.isError = false;
      this.router.navigateByUrl('/');
    },error=>{
      console.log(error);
      this.isError = true;
    });
  }
}
