-- Old Get gender
select
       tconst
     , lower(split_part(genres,',',1)) as gender1
     ,lower(split_part(genres,',',2)) as gender2
     ,lower(split_part(genres,',',3)) as gender3
from "title.basics";

-- Get gender without null
select tconst
     , lower(unnest(string_to_array(genres, ','))) as gender
from "title.basics";

-- Get writers and directors
WITH writer_directors AS (
    SELECT "tconst"
     ,lower(unnest(string_to_array(directors, ','))) AS directors
     ,lower(unnest(string_to_array(writers, ','))) AS writers
    FROM "title.crew")
SELECT "tconst"
     ,(CASE WHEN directors IS NOT NULL
        THEN directors
        ELSE 'unknow'
    END) AS directors
     ,(CASE WHEN writers IS NOT NULL
                THEN writers
            ELSE 'unknow'
    END) AS directors
FROM writer_directors;

-- Create temp view
CREATE OR REPLACE TEMPORARY VIEW writer_directors AS (
  WITH writer_directors AS (
      SELECT "tconst"
           ,lower(unnest(string_to_array(directors, ','))) AS directors
           ,lower(unnest(string_to_array(writers, ','))) AS writers
      FROM "title.crew")
  SELECT "tconst"
       ,(CASE WHEN directors IS NOT NULL
                  THEN directors
              ELSE 'unknow'
      END) AS directors
       ,(CASE WHEN writers IS NOT NULL
                  THEN writers
              ELSE 'unknow'
      END) AS writers
  FROM writer_directors);

-- Get writer information and create temp view
CREATE OR REPLACE TEMPORARY VIEW actor(

);

select
    writers as idWriter
    ,"primaryName"
    ,"birthYear"
    ,"deathYear"
    ,lower(split_part("primaryProfession",',',2)) as alternativeProfession1
    ,lower(split_part("primaryProfession",',',3)) as alternativeProfession2
from writer_directors
LEFT join  "name.basics" on writers= nconst
where writers != 'unknow'
order by writers ASC;


-- Get directors information
select * from writer_directors
join  "name.basics" on directors= nconst
where directors != 'unknow';
