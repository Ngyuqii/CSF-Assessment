import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {firstValueFrom} from "rxjs";
import { CommentPosted } from '../models';

const SB_URL = "http://localhost:8080/comment"

@Injectable()
export class CommentService {

  constructor(private http: HttpClient) { }

  //Method to make a HTTP POST request to the server
  //Returns a Promise of OrderPlaced object and print console.info statement if promise is resolved
  postComment(comment: Comment): Promise<CommentPosted> {

		return firstValueFrom(
			this.http.post<CommentPosted>(SB_URL, comment)
		).then(req => {
      console.info(`${comment} posted to server.`);
      return req;
    });
  }


}
