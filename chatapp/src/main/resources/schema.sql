-- DROP TABLES
DROP TABLE IF EXISTS public.users CASCADE;
DROP TABLE IF EXISTS public.contacts CASCADE;
DROP TABLE IF EXISTS public.employee CASCADE;
DROP TABLE IF EXISTS public.messages CASCADE;
DROP TABLE IF EXISTS public."groups" CASCADE;
DROP TABLE IF EXISTS public.log_sync_app CASCADE;
DROP TABLE IF EXISTS public.multimedias CASCADE;
DROP TABLE IF EXISTS public.topics CASCADE;
DROP TABLE IF EXISTS public.users_contacts CASCADE;
DROP TABLE IF EXISTS public.messages_topics CASCADE;
DROP TABLE IF EXISTS public.users_groups CASCADE;


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

-- public.log_sync_app definition
CREATE TABLE public.log_sync_app (
	id bigserial NOT NULL,
	created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	description varchar(255) NULL,
	"path" varchar(20) NOT NULL,
	CONSTRAINT log_sync_app_pkey PRIMARY KEY (id),
	CONSTRAINT uk_41htn2t4h84r6sp656aq7yuvh UNIQUE (path)
);

-- DROP TABLE public.multimedias;
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

-- public.users definition
CREATE TABLE public.users (
	id bigserial NOT NULL,
	created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	birth_day timestamp NULL,
	email varchar(50) NULL,
	first_name varchar(50) NULL,
	last_name varchar(50) NULL,
	"password" varchar(30) NOT NULL,
	user_name varchar(30) NOT NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT uk_k8d0f2n7n88w1a16yhua64onx UNIQUE (user_name),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- public.contats definition
CREATE TABLE public.contacts (
	id bigserial NOT NULL,
	created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	contact_name_id int8 NULL,
	CONSTRAINT contacts_pkey PRIMARY KEY (id),
	CONSTRAINT fkm2j4b0pgtkgl7o6q3338f6276 FOREIGN KEY (contact_name_id) REFERENCES users(id)
);


-- public.messages definition
CREATE TABLE public.messages (
	id bigserial NOT NULL,
	created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	body text NULL,
	from_user_id int8 NULL,
	multimedia_id int8 NULL,
	CONSTRAINT messages_pkey PRIMARY KEY (id),
	CONSTRAINT fk9caftv2owit9l0k3d2qoj9aee FOREIGN KEY (multimedia_id) REFERENCES multimedias(id),
	CONSTRAINT fkms9o5dx3lfmikr6k8kyxi030e FOREIGN KEY (from_user_id) REFERENCES users(id)
);


-- public.users_contacts definition
CREATE TABLE public.users_contacts (
	user_id int8 NOT NULL,
	contacts_id int8 NOT NULL
);

-- public.messages_topics definition
CREATE TABLE public.messages_topics (
	messages_id int8 NOT NULL,
	topics_id int8 NOT NULL,
	CONSTRAINT fkkika55dwtw88nctpbcilpvhpk FOREIGN KEY (messages_id) REFERENCES messages(id),
	CONSTRAINT fknbrhgtpvnvg0gtpqwn06uwhpb FOREIGN KEY (topics_id) REFERENCES topics(id)
);

-- public.users_groups definition
CREATE TABLE public.users_groups (
	users_id int8 NOT NULL,
	groups_id int8 NOT NULL,
	CONSTRAINT fkeg984vk9mx0imcdffn06f8q45 FOREIGN KEY (users_id) REFERENCES users(id),
	CONSTRAINT fkjex8no6gj9undclnlyn9l52wm FOREIGN KEY (groups_id) REFERENCES groups(id)
);