import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { CommentService } from 'src/app/service/comment.service';
import { Comment } from '../../models';

@Component({
  selector: 'app-postcomment',
  templateUrl: './postcomment.component.html',
  styleUrls: ['./postcomment.component.css']
}) 

export class PostcommentComponent implements OnInit, OnDestroy {

  movieName = "";
  param$!: Subscription;

  constructor(private fb: FormBuilder, private commentSvc: CommentService, 
    private router: Router, private activatedRoute: ActivatedRoute){ }

  form!: FormGroup;
  obs!: Observable<any>; //to see what is commented

  //Initialize form creation
  //Observable emits event on every value change of the form
  ngOnInit(): void {
    this.form = this.createForm();
    this.obs = this.form.valueChanges;

    //Read path variable {movietitle}
    this.param$ = this.activatedRoute.params.subscribe(
      params => {
        this.movieName = params["movietitle"];
        console.log(">>>>PV: ", this.movieName);
      }
    );
  }

  //Method to create a form with form controls and validators
  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
			rating: this.fb.control<number>(5, [ Validators.required ]),
			comment: this.fb.control<string>('', [ Validators.required ])
    })
  }

  //Method that returns true if specific form input is invalid
  invalidControl(ctrlName: string): boolean {
    const ctrl = this.form.get(ctrlName) as FormControl;
    return ctrl.invalid && (!ctrl.pristine);
  }

  //Method to refresh the form
  clearData() {
		this.form = this.createForm();
	}

  //Method to return to list of reviews
  toReviewsList(){
    const search = localStorage.getItem("searchKey");
    this.router.navigate(['/reviews', search]);
  }

  //Method upon ngSubmit to retrieve values of the form controls and set into Comment object
  //Post comment to server, resets form and returns to reviewslist else return error message
  postNewComment() {
    //newComment.movieTitle = this.movieName;
    const newComment = {"movieTitle":this.movieName, ...(this.form.value)} as Comment;
    console.log(">>>> NewComment", newComment);

    this.commentSvc.postComment(newComment)
			.then(result => {
				alert(`Comment id ${result.commentId} is posted.`);
				this.clearData();
        this.toReviewsList();
			})
			.catch(error => {
				alert(`ERROR! ${JSON.stringify(error)}`)
			})
  }

  //Unsubscribe to observable
  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }

}