/*
   Sunday, October 15, 20179:06:56 AM
   User: anhquoc
   Server: ANHQUOC-PC\ANHQUOCPC
   Database: vd
   Application: 
*/
USE [QL_HOCSINH]
GO
/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
CREATE TABLE dbo.DIEMDATMON
	(
	MAMH varchar(5) NOT NULL,
	DiemDat float(53) NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.DIEMDATMON SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
