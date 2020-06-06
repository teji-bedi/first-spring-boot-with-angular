import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WallComponent} from "./pages/messaging/wall/wall.component";
import {PostComponent} from "./pages/messaging/post/post.component";
import {TimelineComponent} from "./pages/messaging/timeline/timeline.component";
import {FollowComponent} from "./pages/messaging/follow/follow.component";
import {HomeComponent} from "./home/home.component";


const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "home"},
  {path: "home", component: HomeComponent},
  {path: 'wall', component: WallComponent},
  {path: 'post', component: PostComponent},
  {path: 'timeline', component: TimelineComponent},
  {path: 'follow', component: FollowComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: "reload"
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
