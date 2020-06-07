import {Component, OnInit} from '@angular/core';
import {Message} from '../../../model/message';
import {SocialWebMessagingService} from '../../social.web.messaging.service';
import {AlertMessage} from '../../../model/alert.message';

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
})
export class TimelineComponent implements OnInit {
  public messages: Message[] = [];
  public userName: string;
  public alertMessage: AlertMessage = new AlertMessage();

  constructor(private messagingService: SocialWebMessagingService) {
  }

  ngOnInit() {
    this.messagingService.getMessagesFromFollowing(this.userName).subscribe();
  }

  getMessagesFromFollowing() {
    this.alertMessage = new AlertMessage();
    this.messages = [];
    this.alertMessage = new AlertMessage();
    this.alertMessage.alertStatus = 'success';
    if (!this.userName) {
      this.alertMessage.alertMessage = 'Please Enter User Name';
      this.alertMessage.alertStatus = 'danger';
      return;
    }
    this.messagingService.getMessagesFromFollowing(this.userName).subscribe(data => {
      this.messages = data;
      if (this.messages.length === 0) {
        this.alertMessage.alertMessage = 'No Messages Found From Your Friends';
        this.alertMessage.alertStatus = 'danger';
      }
    });

    return this.messages;
  }
}
