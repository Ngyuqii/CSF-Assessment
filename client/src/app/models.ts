export interface Review {
  title: string;
  rating: string;
  byline: string;
  headline: string;
  summary: string;
  url: string;
  image: string;
  commentCount: number;
  }

  export interface Comment {
    name: string;
    rating: number;
    comment: string;
}