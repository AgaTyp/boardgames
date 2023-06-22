create table if not exists CATEGORY (id serial primary key,
                                    name varchar(40));

create table if not exists AGE_RANGE (id serial primary key,
                                     min_age int);
create table if not exists TIME_RANGE (id serial primary key,
                                       min_time int,
                                       max_time int);
create table if not exists PLAYER_RANGE (id serial primary key,
                                       min_players int,
                                       max_players int);
create table if not exists PLAYER (id serial primary key,
                                   name varchar(40),
                                   birthday date);
create table if not exists BOARDGAME (id serial primary key,
                                      title varchar(100),
                                      description varchar(1000),
                                      category_id bigint,
                                      time_range_id bigint,
                                      player_range_id bigint,
                                      age_range_id bigint,
                                      last_time_played date,
                                      creation_date date,
                                      my_favorite boolean,
                                      my_own boolean,
                                      image_file varchar(200),
                                        constraint fk_category foreign key(category_id)
    references category(id) on delete set null,
    constraint fk_time_range foreign key(time_range_id)
    references time_range(id) on delete set null,
    constraint fk_player_range foreign key(player_range_id)
    references player_range(id) on delete set null,
    constraint fk_age_range foreign key(age_range_id)
    references age_range(id) on delete set null);

create table if not exists STATS (id serial primary key,
                                  boardgame_id bigint,
                                  player_id bigint,
                                  play_counter int,
                                  win_counter int,
                                  max_points int,
                                  constraint fk_boardgame foreign key (boardgame_id)
                                 references BOARDGAME(id) on delete set null,
                                  constraint fk_player foreign key (player_id)
                                 references PLAYER(id) on delete set null );
