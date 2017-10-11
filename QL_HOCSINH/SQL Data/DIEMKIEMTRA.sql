/*
   Tuesday, October 10, 20176:55:38 PM
   User: anhquoc
   Server: ANHQUOC-PC\ANHQUOCPC
   Database: QL_HOCSINH
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
CREATE TABLE dbo.DIEMKIEMTRA
	(
	MAHS varchar(10) NOT NULL,
	[15P] float(53) NULL,
	[45P] float(53) NULL
	)  ON [PRIMARY]
GO

COMMIT
select Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'CONTROL') as Contr_Per 