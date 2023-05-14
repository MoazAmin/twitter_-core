insert into tweet(id, created, deleted, likes, user_id, tweet_string, picture)
VALUES (1,'2017-03-14',false,0,10000,'This is my first tweet',null),
       (2,'2017-03-14',false,0,10000,'This is my second tweet',null),
       (3,'2017-03-14',false,0,10001,'This is my first tweet',null);


insert into follower(id, user_id, follower_id, deleted)
VALUES (1,10000, 10001,false),
       (2,10000, 10002,false),
       (3,10001, 10000,false)