import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { Tweet } from 'src/app/tweets/tweets.component';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
  providedIn: 'root'
})
export class TweetDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllTweets() {
    return this.http.get<Tweet[]>(`${API_URL}/all`);
  }
  deleteTweet(username:any, id:any){
    return this.http.delete(`${API_URL}/${username}/delete/${id}`);
  }

  retrieveTweet(id: number){
    return this.http.get<Tweet>(`${API_URL}/all/${id}`);
  }
  updateTweet(username:any, id:any, t:Tweet){
    return this.http.put(`${API_URL}/${username}/update/${id}`,t);
  }

  postTweet(username:any, t:Tweet){
    return this.http.post(`${API_URL}/${username}/add`,t);
  }

  replyTweet(username:any,id:any,message:any){
    return this.http.post(`${API_URL}/${username}/reply/${id}`,message);
  }

  likeTweet(username:any,id :any,t:Tweet){
    return this.http.put(`${API_URL}/${username}/like/${id}`,t);
  }

  searchTweet(username:any){
    return this.http.get<Tweet[]>(`${API_URL}/search/${username}`);
  }
}
