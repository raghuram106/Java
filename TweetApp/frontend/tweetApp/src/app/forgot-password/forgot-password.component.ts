import { Component, NgModule, OnInit } from '@angular/core';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { UserDataService } from '../service/data/user-data.service';
import { User } from '../users/users.component';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})

export class ForgotPasswordComponent implements OnInit {
 
  user!:User
  errorMessage=''
  successMessage=''
  constructor(private userDataService : UserDataService,
    private service:BasicAuthenticationService,) { }

  ngOnInit(): void {
    this.user = new User("","","","","","","");
    this.user.loginId=this.service.userName;
  }

  forgotPassword(){
    this.userDataService.forgotPassword(this.user.loginId,this.user).subscribe(
      reponse=>{
          this.successMessage="Password Changed Successfully"
        },
      error => {this.errorMessage=error.error.message;
        
      }
    );
  }

}
