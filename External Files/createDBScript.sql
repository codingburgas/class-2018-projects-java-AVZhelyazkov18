USE [master]
GO

/****** Object:  Database [ProjectDB]    Script Date: 6/15/2022 12:16:10 AM ******/
CREATE DATABASE [ProjectDB]

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ProjectDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [ProjectDB] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [ProjectDB] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [ProjectDB] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [ProjectDB] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [ProjectDB] SET ARITHABORT OFF 
GO

ALTER DATABASE [ProjectDB] SET AUTO_CLOSE ON 
GO

ALTER DATABASE [ProjectDB] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [ProjectDB] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [ProjectDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [ProjectDB] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [ProjectDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [ProjectDB] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [ProjectDB] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [ProjectDB] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [ProjectDB] SET  ENABLE_BROKER 
GO

ALTER DATABASE [ProjectDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [ProjectDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [ProjectDB] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [ProjectDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [ProjectDB] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [ProjectDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [ProjectDB] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [ProjectDB] SET RECOVERY SIMPLE 
GO

ALTER DATABASE [ProjectDB] SET  MULTI_USER 
GO

ALTER DATABASE [ProjectDB] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [ProjectDB] SET DB_CHAINING OFF 
GO

ALTER DATABASE [ProjectDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [ProjectDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO

ALTER DATABASE [ProjectDB] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [ProjectDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO

ALTER DATABASE [ProjectDB] SET QUERY_STORE = OFF
GO

ALTER DATABASE [ProjectDB] SET  READ_WRITE 
GO

