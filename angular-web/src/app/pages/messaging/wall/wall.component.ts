import {Component, OnInit} from '@angular/core';
import {SocialWebMessagingService} from "../../social.web.messaging.service";
import {Message} from "../../../model/message";
import {AlertMessage} from "../../../model/alert.message";

@Component({
  selector: 'app-wall',
  templateUrl: './wall.component.html',
})
export class WallComponent implements OnInit {

  public messages: Message[];
  public userName: string;
  public alertMessage: AlertMessage = new AlertMessage();

  constructor(private messagingService: SocialWebMessagingService) { }

  ngOnInit() {
    this.messagingService.getMessagesForUser(this.userName).subscribe(data => this.messages = data);
  }

  getMessagesForUser() {
    this.messages = [];
    this.alertMessage = new AlertMessage();
    this.alertMessage.alertStatus = 'success';
    if (!this.userName) {
      this.alertMessage.alertMessage = 'Please Enter User Name';
      this.alertMessage.alertStatus = 'danger';
      return;
    }

    this.messagingService.getMessagesForUser(this.userName).subscribe(data => {
      this.messages = data;
      if (this.messages.length === 0) {
        this.alertMessage.alertMessage = 'No Messages Found For This User';
        this.alertMessage.alertStatus = 'danger';
      }
    });

    return this.messages;
  }


}
