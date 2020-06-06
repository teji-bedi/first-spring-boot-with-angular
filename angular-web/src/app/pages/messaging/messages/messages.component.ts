import { Component, OnInit } from '@angular/core';
import {SocialWebMessagingService} from "../../social.web.messaging.service";
import {Message} from "../../../model/message";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
})
export class MessagesComponent implements OnInit {

  public messages: Message[];
  constructor(private messageService: SocialWebMessagingService) { }

  ngOnInit() {
    this.addSubscriptionForWall();
  }

  private addSubscriptionForWall() {
    this.messageService.getMessagesForUser('abc').subscribe(data => this.messages = data);
  }

  public getMessagesForUser(userName: string){
    return this.messages;
  }

}
