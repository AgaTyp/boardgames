alter table BOARDGAME add column min_time int;
alter table BOARDGAME add column max_time int;

update BOARDGAME set BOARDGAME.min_time = (select tr.min_time from TIME_RANGE tr where BOARDGAME.time_range_id=tr.id),
                     BOARDGAME.max_time = (select tr.max_time from TIME_RANGE tr where BOARDGAME.time_range_id=tr.id)
                 where exists(select * from TIME_RANGE tr where tr.id=BOARDGAME.time_range_id);

alter table BOARDGAME drop column time_range_id;
drop table if exists TIME_RANGE;
