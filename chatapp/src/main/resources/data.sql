-- INSERTS TABLE

INSERT INTO users( id, email, first_name, last_name, password, user_name) VALUES
(1, 'aldrix@gmail.com' , 'Aldrix' , 'Marfil'   , 'AM1234', 'amarfil'),
(2, 'shamuel@gmail.com', 'Shamuel', 'Manrrique', 'SM1234', 'smanrrique'),
(3, 'charles@gmail.com', 'Charles', 'Ochoa'    , 'CO1234', 'cochoa'),
(4, 'gabriel@gmail.com', 'Gabriel', 'Alvarez'  , 'GA1234', 'galvarez');

INSERT INTO groups(id, description, name) VALUES
(1, NULL, 'grupo1'),
(2, 'La Mansion de Gabriel', 'lamaga');

INSERT INTO public.contats (created, updated, contact_name_id) VALUES
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);

-- INSERT INTO public."groups" (created, updated, description, "name") VALUES
-- (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '', '');

-- INSERT INTO public.log_sync_app (created, updated, description, "path") VALUES
-- (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '', '');

-- INSERT INTO public.messages (created, updated, body, from_user_id, multimedia_id) VALUES
-- (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '', 0, 0);

-- INSERT INTO public.messages_topics (messages_id, topics_id) VALUES
-- (0, 0);

-- INSERT INTO public.multimedias (created, updated, url) VALUES
-- (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '');

-- INSERT INTO public.topics (created, updated, description, "name") VALUES
-- (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '', '');

-- INSERT INTO public.users (created, updated, birth_day, email, first_name, last_name, "password", user_name) VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '', '', '', '', '', '');

INSERT INTO public.contats (contact_name_id) VALUES
(1),
(2),
(3),
(4);

INSERT INTO public.users_contacts (user_id, contacts_id) VALUES
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 3),
(3, 1),
(3, 2),
(3, 4);

INSERT INTO public.users_groups (users_id, groups_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2);