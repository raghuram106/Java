import { ConditionalExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { TweetDataService } from '../service/data/tweet-data.service';
import { User } from '../service/user.model';
import { Tweet } from '../tweets/tweets.component';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit {
  id!: any;
  name:any;
  tweet!: Tweet;
  errorMessage!:string;
  constructor(
    private tweetService: TweetDataService,
    private route: ActivatedRoute,
    private router: Router,
    private service:BasicAuthenticationService,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.name=this.service.userName;
    console.log(this.name)
    this.tweet = new Tweet(this.id, '', this.name, new Date(), [], 0);
    console.log(this.id);
    if(this.id!=-1){
    this.tweetService.retrieveTweet(this.id).subscribe(
      
      data => {
        this.tweet = data;
        console.log(this.tweet.tweetId)
        console.log("update in ")}
        
    )
    }
  }

  saveTweet() {
    console.log(this.name);
    if (this.id == -1) {
      this.tweetService.postTweet(this.name, this.tweet).subscribe(
        
        data => {
          console.log(this.tweet);
          this.router.navigate(['tweets']);

        },
        error=>this.errorMessage=error.error.message
      )
    } else {
      this.tweetService.updateTweet(this.name, this.id, this.tweet).subscribe(
        data => {

          this.router.navigate(['tweets']);

        },
        error=>this.errorMessage=error.error.message
      )
    }
  }
}
