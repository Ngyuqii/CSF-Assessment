### API Call and Response
URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=minions&api-key={API_KEY}"
```
{
  "status": "OK",
  "copyright": "Copyright (c) 2023 The New York Times Company. All Rights Reserved.",
  "has_more": false,
  "num_results": 2,
  "results": [
    {
      "display_title": "Minions: The Rise of Gru",
      "mpaa_rating": "PG",
      "critics_pick": 0,
      "byline": "Glenn Kenny",
      "headline": "‘Minions: The Rise of Gru’ Review: They’re Yellow but Not Mellow",
      "summary_short": "This latest edition featuring the animated creatures is an origin story of sorts for their master, Gru.",
      "publication_date": "2022-06-30",
      "opening_date": "2022-07-01",
      "date_updated": "2022-06-30 11:06:02",
      "link": {
        "type": "article",
        "url": "https://www.nytimes.com/2022/06/30/movies/minions-the-rise-of-gru-review.html",
        "suggested_link_text": "Read the New York Times Review of Minions: The Rise of Gru"
      },
      "multimedia": {
        "type": "mediumThreeByTwo210",
        "src": "https://static01.nyt.com/images/2022/07/01/arts/30minions/30minions-mediumThreeByTwo440.jpg",
        "height": 140,
        "width": 210
      }
    },
    {
      "display_title": "Minions",
      "mpaa_rating": "PG",
      "critics_pick": 0,
      "byline": "MANOHLA DARGIS",
      "headline": "Review: Positions Wanted by Minions Experienced at Serving Evildoers",
      "summary_short": "Those look-alike yellow guys become miserable when they have no one to help, albeit incompetently, in the first movie that&#8217;s all about them.",
      "publication_date": "2015-07-09",
      "opening_date": "2015-07-10",
      "date_updated": "2017-11-02 04:18:21",
      "link": {
        "type": "article",
        "url": "https://www.nytimes.com/2015/07/10/movies/review-positions-wanted-by-minions-experienced-at-serving-evildoers.html",
        "suggested_link_text": "Read the New York Times Review of Minions"
      },
      "multimedia": {
        "type": "mediumThreeByTwo210",
        "src": "https://static01.nyt.com/images/2015/07/10/arts/10MINIONJP/10MINIONJP-mediumThreeByTwo210.jpg",
        "height": 140,
        "width": 210
      }
    }
  ]
}
```

### Mongo Database
Database moviereviews

1. Get count
```
db.comments.find({
  movieTitle: 'Minions: The Rise of Gru'
}).count()
```

2. Insert comment
```
db.comments.insertOne({
  movieTitle: 'Minions: The Rise of Gru',
  name: 'Ace',
  rating: 5,
  comment: 'Great Comedy'
})
```