import {Component, OnInit} from '@angular/core';
import {SocialWebMessagingService} from '../../social.web.messaging.service';
import {User} from '../../../model/user';
import {AlertMessage} from '../../../model/alert.message';

@Component({
  selector: 'app-follow',
  templateUrl: './follow.component.html',
})
export class FollowComponent implements OnInit {

  public users: User[] = [];
  public isUserValid = true;
  public userName: string;
  public selectedFollowingUserName: string;
  public alertMessage: AlertMessage = new AlertMessage();


  constructor(private messagingService: SocialWebMessagingService) {
    this.messagingService.getAvailableUsersToFollowForUserName(this.userName).subscribe();
  }

  ngOnInit() {
  }

  getAvailableUsersToFollowForUserName() {
    this.alertMessage = new AlertMessage();
    this.users = [];
    if (!this.userName) {
      this.alertMessage.alertMessage = 'Please Enter User Name';
      this.alertMessage.alertStatus = 'danger';
      return;
    }
    this.messagingService.checkForValidUserName(this.userName).subscribe(data => {
      this.isUserValid = data;
      if (!this.isUserValid) {
        this.alertMessage.alertMessage = 'Please Enter Valid User Name';
        this.alertMessage.alertStatus = 'danger';
        return;
      } else {
        this.getAvailableUsersToFollow();
      }
    });
  }

  private getAvailableUsersToFollow() {
    this.messagingService.getAvailableUsersToFollowForUserName(this.userName).subscribe(data => {
      this.users = data;
      if (this.users.length <= 0) {
        this.alertMessage.alertMessage = 'No More Users Found to be followed';
        this.alertMessage.alertStatus = 'danger';
      }
    });
  }

  public addFollowing() {
    this.alertMessage = this.messagingService.addFollowing(this.userName, this.selectedFollowingUserName);
    this.users = [];
  }
}
