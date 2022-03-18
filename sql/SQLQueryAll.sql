USE [master]
GO
/****** Object:  Database [hotel]    Script Date: 18-Mar-22 5:44:00 PM ******/
CREATE DATABASE [hotel]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'hotel', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\hotel.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'hotel_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\hotel_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [hotel] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [hotel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [hotel] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [hotel] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [hotel] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [hotel] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [hotel] SET ARITHABORT OFF 
GO
ALTER DATABASE [hotel] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [hotel] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [hotel] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [hotel] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [hotel] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [hotel] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [hotel] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [hotel] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [hotel] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [hotel] SET  DISABLE_BROKER 
GO
ALTER DATABASE [hotel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [hotel] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [hotel] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [hotel] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [hotel] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [hotel] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [hotel] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [hotel] SET RECOVERY FULL 
GO
ALTER DATABASE [hotel] SET  MULTI_USER 
GO
ALTER DATABASE [hotel] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [hotel] SET DB_CHAINING OFF 
GO
ALTER DATABASE [hotel] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [hotel] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [hotel] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [hotel] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'hotel', N'ON'
GO
ALTER DATABASE [hotel] SET QUERY_STORE = OFF
GO
USE [hotel]
GO
/****** Object:  Table [dbo].[action]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[action](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [nvarchar](255) NOT NULL,
	[feature] [varchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[price] [money] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category_utility]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category_utility](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[categoryId] [int] NULL,
	[utilityId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[gender] [bit] NOT NULL,
	[phone_number] [varchar](20) NOT NULL,
	[cmnd] [varchar](16) NOT NULL,
	[email] [varchar](150) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[group]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[group](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[group_action]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[group_action](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[groupId] [int] NULL,
	[actionId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[room]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[room](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[categoryId] [int] NOT NULL,
	[roomstateId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[room_state]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[room_state](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[service]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[service](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[startDate] [date] NOT NULL,
	[endDate] [date] NOT NULL,
	[dateCreate] [date] NOT NULL,
	[dateUpdate] [date] NOT NULL,
	[roomId] [int] NOT NULL,
	[customerId] [int] NOT NULL,
	[stateId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[state]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[state](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[first_name] [nvarchar](255) NULL,
	[last_name] [nvarchar](255) NULL,
	[birthday] [date] NULL,
	[email] [nvarchar](255) NOT NULL,
	[gender] [bit] NULL,
	[is_admin] [bit] NULL,
	[permission] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_group]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_group](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[groupId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[utility]    Script Date: 18-Mar-22 5:44:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[utility](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[action] ON 
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (1, N'READ', N'SERVICE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (2, N'CREATE', N'SERVICE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (3, N'UPDATE', N'SERVICE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (4, N'REMOVE', N'SERVICE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (5, N'READ', N'CUSTOMER')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (6, N'CREATE', N'CUSTOMER')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (7, N'UPDATE', N'CUSTOMER')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (8, N'REMOVE', N'CUSTOMER')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (9, N'READ', N'ROOM')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (10, N'CREATE', N'ROOM')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (11, N'UPDATE', N'ROOM')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (12, N'REMOVE', N'ROOM')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (13, N'READ', N'ROOMSTATE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (14, N'CREATE', N'ROOMSTATE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (15, N'UPDATE', N'ROOMSTATE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (16, N'REMOVE', N'ROOMSTATE')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (17, N'READ', N'CATEGORY')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (18, N'CREATE', N'CATEGORY')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (19, N'UPDATE', N'CATEGORY')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (20, N'REMOVE', N'CATEGORY')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (21, N'READ', N'UTILITY')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (22, N'CREATE', N'UTILITY')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (23, N'UPDATE', N'UTILITY')
GO
INSERT [dbo].[action] ([id], [code], [feature]) VALUES (24, N'REMOVE', N'UTILITY')
GO
SET IDENTITY_INSERT [dbo].[action] OFF
GO
SET IDENTITY_INSERT [dbo].[category] ON 
GO
INSERT [dbo].[category] ([id], [name], [price]) VALUES (1, N'PenthouseVIP', 3000000.0000)
GO
INSERT [dbo].[category] ([id], [name], [price]) VALUES (2, N'Penthouse', 2000000.0000)
GO
INSERT [dbo].[category] ([id], [name], [price]) VALUES (3, N'VIP1', 1500000.0000)
GO
INSERT [dbo].[category] ([id], [name], [price]) VALUES (4, N'VIP2', 1200000.0000)
GO
INSERT [dbo].[category] ([id], [name], [price]) VALUES (5, N'Thường', 800000.0000)
GO
INSERT [dbo].[category] ([id], [name], [price]) VALUES (6, N'Rẻ', 600000.0000)
GO
SET IDENTITY_INSERT [dbo].[category] OFF
GO
SET IDENTITY_INSERT [dbo].[category_utility] ON 
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (15, 3, 1)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (16, 3, 2)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (17, 3, 3)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (18, 3, 4)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (19, 3, 5)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (20, 3, 6)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (21, 3, 8)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (22, 4, 1)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (23, 4, 2)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (24, 4, 3)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (25, 4, 5)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (26, 4, 8)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (30, 6, 10)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (61, 1, 1)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (62, 1, 2)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (63, 1, 3)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (64, 1, 4)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (65, 1, 5)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (66, 1, 6)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (67, 1, 7)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (68, 1, 8)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (69, 2, 1)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (70, 2, 2)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (71, 2, 3)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (72, 2, 4)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (73, 2, 5)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (74, 2, 6)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (75, 2, 7)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (79, 5, 1)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (80, 5, 2)
GO
INSERT [dbo].[category_utility] ([id], [categoryId], [utilityId]) VALUES (81, 5, 6)
GO
SET IDENTITY_INSERT [dbo].[category_utility] OFF
GO
SET IDENTITY_INSERT [dbo].[customer] ON 
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (1, N'Nguyễn Văn A', 1, N'0123456789', N'123456', N'abc@gmail')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (2, N'Nguyễn Văn B', 0, N'0123456', N'1234567', N'b@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (3, N'Test', 0, N'123456', N'654641213', N'sgsa@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (5, N'Test2', 0, N'0326885935', N'321546', N'hieu2611011@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (6, N'Test3', 0, N'032688593', N'646126', N'hieu2611@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (7, N'Test4', 0, N'6546541', N'1236564', N'ldjhsk@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (8, N'test5', 0, N'31632365', N'12548', N'ksdhf@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (9, N'test 6', 0, N'231365', N'685232', N'sdfs@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (10, N'nguoidung', 0, N'12136', N'5321545', N'ds@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (11, N'person', 0, N'4341325', N'126465', N'kjdchkjxz@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (12, N'hieuhihi', 0, N'0368330563', N'5454', N'diuhsid@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (13, N'hihi', 0, N'213546', N'64623546', N'kdhkj@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (14, N'hihihi', 0, N'12335456', N'136426', N'hiu@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (15, N'hieuhihi43535', 0, N'0368330563', N'646126', N'diuhsi3454@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (16, N'Tesst tesst', 0, N'0326885935', N'321546', N'hieu2611011@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (17, N'Tesst tesst4353', 1, N'0326885935', N'646126', N'hieu2611011@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (18, N'Test 234', 1, N'0326885935', N'321546', N'hieu261101432421@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (19, N'hieuhihi', 1, N'0368330563', N'231321', N'diuhsid@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (20, N'fghdf', 1, N'4321321', N'4212', N'sdfxczx@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (21, N'dfsd', 1, N'3565123', N'646126', N'vxcv@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (23, N'dfsfxc', 1, N'346345756', N'2', N'bfdbbcv@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (24, N'lzxnclxn', 1, N'1313254', N'132156', N'jdshfkxnk@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (25, N'ckljxnzm', 1, N'13245623132', N'3125464', N'lxkcnv4q8q@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (26, N'lj:LA;xmz', 0, N'65326654', N'42135465', N'kjdhfkz@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (27, N'gfbcv', 1, N'312654', N'3', N'kjhzl@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (28, N'dfdxcv', 1, N'45662', N'2313213', N'kjzxcbkzj@gmail')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (29, N'vcxvxc', 1, N'0368330563', N'1365121', N'diuhsid@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (30, N'zvccx', 1, N'435345', N'3121', N'kjhxzczb')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (31, N'gjvnb', 0, N'132165', N'321546', N'sdczx')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (32, N'szcxz', 1, N'321356', N'131', N'xgchjx')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (33, N'jkdhckxn', 0, N'3216516', N'321546', N'zckvjxkj')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (34, N'kjcxnk,m, mklc', 1, N'0326885935', N'12313', N'hieu2611011@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (35, N'kuaxgjzhxb', 1, N'416513', N'1632', N'kxjchkzxjk')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (36, N'xhbcvnxcm', 0, N'2313546', N'51321', N'mvbx m')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (37, N'kjdhskxcnv', 1, N'164516516', N'646126', N'kjcbkxzmn')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (38, N'Test2', 1, N'0326885935', N'12548', N'hieu2611011@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (39, N'lzxnclxn', 1, N'1313254', N'212313', N'jdshfkxnk@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (40, N'hieuhihi', 1, N'0368330563', N'5321545', N'diuhsid@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (41, N'hjbfk', 1, N'213213', N'646126', N'vcxvxc')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (42, N'vcvxv', 1, N'35455', N'3132', N'xcvzc')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (43, N'rwew', 0, N'6545', N'5484612', N'dfsdfsd')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (44, N'lzxnclxn', 1, N'1313254', N'34wer', N'jdshfkxnk@gmail.com')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (45, N'sdfsd', 1, N'234656', N'12548', N'vzxcvvx')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (46, N'rerte', 1, N'45456345', N'4566513', N'cvxcvxc')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (47, N'cbvcx', 0, N'5676790', N'45312', N'cbcvbc')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (48, N'gfhdgh', 1, N'345778', N'5321545', N'vbnbvc')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (49, N'dfgbd', 0, N'4tretyu', N'4213', N'hnvbnc')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (50, N'hfgbc', 1, N'65765', N'321546', N'cgfhf')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (51, N'hjvbxc', 1, N'2165', N'3213', N'kdvjxcv')
GO
INSERT [dbo].[customer] ([id], [name], [gender], [phone_number], [cmnd], [email]) VALUES (52, N'dfsd', 1, N'3565123', N'45465', N'vxcv@gmail.com')
GO
SET IDENTITY_INSERT [dbo].[customer] OFF
GO
SET IDENTITY_INSERT [dbo].[group] ON 
GO
INSERT [dbo].[group] ([id], [name]) VALUES (1, N'admin')
GO
INSERT [dbo].[group] ([id], [name]) VALUES (2, N'manager')
GO
SET IDENTITY_INSERT [dbo].[group] OFF
GO
SET IDENTITY_INSERT [dbo].[group_action] ON 
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (1, 1, 1)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (2, 1, 2)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (3, 1, 3)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (4, 1, 4)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (5, 1, 5)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (6, 1, 6)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (7, 1, 7)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (8, 1, 8)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (9, 1, 9)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (10, 1, 10)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (11, 1, 11)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (12, 1, 12)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (13, 1, 13)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (14, 1, 14)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (15, 1, 15)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (16, 1, 16)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (17, 1, 17)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (18, 1, 18)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (19, 1, 19)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (20, 1, 20)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (21, 1, 21)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (22, 1, 22)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (23, 1, 23)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (24, 1, 24)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (25, 2, 1)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (26, 2, 2)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (27, 2, 3)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (28, 2, 4)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (29, 2, 5)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (30, 2, 6)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (31, 2, 7)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (32, 2, 8)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (33, 2, 9)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (34, 2, 10)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (35, 2, 11)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (36, 2, 12)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (37, 2, 13)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (38, 2, 14)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (39, 2, 15)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (40, 2, 16)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (41, 2, 17)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (42, 2, 18)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (43, 2, 19)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (44, 2, 20)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (45, 2, 21)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (46, 2, 22)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (47, 2, 23)
GO
INSERT [dbo].[group_action] ([id], [groupId], [actionId]) VALUES (48, 2, 24)
GO
SET IDENTITY_INSERT [dbo].[group_action] OFF
GO
SET IDENTITY_INSERT [dbo].[room] ON 
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (1, N'101', 6, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (2, N'102', 5, 2)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (3, N'103', 4, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (4, N'104', 3, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (5, N'201', 6, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (6, N'202', 6, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (7, N'203', 5, 2)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (8, N'204', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (9, N'205', 3, 3)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (10, N'301', 5, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (11, N'302', 5, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (12, N'303', 5, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (13, N'304', 5, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (14, N'305', 4, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (15, N'401', 4, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (16, N'402', 4, 3)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (17, N'403', 4, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (18, N'404', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (20, N'405', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (21, N'Hpro1', 2, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (22, N'Hpro max', 1, 2)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (23, N'501', 4, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (24, N'502', 4, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (25, N'503', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (26, N'504', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (27, N'505', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (28, N'601', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (29, N'602', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (30, N'603', 3, 4)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (31, N'604', 3, 1)
GO
INSERT [dbo].[room] ([id], [name], [categoryId], [roomstateId]) VALUES (32, N'Hpro2', 2, 2)
GO
SET IDENTITY_INSERT [dbo].[room] OFF
GO
SET IDENTITY_INSERT [dbo].[room_state] ON 
GO
INSERT [dbo].[room_state] ([id], [name]) VALUES (1, N'Chưa có người thuê')
GO
INSERT [dbo].[room_state] ([id], [name]) VALUES (2, N'Đang sửa chữa')
GO
INSERT [dbo].[room_state] ([id], [name]) VALUES (3, N'Đang dọn')
GO
INSERT [dbo].[room_state] ([id], [name]) VALUES (4, N'Đang có người thuê')
GO
SET IDENTITY_INSERT [dbo].[room_state] OFF
GO
SET IDENTITY_INSERT [dbo].[service] ON 
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (1, CAST(N'2022-01-23' AS Date), CAST(N'2022-01-25' AS Date), CAST(N'2022-01-23' AS Date), CAST(N'2022-01-23' AS Date), 20, 1, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (3, CAST(N'2022-01-25' AS Date), CAST(N'2022-01-30' AS Date), CAST(N'2022-01-25' AS Date), CAST(N'2022-01-25' AS Date), 21, 2, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (4, CAST(N'2022-01-29' AS Date), CAST(N'2022-02-20' AS Date), CAST(N'2022-01-29' AS Date), CAST(N'2022-01-29' AS Date), 23, 1, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (5, CAST(N'2022-03-17' AS Date), CAST(N'2022-03-20' AS Date), CAST(N'2022-03-17' AS Date), CAST(N'2022-03-17' AS Date), 22, 15, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (6, CAST(N'2022-03-19' AS Date), CAST(N'2022-03-22' AS Date), CAST(N'2022-03-17' AS Date), CAST(N'2022-03-17' AS Date), 21, 16, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (7, CAST(N'2022-03-29' AS Date), CAST(N'2022-03-31' AS Date), CAST(N'2022-03-17' AS Date), CAST(N'2022-03-17' AS Date), 32, 18, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (14, CAST(N'2022-01-29' AS Date), CAST(N'2022-02-20' AS Date), CAST(N'2022-01-29' AS Date), CAST(N'2022-01-29' AS Date), 1, 5, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (15, CAST(N'2022-03-18' AS Date), CAST(N'2022-04-02' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 13, 19, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (16, CAST(N'2022-03-18' AS Date), CAST(N'2022-03-25' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 23, 20, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (17, CAST(N'2022-03-18' AS Date), CAST(N'2022-03-24' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 24, 21, 3)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (18, CAST(N'2022-02-28' AS Date), CAST(N'2022-04-02' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 10, 23, 3)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (19, CAST(N'2022-03-24' AS Date), CAST(N'2022-03-30' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 3, 24, 3)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (20, CAST(N'2022-03-09' AS Date), CAST(N'2022-04-08' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 6, 25, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (21, CAST(N'2022-03-01' AS Date), CAST(N'2022-04-08' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 1, 26, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (22, CAST(N'2022-03-19' AS Date), CAST(N'2022-03-20' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 26, 27, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (23, CAST(N'2022-03-17' AS Date), CAST(N'2022-03-31' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 21, 28, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (24, CAST(N'2022-03-19' AS Date), CAST(N'2022-03-25' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 29, 29, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (25, CAST(N'2022-03-05' AS Date), CAST(N'2022-03-06' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 30, 30, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (26, CAST(N'2022-03-12' AS Date), CAST(N'2022-03-20' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 5, 31, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (27, CAST(N'2022-04-14' AS Date), CAST(N'2022-05-07' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 27, 32, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (28, CAST(N'2022-05-20' AS Date), CAST(N'2022-06-24' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 28, 33, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (29, CAST(N'2022-07-15' AS Date), CAST(N'2022-07-30' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 25, 34, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (30, CAST(N'2022-04-02' AS Date), CAST(N'2022-04-10' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 12, 35, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (31, CAST(N'2022-04-01' AS Date), CAST(N'2022-04-10' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 28, 36, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (32, CAST(N'2022-03-03' AS Date), CAST(N'2022-03-12' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 26, 37, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (33, CAST(N'2022-03-05' AS Date), CAST(N'2022-04-03' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 16, 38, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (34, CAST(N'2022-03-25' AS Date), CAST(N'2022-04-09' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 9, 39, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (35, CAST(N'2022-07-08' AS Date), CAST(N'2022-09-23' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 29, 40, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (36, CAST(N'2022-04-03' AS Date), CAST(N'2022-04-06' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 20, 41, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (37, CAST(N'2022-04-02' AS Date), CAST(N'2022-04-10' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 29, 42, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (38, CAST(N'2022-04-15' AS Date), CAST(N'2022-05-01' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 9, 43, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (39, CAST(N'2022-05-20' AS Date), CAST(N'2022-07-15' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 15, 44, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (40, CAST(N'2022-03-26' AS Date), CAST(N'2022-04-09' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 8, 45, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (41, CAST(N'2022-03-19' AS Date), CAST(N'2022-04-02' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 27, 46, 7)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (42, CAST(N'2022-04-21' AS Date), CAST(N'2022-05-01' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 28, 47, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (43, CAST(N'2022-03-18' AS Date), CAST(N'2022-03-26' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 12, 48, 6)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (44, CAST(N'2022-03-18' AS Date), CAST(N'2022-03-20' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 18, 49, 1)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (45, CAST(N'2022-03-18' AS Date), CAST(N'2022-04-03' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 13, 50, 3)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (46, CAST(N'2022-03-18' AS Date), CAST(N'2022-04-03' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 11, 51, 3)
GO
INSERT [dbo].[service] ([id], [startDate], [endDate], [dateCreate], [dateUpdate], [roomId], [customerId], [stateId]) VALUES (47, CAST(N'2022-03-19' AS Date), CAST(N'2022-04-03' AS Date), CAST(N'2022-03-18' AS Date), CAST(N'2022-03-18' AS Date), 31, 52, 1)
GO
SET IDENTITY_INSERT [dbo].[service] OFF
GO
SET IDENTITY_INSERT [dbo].[state] ON 
GO
INSERT [dbo].[state] ([id], [name]) VALUES (1, N'Đặt trước')
GO
INSERT [dbo].[state] ([id], [name]) VALUES (3, N'Đang sử dụng')
GO
INSERT [dbo].[state] ([id], [name]) VALUES (6, N'Đã Trả Phòng')
GO
INSERT [dbo].[state] ([id], [name]) VALUES (7, N'Hủy')
GO
SET IDENTITY_INSERT [dbo].[state] OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON 
GO
INSERT [dbo].[user] ([id], [username], [password], [first_name], [last_name], [birthday], [email], [gender], [is_admin], [permission]) VALUES (1, N'admin', N'admin', N'hieu', N'hieu', CAST(N'2001-01-01' AS Date), N'hieu261101@gmail.com', 1, 1, N'admin')
GO
SET IDENTITY_INSERT [dbo].[user] OFF
GO
SET IDENTITY_INSERT [dbo].[user_group] ON 
GO
INSERT [dbo].[user_group] ([id], [userId], [groupId]) VALUES (1, 1, 1)
GO
SET IDENTITY_INSERT [dbo].[user_group] OFF
GO
SET IDENTITY_INSERT [dbo].[utility] ON 
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (1, N'Có hút thuốc')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (2, N'Có mạng LAN')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (3, N'Có ăn sáng')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (4, N'Massage')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (5, N'Cún 1m7')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (6, N'Tặng kèm nước uống')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (7, N'Nuôi thú cưng')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (8, N'Giường đôi')
GO
INSERT [dbo].[utility] ([id], [name]) VALUES (10, N'Còn cái nịt')
GO
SET IDENTITY_INSERT [dbo].[utility] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__user__AB6E6164EF4A5852]    Script Date: 18-Mar-22 5:44:01 PM ******/
ALTER TABLE [dbo].[user] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[user] ADD  DEFAULT ((0)) FOR [is_admin]
GO
ALTER TABLE [dbo].[user] ADD  DEFAULT ('user') FOR [permission]
GO
ALTER TABLE [dbo].[category_utility]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[category_utility]  WITH CHECK ADD FOREIGN KEY([utilityId])
REFERENCES [dbo].[utility] ([id])
GO
ALTER TABLE [dbo].[group_action]  WITH CHECK ADD FOREIGN KEY([actionId])
REFERENCES [dbo].[action] ([id])
GO
ALTER TABLE [dbo].[group_action]  WITH CHECK ADD FOREIGN KEY([groupId])
REFERENCES [dbo].[group] ([id])
GO
ALTER TABLE [dbo].[room]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[room]  WITH CHECK ADD FOREIGN KEY([roomstateId])
REFERENCES [dbo].[room_state] ([id])
GO
ALTER TABLE [dbo].[service]  WITH CHECK ADD FOREIGN KEY([customerId])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[service]  WITH CHECK ADD FOREIGN KEY([roomId])
REFERENCES [dbo].[room] ([id])
GO
ALTER TABLE [dbo].[service]  WITH CHECK ADD FOREIGN KEY([stateId])
REFERENCES [dbo].[state] ([id])
GO
ALTER TABLE [dbo].[user_group]  WITH CHECK ADD FOREIGN KEY([groupId])
REFERENCES [dbo].[group] ([id])
GO
ALTER TABLE [dbo].[user_group]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
USE [master]
GO
ALTER DATABASE [hotel] SET  READ_WRITE 
GO
