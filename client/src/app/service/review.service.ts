import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject, firstValueFrom } from 'rxjs';
import { Review } from '../models';

const SB_URL = "http://localhost:8080/api/search"
//Railway
//const SB_URL = "https://csf-assessment-production-e3de.up.railway.app/api/search"

@Injectable()
export class ReviewService {

  constructor(private http: HttpClient) { }

  onMovieReviews = new Subject<Promise<Review[]>>();

  //Method to make a HTTP GET request to the server
  //Returns a Promise of an array of object
  getMovieReviews(movie: string): Promise<Review[]> {

    console.info(`>>> Movie search: ${movie}`);
    
    //Method to retrieve data and cast to Review[]
    //Emit the data to subscribers of onMovieReviews subject
    return firstValueFrom(
      this.http.get<Review[]>(`${SB_URL}/${movie}`)
    )
    .then(result => {
      console.info(">>> Response: ", result);
      const a = result as Review[];
      this.onMovieReviews.next(Promise.resolve(a));
      return a;
    })

  }

}