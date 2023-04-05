import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchreviewComponent } from './components/searchreview/searchreview.component';
import { MoviereviewslistComponent } from './components/moviereviewslist/moviereviewslist.component';
import { PostcommentComponent } from './components/postcomment/postcomment.component';

const routes: Routes = [
  {path:'', component: SearchreviewComponent}, //localhost:4200
  {path:'reviews/:movie', component: MoviereviewslistComponent}, //localhost:4200/reviews/{movie}
  {path:'addcomment', component: PostcommentComponent}, //localhost:4200/addcomment
  {path: '**', redirectTo: '/', pathMatch:"full"} //redirect to localhost:4200
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
