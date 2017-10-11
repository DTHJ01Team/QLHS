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
CREATE TABLE dbo.HT_KIEMTRA
	(
	MAKT varchar(5) NOT NULL,
	Ten nvarchar(50) NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.HT_KIEMTRA ADD CONSTRAINT
	PK_HT_KIEMTRA PRIMARY KEY CLUSTERED 
	(
	MAKT
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.HT_KIEMTRA SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'CONTROL') as Contr_Per 