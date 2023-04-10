import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {firstValueFrom} from "rxjs";
import { Comment, CommentPosted } from '../models';

//const SB_URL = "http://localhost:8080/api/comment"
//Railway
const SB_URL = "https://csf-assessment-production-e3de.up.railway.app/api/comment"

@Injectable()
export class CommentService {

  constructor(private http: HttpClient) { }

  //Method to make a HTTP POST request to the server
  //Returns a Promise of CommentPosted object and print console.info statement if promise is resolved
  postComment(comment: Comment): Promise<CommentPosted> {

		return firstValueFrom(
			this.http.post<CommentPosted>(SB_URL, comment)
		).then(req => {
      console.info(`${comment} posted to server.`);
      return req;
    });
  }

}
