import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  name=''
  welcomeMessageFromService:string | undefined
  constructor(private authService:BasicAuthenticationService) { }

  ngOnInit(): void {
    this.name = this.authService.userName;
  }

  }

 


