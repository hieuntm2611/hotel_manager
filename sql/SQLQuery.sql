create table [user](
	id int not null identity(1,1) primary key,
	username nvarchar(255) not null,
	password nvarchar(255) not null,
	first_name nvarchar(255),
	last_name nvarchar(255),
	birthday date,
	email nvarchar(255) not null unique,
	gender bit,
	is_admin bit default 0,
	permission nvarchar(255) default 'user'
	)

	create table [group](
	id int not null identity(1,1) primary key,
	name varchar(200)
)

create table user_group(
	id int not null identity(1,1) primary key,
	userId int foreign key references [user](id),
	groupId int foreign key references [group](id),
)

create table [action](
	id int not null identity(1,1) primary key,
	[name] nvarchar(255) not null,
	code nvarchar(255) not null,
	feature varchar(200) not null,
)

create table group_action(
	id int not null identity(1,1) primary key,
	groupId int foreign key references [group](id),
	actionId int foreign key references [action](id),
)