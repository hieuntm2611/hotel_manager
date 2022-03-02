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

create table [state](
	id int not null identity(1,1) primary key,
	[name] nvarchar(255) not null
	)
	create table [room_state](
	id int not null identity(1,1) primary key,
	[name] nvarchar(255) not null
	)
	create table [utility](
	id int not null identity(1,1) primary key,
	[name] nvarchar(255) not null
	)
	create table [customer](
	id int not null identity(1,1) primary key,
	[name] nvarchar(255) not null,
	gender bit not null,
	phone_number varchar(20) not null,
	cmnd varchar(16) not null,
	email varchar(150) not null
	)
	create table [category](
	id int not null identity(1,1) primary key,
	[name] nvarchar(255) not null,
	price money not null
	)
	create table [category_utility](
	id int not null identity(1,1) primary key,
	categoryId int foreign key references [category](id),
	utilityId int foreign key references [utility](id),
	)
	create table [room](
	id int not null identity(1,1) primary key,
	[name] nvarchar(255) not null,
	categoryId int not null foreign key references [category](id),
	roomstateId int not null foreign key references [room_state](id)
	)
	create table [service](
	id int not null identity(1,1) primary key,
	startDate date not null,
	endDate date not null,
	dateCreate date not null,
	dateUpdate date not null,
	roomId int not null foreign key references [room](id),
	customerId int not null foreign key references [customer](id),
	stateId int not null foreign key references [state](id)
	)
