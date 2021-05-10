
## Functional 

### Doobie  

#### Mapping Postrgres db fields

* Array[Array[String]] Doobie <=> Postgres 

``` sql
CREATE DATABASE football OWNER postgres;

CREATE TABLE footballgame (leagueid text, season text NOT NULL, audience int, playermin text[][]);
``` 


* necessary imports
 
  [Review seting Up](https://tpolecat.github.io/doobie/docs/11-Arrays.html#setting-up)
  
  


* src code 

```scala

implicit val arraynested = Meta.Advanced.array[Array[String]]("varchar", "_varchar", "_char","_text")

case class FootballMatch(league: String, season: String, audience: Int, team: Array[Array[String]])

/** TODO Code for get the transactor */

sql"select leagueid,season,audience,playermin from footballgame".query[FootballMatch].to[List].transact(xa)
```



ref:

https://tpolecat.github.io/doobie-0.2.1/13-Extensions-PostgreSQL.html

https://tpolecat.github.io/doobie/docs/11-Arrays.html



