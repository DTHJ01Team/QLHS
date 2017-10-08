/*
   Sunday, October 08, 201711:25:51 AM
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
CREATE TABLE dbo.Tmp_HOCKY
	(
	MAHK varchar(5) NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_HOCKY SET (LOCK_ESCALATION = TABLE)
GO
IF EXISTS(SELECT * FROM dbo.HOCKY)
	 EXEC('INSERT INTO dbo.Tmp_HOCKY (MAHK)
		SELECT MAHK FROM dbo.HOCKY WITH (HOLDLOCK TABLOCKX)')
GO
DROP TABLE dbo.HOCKY
GO
EXECUTE sp_rename N'dbo.Tmp_HOCKY', N'HOCKY', 'OBJECT' 
GO
ALTER TABLE dbo.HOCKY ADD CONSTRAINT
	PK_HOCKY PRIMARY KEY CLUSTERED 
	(
	MAHK
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
COMMIT
select Has_Perms_By_Name(N'dbo.HOCKY', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.HOCKY', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.HOCKY', 'Object', 'CONTROL') as Contr_Per 