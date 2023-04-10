import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Review } from 'src/app/models';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'app-moviereviewslist',
  templateUrl: './moviereviewslist.component.html',
  styleUrls: ['./moviereviewslist.component.css']
})

export class MoviereviewslistComponent implements OnInit, OnDestroy {

  movie = "";
  param$!: Subscription;

  reviews$!: Promise<Review[]>;

  constructor(private reviewSvc: ReviewService, private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {

      //Read path variable {movie}
      this.param$ = this.activatedRoute.params.subscribe(
        params => {
          this.movie= params["movie"];
          console.log(">>>>PV: ", this.movie);
        }
      );

      //Get movie reviews and subscribe to the onMovieReviews observable in the review service
      //When new reviews are emitted by the observable, reviews$ is updated with the new value
      this.reviewSvc.getMovieReviews(this.movie);
      this.reviewSvc.onMovieReviews.subscribe(
        p => {
          console.info(">>>Subscrib to server.");
          this.reviews$ = p;
        }
      )

    }

    //Unsubscribe to observable
    ngOnDestroy(): void {
      this.param$.unsubscribe();
    }

}