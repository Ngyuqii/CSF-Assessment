import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { CommentService } from 'src/app/service/comment.service';

@Component({
  selector: 'app-postcomment',
  templateUrl: './postcomment.component.html',
  styleUrls: ['./postcomment.component.css']
})

export class PostcommentComponent implements OnInit {

  constructor(private fb: FormBuilder, private commentSvc: CommentService){ }

  form!: FormGroup;
  obs!: Observable<any>; //to see what is commented

  //Initialize form creation
  //Observable emits event on every value change of the form with a delay of 500ms
  ngOnInit(): void {
    this.form = this.createForm();
    this.obs = this.form.valueChanges;
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

  //Method upon ngSubmit to retrieve values of the form controls and set into Comment object
  //Post comment to server and resets form else return error
  postNewComment() {
    const newComment = this.form.value as Comment;

    this.commentSvc.postComment(newComment)
			.then(result => {
				alert(`Comment id ${result.commentId} is posted.`);
				this.clearData(); //use child's method clearData
			})
			.catch(error => {
				alert(`ERROR! ${JSON.stringify(error)}`)
			})
  }

}
