import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username= ''
  password= ''
  errorMessage='Invalid Credentials'
  invalidLogin = false
  constructor(private router:Router, private authenticationService: BasicAuthenticationService) { }

  ngOnInit(): void {
  }
  handleLogin(){
    console.log(this.username);

    const username = this.username;
    const password = this.password;

    this.authenticationService.executeAuthenticationService({username,password}).subscribe(
      data => { 
        this.router.navigate(['welcome'])  
      },
      error => {
        this.invalidLogin = true
      }
    )
    
  }

  

}
