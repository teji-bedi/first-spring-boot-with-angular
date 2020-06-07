import {Component, OnInit} from '@angular/core';
import {SocialWebMessagingService} from '../../social.web.messaging.service';
import {AlertMessage} from '../../../model/alert.message';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
})
export class PostComponent implements OnInit {

  public userName: string;
  public message: string;
  public alertMessage: AlertMessage = new AlertMessage();

  constructor(public service: SocialWebMessagingService) {
  }

  ngOnInit() {
  }

  public postMessage() {
    this.alertMessage = new AlertMessage();
    if (!this.userName) {
      this.alertMessage.alertMessage = 'Please Enter User Name';
      this.alertMessage.alertStatus = 'danger';
      return;
    }
    if (!this.message) {
      this.alertMessage.alertMessage = 'Please Enter Message';
      this.alertMessage.alertStatus = 'danger';
      return;
    }
    this.alertMessage = this.service.postMessage(this.userName, this.message);
  }
}
