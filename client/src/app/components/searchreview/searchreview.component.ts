import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'app-searchreview',
  templateUrl: './searchreview.component.html',
  styleUrls: ['./searchreview.component.css']
})

export class SearchreviewComponent implements OnInit {

  constructor(private fb: FormBuilder, private router: Router, private reviewSvc: ReviewService) {}

  form!: FormGroup;

  //Initialize a FormGroup with validation
  ngOnInit(): void {
    this.form = this.fb.group({
      movie: this.fb.control("", [ Validators.required, Validators.minLength(2)])
    });
  }

  //Search button disabled if <2 characters not including leading and trailing blank space
  invalidEntry(): boolean {
    const formEntry = this.form.value.movie.trim();
    return this.form.invalid || formEntry.length<2;
  }

  //Retrieve form value
  //Nagivate user to reviews/{movie} page
  searchMovie(): void {
    const movieResult = this.form.value.movie;
    console.info(`>>>Inputs: ${movieResult}`);
    this.router.navigate(['/reviews', movieResult])
  }
}
