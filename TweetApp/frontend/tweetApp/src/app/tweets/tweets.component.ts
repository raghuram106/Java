import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { TweetDataService } from '../service/data/tweet-data.service';
export class Tweet{
  constructor(
    public tweetId:number,
    public tweetMessage:string,
    public loginId: string,
    public tweetTime:Date,
    public reply: String[],
    public likeCount: number

    ){

    }
}

@Component({
  selector: 'app-tweets',
  templateUrl: './tweets.component.html',
  styleUrls: ['./tweets.component.css']
})
export class TweetsComponent implements OnInit {
  tweetsList : Tweet[] | undefined
  message:string | undefined;
  count:any
  name:any
  constructor(private tweetService:TweetDataService,
    private router:Router,
    private service:BasicAuthenticationService,
    ) { }

  ngOnInit(): void {
    console.log("tweets")
    this.name=this.service.userName;
    this.refreshTweets();
  }
  refreshTweets(){
    this.tweetService.retrieveAllTweets().subscribe(
      response => {
          console.log(response);
          this.tweetsList=response;
        }
    )
  }

  deleteTweet(username:any,id:any){
    if(username==this.name){
    this.tweetService.deleteTweet(this.name,id).subscribe(
      response => {
         
         // this.replyList = this.tweet.reply;
          this.message=`Delete of Tweet Successful`
          this.refreshTweets();
        },
    )
    }

  }

  updateTweet(username:any,id:any){
    if(username==this.name)
    {
      this.router.navigate(['tweets',id])
    }
  }

  addTweet(){
    this.router.navigate(['tweets',-1])
  }

  commentTweet(username:any,id:any){
    this.router.navigate(['reply',id])
  }

  likeTweet(username:any,id:any,t:Tweet){
    this.tweetService.likeTweet(this.name,id,t).subscribe(
      response=>this.refreshTweets()
    );
  }
  

}
