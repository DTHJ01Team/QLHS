/*
   Tuesday, October 10, 20177:10:03 PM
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
CREATE TABLE dbo.QUYDINH
	(
	MAQD varchar(5) NOT NULL,
	GiaTriQD float NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.QUYDINH ADD CONSTRAINT
	PK_QUYDINH PRIMARY KEY CLUSTERED 
	(
	MAQD
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.QUYDINH SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'CONTROL') as Contr_Per 