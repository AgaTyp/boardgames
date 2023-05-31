INSERT INTO CATEGORY (name)
VALUES ('strategiczna'), ('ekonomiczna'), ('karciana'), ('rodzinna');

INSERT INTO AGE_RANGE (min_age)
VALUES (14), (10), (8);

INSERT INTO TIME_RANGE (min_time, max_time)
VALUES (60, 90), (40, 70), (120, 180), (30, 40);

INSERT INTO PLAYER_RANGE (min_players, max_players)
VALUES (2, 2), (1, 5), (2, 5), (2, 6), (1, 4);

INSERT INTO PLAYER (name, birthday)
VALUES ('Aga', '1984-10-01'), ('Rafał', '1984-07-01'), ('Ala', '2012-12-01'), ('Zuzia', '2014-12-01');

INSERT INTO BOARDGAME (title, description, category_id, time_range_id, player_range_id, age_range_id, last_time_played, creation_date, my_favorite, my_own, image_file)
VALUES ('Na skrzydłach', 'Bardzo przyjemna, prosta do wytłumaczenia gra. Przepięknie wykonana. Najlepsza rozgrywka od 3 osób. Można grać z dziećmi od 8. roku życia',
        1, 2, 2, 2, '2023-05-10', '2023-02-01', true, true, 'na-skrzydlach.png'),
       ('Wsiąść do pociągu: Europa', 'Łatwa gra, dobra do grania z dziećmi. Przy 5 osobach robi się ciasno na planszy. Przy częstym graniu robi się trochę monotonna, więc dobrze robić dłuższe przerwy między rozgrywkami',
        4, 1, 3, 3, '2023-04-15', '2022-12-30', false, true, 'wsiasc-do-pociagu-europa.png'),
       ('Twilight Struggle: Zimna Wojna 1945-1989', 'Super gra. Bardzo rozbudowana i złożona, ale jak się załapie mechanikę, to bardzo logiczna. Super oddaje klimat. Trzeba sobie zarezerwować około 3h',
        1, 2, 1, 1, '2023-05-15', '2023-05-10', true, true, 'twilight-struggle-zimna-wojna.png'),
       ('Potwory w Tokio', 'Duże pozytywne zaskoczenie. Gra niepozorna, a daje ogromną frajdę. Spora losowość, ale nadal można obmyślać strategię',
       4, 4, 4, 3, '2023-05-18', '2023-05-18', false, false, 'potwory-w-tokio.jpg'),
       ('Kroniki zamku Avel', 'Pięknie zrobiona gra w rodzaju planszowego RPG. Idealna na rodzinne granie',
        4, 1, 5, 3, null, '2023-05-30', false, true, 'kroniki-zamku-avel.png');;

INSERT INTO STATS (boardgame_id, player_id, play_counter, win_counter, max_points)
VALUES (1, 1, 10, 4, 67),
       (1, 2, 10, 4, 64),
       (1, 3, 4, 1, 70),
       (1, 4, 4, 1, 63),
       (2, 1, 4, 2, 110),
       (2, 2, 3, 1, 115),
       (2, 3, 4, 0, 70),
       (2, 4, 4, 1, 103),
       (3, 1, 3, 1, 0),
       (3, 2, 3, 1, 0),
       (4, 1, 1, 0, 0),
       (4, 2, 1, 1, 0),
       (4, 4, 1, 0, 0);
