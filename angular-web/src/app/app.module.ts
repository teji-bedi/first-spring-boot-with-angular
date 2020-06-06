import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PostComponent} from './pages/messaging/post/post.component';
import {WallComponent} from './pages/messaging/wall/wall.component';
import {FollowComponent} from './pages/messaging/follow/follow.component';
import {TimelineComponent} from './pages/messaging/timeline/timeline.component';
import {HttpClientModule} from "@angular/common/http";
import {SocialWebMessagingService} from "./pages/social.web.messaging.service";
import {FormsModule} from "@angular/forms";
import { MessagesComponent } from './pages/messaging/messages/messages.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    WallComponent,
    FollowComponent,
    TimelineComponent,
    MessagesComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [SocialWebMessagingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
