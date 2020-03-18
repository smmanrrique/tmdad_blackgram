-- INSERTS TABLE
INSERT INTO public.users (id,email,first_name,last_name,"password",user_name) VALUES (2,'aldrix2@gmail.com','Aldrix','Marfil','AM1234','amarfil2');

INSERT INTO users( id, email, first_name, last_name, password, user_name) VALUES
(1, 'aldrix@gmail.com' , 'Aldrix' , 'Marfil'   , 'AM1234', 'amarfil'),
(2, 'shamuel@gmail.com', 'Shamuel', 'Manrrique', 'SM1234', 'smanrrique'),
(3, 'charles@gmail.com', 'Charles', 'Ochoa'    , 'CO1234', 'cochoa'),
(4, 'gabriel@gmail.com', 'Gabriel', 'Alvarez'  , 'GA1234', 'galvarez');


INSERT INTO groups(id, description, name) VALUES
(1, NULL, 'grupo1'),
(2, 'La Mansion de Gabriel', 'lamaga');

INSERT INTO users(id,  birthday, email, first_name, last_name, password, user_name) VALUES
(1,  'aldrix@gmail.com' , 'Aldrix' , 'Marfil'   , 'AM1234', 'amarfil'),
(2, 'shamuel@gmail.com', 'Shamuel', 'Manrrique', 'SM1234', 'smanrrique'),
(3, 'charles@gmail.com', 'Charles', 'Ochoa'    , 'CO1234', 'cochoa'),
(4, 'gabriel@gmail.com', 'Gabriel', 'Alvarez'  , 'GA1234', 'galvarez');

INSERT INTO contacts(id, created, updated, birthday, email, first_name, last_name, password, username) VALUES
(1,  'aldrix@gmail.com' , 'Aldrix' , 'Marfil'   , 'AM1234', 'amarfil'),
(2,  'shamuel@gmail.com', 'Shamuel', 'Manrrique', 'SM1234', 'smanrrique'),
(3, 'charles@gmail.com', 'Charles', 'Ochoa'    , 'CO1234', 'cochoa'),
(4, 'gabriel@gmail.com', 'Gabriel', 'Alvarez'  , 'GA1234', 'galvarez');
