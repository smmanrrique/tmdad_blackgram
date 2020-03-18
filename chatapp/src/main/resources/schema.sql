-- DROP TABLES

DROP TABLE IF EXISTS public.users CASCADE;
DROP TABLE IF EXISTS public.contacts CASCADE;
DROP TABLE IF EXISTS public.employee CASCADE;
DROP TABLE IF EXISTS public.groups CASCADE;
DROP TABLE IF EXISTS public.logs CASCADE;
DROP TABLE IF EXISTS public.multimedias CASCADE;
DROP TABLE IF EXISTS public.topics CASCADE;



-- CREATE TABLES

-- public.users definition
CREATE TABLE public.users (
                              id bigserial NOT NULL,
                              created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              birth_day timestamp NULL,
                              email varchar(50) NOT NULL,
                              first_name varchar(50) NOT NULL,
                              last_name varchar(50) NOT NULL,
                              "password" varchar(30) NOT NULL,
                              user_name varchar(20) NOT NULL,
                              CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
                              CONSTRAINT uk_k8d0f2n7n88w1a16yhua64onx UNIQUE (user_name),
                              CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.contacts definition
CREATE TABLE public.contacts (
                                 id bigserial NOT NULL,
                                 created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 birth_day date NULL,
                                 email varchar(50) NOT NULL,
                                 first_name varchar(50) NOT NULL,
                                 last_name varchar(50) NOT NULL,
                                 "password" varchar(30) NOT NULL,
                                 username varchar(20) NOT NULL,
                                 CONSTRAINT contacts_pkey PRIMARY KEY (id),
                                 CONSTRAINT uk_728mksvqr0n907kujew6p3jc0 UNIQUE (email),
                                 CONSTRAINT uk_iy89i58xo7oepohxdhtenhgel UNIQUE (username)
);


-- public.employee definition
CREATE TABLE public.employee (
                                 employeename varchar(100) NOT NULL,
                                 employeeid varchar(11) NOT NULL,
                                 employeeaddress varchar(100) NULL DEFAULT NULL::character varying,
                                 employeeemail varchar(100) NULL DEFAULT NULL::character varying,
                                 CONSTRAINT employee_pkey PRIMARY KEY (employeeid)
);


-- public."groups" definition
CREATE TABLE public."groups" (
                                 id bigserial NOT NULL,
                                 created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 description varchar(255) NULL,
                                 "name" varchar(50) NOT NULL,
                                 CONSTRAINT groups_pkey PRIMARY KEY (id),
                                 CONSTRAINT uk_8mf0is8024pqmwjxgldfe54l7 UNIQUE (name)
);


-- public.logs definition
CREATE TABLE public.logs (
                             id bigserial NOT NULL,
                             created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             description varchar(255) NULL,
                             "path" varchar(20) NOT NULL,
                             CONSTRAINT logs_pkey PRIMARY KEY (id),
                             CONSTRAINT uk_6tmim9f41la8gnlio28vfcgdi UNIQUE (path)
);


-- public.multimedias definition
CREATE TABLE public.multimedias (
                                    id bigserial NOT NULL,
                                    created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    url text NOT NULL,
                                    CONSTRAINT multimedias_pkey PRIMARY KEY (id)
);


-- public.topics definition
CREATE TABLE public.topics (
                               id bigserial NOT NULL,
                               created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               description varchar(255) NULL,
                               "name" varchar(20) NOT NULL,
                               CONSTRAINT topics_pkey PRIMARY KEY (id),
                               CONSTRAINT uk_7tuhnscjpohbffmp7btit1uff UNIQUE (name)
);



