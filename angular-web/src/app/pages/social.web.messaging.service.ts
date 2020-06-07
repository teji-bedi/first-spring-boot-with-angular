import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Message} from '../model/message';
import {Observable} from 'rxjs';
import {User} from '../model/user';
import {AlertMessage} from '../model/alert.message';

@Injectable()
export class SocialWebMessagingService {
  private alertMessage: AlertMessage = new AlertMessage();


  constructor(private httpClient: HttpClient) {
  }

  private BASE_URL = 'http://localhost:8080';

  getAllMessages(): Observable<Message[]> {
    return this.httpClient.get<Message[]>(this.BASE_URL + '/getAllMessages');
  }

  postMessage(userName: string, message: string): AlertMessage {
    this.httpClient.post(this.BASE_URL + '/postMessage/' + userName, {message: message},
        {headers: new HttpHeaders({'Content-Type': 'application/json'})}).subscribe(
        (data: any) => {
          this.alertMessage.alertMessage = 'Message posted successfully';
          this.alertMessage.alertStatus = 'success';
        },
        (response: HttpErrorResponse) => {
          this.alertMessage.alertMessage = 'Message post was unsuccessful';
          this.alertMessage.alertStatus = 'danger';
        }
    );
    return this.alertMessage;
  }

  addFollowing(userName: string, followingUserName: string): AlertMessage {
    this.httpClient.post(this.BASE_URL + '/addFollowing/' + userName + '/' + followingUserName, {},
        {headers: new HttpHeaders({'Content-Type': 'application/json'})}).subscribe(
        (data: any) => {
          this.alertMessage.alertMessage = 'You have successfully started following ' + followingUserName;
          this.alertMessage.alertStatus = 'success';
        },
        (response: HttpErrorResponse) => {
          this.alertMessage.alertMessage = 'Issue raised while sending follow request';
          this.alertMessage.alertStatus = 'danger';
        }
    );
    return this.alertMessage;
  }

  getMessagesForUser(userName: string): Observable<Message[]> {
    return this.httpClient.get<Message[]>(this.BASE_URL + '/getMessagesByUserName/' + userName);
  }

  getMessagesFromFollowing(userName: string): Observable<Message[]> {
    return this.httpClient.get<Message[]>(this.BASE_URL + '/getMessagesFromFollowing/' + userName);
  }

  getAvailableUsersToFollowForUserName(userName: string): Observable<User[]> {
    return this.httpClient.get<User[]>(this.BASE_URL + '/getAvailableUsersToFollowForUserName/' + userName);
  }

  checkForValidUserName(userName: string): Observable<boolean> {
    return this.httpClient.get<boolean>(this.BASE_URL + '/checkForValidUserName/' + userName);
  }
}
