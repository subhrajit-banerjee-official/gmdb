# gmdb

Resource Summary

|URI                             |   HTTP Method |   HTTP    |   Status      |   Description                    |
|--------------------------------|---------------|-----------|---------------|----------------------------------|
|galvanize.com/movies               |   GET         |   200     |   OK          |   Get all Movie names.         |
|galvanize.com/movies/{name}        |   GET         |   200     |   OK          |   When movie found share all details         |
|galvanize.com/movies/{name}        |   GET         |   204      |  No Content   |   Failure if movie's name doesn't exist  |
|galvanize.com/movies/{name}        |   POST        |   200     |   OK          |   Update movie details like Rating & Text review         |
