import { AuthResponse } from "./basic-authentication.service";


export class User implements AuthResponse{
  constructor(
    public username: string,
    public jwtAuthToken: string,
    public serverCurrentTime: number,
    public _tokenExpirationTime: number
  ) {}

  get token() {
    return this.jwtAuthToken;
  }

  get user(){
    return this.username;
  }

  get tokenExpirationTime() {
    return this._tokenExpirationTime;
  }
}
