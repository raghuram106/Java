import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDataService } from '../service/data/user-data.service';
import { User } from '../users/users.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user!:User
  errorMessage=''
  successMessage=''
  constructor(private router:Router, private userDataService:UserDataService) { }

  ngOnInit(): void {
    this.user= new User("","","","","","","");
  }
  registerUser(){
    this.userDataService.registerUser(this.user).subscribe(
      response=>{
        this.successMessage='User Registered Successfully'
      },
      error => {this.errorMessage=error.error.message;

      }
    );
  }

}
