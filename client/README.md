### API Call and Response

1. GET
URL = http://localhost:8080/api/search?query=minions
```
[
    {
        "title": "Minions: The Rise of Gru",
        "rating": "PG",
        "byline": "Glenn Kenny",
        "headline": "‘Minions: The Rise of Gru’ Review: They’re Yellow but Not Mellow",
        "summary": "This latest edition featuring the animated creatures is an origin story of sorts for their master, Gru.",
        "url": "https://www.nytimes.com/2022/06/30/movies/minions-the-rise-of-gru-review.html",
        "image": "https://static01.nyt.com/images/2022/07/01/arts/30minions/30minions-mediumThreeByTwo440.jpg",
        "commentCount": 0
    },
    {
        "title": "Minions",
        "rating": "PG",
        "byline": "MANOHLA DARGIS",
        "headline": "Review: Positions Wanted by Minions Experienced at Serving Evildoers",
        "summary": "Those look-alike yellow guys become miserable when they have no one to help, albeit incompetently, in the first movie that&#8217;s all about them.",
        "url": "https://www.nytimes.com/2015/07/10/movies/review-positions-wanted-by-minions-experienced-at-serving-evildoers.html",
        "image": "https://static01.nyt.com/images/2015/07/10/arts/10MINIONJP/10MINIONJP-mediumThreeByTwo210.jpg",
        "commentCount": 0
    }
]
```

2. POST
URL = "http://localhost:8080/api/comment"
```
{
    "movieTitle": "Minions: The Rise of Gru",
    "name": "Ace",
    "rating": 5,
    "comment": "Great Comedy"
}
```