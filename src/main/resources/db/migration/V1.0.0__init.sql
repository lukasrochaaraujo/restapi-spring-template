create schema if not exists api;

create TABLE IF NOT EXISTS api."user" (
	id uuid NOT NULL DEFAULT random_uuid(),
	username varchar(16),
	password varchar(72),
	first_name varchar(16),
	last_name varchar(16),
	email varchar(24),
	phone varchar(24),
	user_status varchar(16) NOT NULL DEFAULT 'ACTIVE' NULL_TO_DEFAULT,
	role varchar(16) NOT NULL DEFAULT 'ROLE_USER' NULL_TO_DEFAULT,
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS api.user_token (
	id uuid NOT NULL DEFAULT random_uuid(),
	refresh_token varchar(128),
	user_id uuid NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (user_id) REFERENCES api."user"(id)
);

insert into api."user" (id, username, password, first_name, last_name, email, phone, user_status, role) values('a1b9b31d-e73c-4112-af7c-b68530f38222', 'jorgelucas', '{bcrypt}$2a$10$neR0EcYY5./tLVp4litNyuBy/kfrTsqEv8hiyqEKX0TXIQQwC/5Rm', 'Jorge', 'Lucas', 'jorge@lucas.db', '234234234', 'ACTIVE', 'USER');
insert into api."user" (id, username, password, first_name, last_name, email, phone, user_status, role) values('a1b9b31d-e73c-4112-af7c-b68530f38223', 'jorgelucas2', '{bcrypt}$2a$10$neR0EcYY5./tLVp4litNyuBy/kfrTsqEv8hiyqEKX0TXIQQwC/5Rm', 'Jorge', 'Lucas', 'jorge2@lucas.db', '234234234', 'ACTIVE', 'ADMIN');