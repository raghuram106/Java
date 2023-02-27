import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { User } from 'src/app/users/users.component';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  constructor(private http:HttpClient) { 
    
  }

  retrieveAllUsers(){
    return this.http.get<User[]>(`${API_URL}/users/all`);
  }

  registerUser(user:User){
    return this.http.post(`${API_URL}/register`,user);
  }

  forgotPassword(username:any,user:User){
    return this.http.post(`${API_URL}/${username}/forgot`,user);
  }

  
  searchByRegex(str : string){
    return this.http.get<User[]>(`${API_URL}/user/search/${str}`)
  }
}
