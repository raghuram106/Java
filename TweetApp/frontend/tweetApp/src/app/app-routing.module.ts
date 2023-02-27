import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { RouteGuardService } from './service/route-guard.service';
import { ErrorComponent } from './error/error.component';
import { TweetsComponent } from './tweets/tweets.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { UsersComponent } from './users/users.component';
import { TweetComponent } from './tweet/tweet.component';
import { CommentComponent } from './comment/comment.component';
import { UserTweetsComponent } from './user-tweets/user-tweets.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent, canActivate:[RouteGuardService]},
  { path: 'home', component: HomeComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'welcome', component:WelcomeComponent, canActivate:[RouteGuardService]},
  { path: 'tweets', component:TweetsComponent, canActivate:[RouteGuardService]},
  { path: 'forgotPassword', component:ForgotPasswordComponent},
  { path: 'users', component:UsersComponent, canActivate:[RouteGuardService]},
  { path: 'tweets/:id', component: TweetComponent, canActivate:[RouteGuardService]},
  { path: 'reply/:id', component: CommentComponent, canActivate:[RouteGuardService]},
  { path: 'search/:id', component:UserTweetsComponent, canActivate:[RouteGuardService]},
  { path: '', component: HomeComponent},
  { path: '**', component:ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
