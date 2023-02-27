import { Component, OnInit } from '@angular/core';
import { EmailValidator } from '@angular/forms';
import { UserDataService } from '../service/data/user-data.service';

export class User{
  constructor(
    public loginId: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public password: string,
    public confirmPassword: string,
    public contactNumber: string
  ){

  }
}
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  usersList : User[] | undefined;
  loginId = ''

  str = ''
  searchMessage = ''
  val = false
  noUser = ''
  constructor( private userService:UserDataService) { }

  ngOnInit(): void {
    //this.refreshUsers();

  }

  all(){
    this.userService.retrieveAllUsers().subscribe(
      data => {
         this.usersList=data;
       }
   )

  }

  refreshUsers(){
    this.userService.retrieveAllUsers().subscribe(
       data => {
          this.usersList=data;
        }
    )

  }

  search(){
    this.noUser = ''

    if (this.str === '') {
      this.searchMessage = "Please type something before searching"
    }
    else {
      this.searchMessage = ''
      this.userService.searchByRegex(this.str).subscribe(data => {
        this.usersList = data
        if (!this.usersList.length) {
          this.noUser = "No Results for '" + this.str+"'"
          this.userService.retrieveAllUsers().subscribe(response => {
            this.usersList = response
          })
        }

      })
    }

  }



}
