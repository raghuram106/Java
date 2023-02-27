import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { TweetDataService } from '../service/data/tweet-data.service';
import { Tweet } from '../tweets/tweets.component';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  commentToAdd!:string
  id!:number
  tweet!:Tweet
  name:any
  constructor(private tweetDataService:TweetDataService, 
    private route:ActivatedRoute,
    private router:Router,
    private service:BasicAuthenticationService) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.name=this.service.userName;
    this.tweet = new Tweet(this.id, '', '', new Date(), [],0);
    this.tweetDataService.retrieveTweet(this.id).subscribe(
      
      data => {
        this.tweet = data;
      }
    )
  }

  replyTweet(){

    this.tweetDataService.replyTweet(this.name,this.id,this.commentToAdd).subscribe(
      data => {
        this.router.navigate(['tweets']);
      }
    )

  }

}
