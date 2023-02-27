import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { TweetDataService } from '../service/data/tweet-data.service';
import { Tweet } from '../tweets/tweets.component';

@Component({
  selector: 'app-user-tweets',
  templateUrl: './user-tweets.component.html',
  styleUrls: ['./user-tweets.component.css']
})
export class UserTweetsComponent implements OnInit {

  tweetsList : Tweet[] | undefined
  message:string | undefined;
  count:any
  name=this.service.userName;
  username!:string;
  postmsg:any
  constructor(private tweetService:TweetDataService,
    private router:Router,
    private service:BasicAuthenticationService,
    private route:ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this.postmsg=''
    this.username= this.route.snapshot.params['id'];
    this.refreshTweets();
    if(this.username==this.name){
      this.postmsg="yes"
    }
  }
  refreshTweets(){
    this.tweetService.searchTweet(this.username).subscribe(
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
