/*
   Sunday, October 08, 201711:50:16 AM
   User: anhquoc
   Server: ANHQUOC-PC\ANHQUOCPC
   Database: QL_HOCSINH
   Application: 
*/
USE [QL_HOCSINH]
GO
/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE dbo.KHOI
	(
	MAKHOI varchar(5) NOT NULL,
	Ten nvarchar(10) NULL
	)  ON [PRIMARY]
GO
