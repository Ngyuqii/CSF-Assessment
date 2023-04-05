import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchreviewComponent } from './components/searchreview/searchreview.component';
import { PostcommentComponent } from './components/postcomment/postcomment.component';
import { MoviereviewslistComponent } from './components/moviereviewslist/moviereviewslist.component';
import { ReviewService } from './service/review.service';
import { CommentService } from './service/comment.service';

@NgModule({
  declarations: [
    AppComponent,
    SearchreviewComponent,
    MoviereviewslistComponent,
    PostcommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ReviewService, CommentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
