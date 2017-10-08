/*
   Sunday, October 08, 201711:59:32 AM
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
CREATE TABLE dbo.Tmp_MONHOC
	(
	MAMH varchar(10) NOT NULL,
	Ten nvarchar(50) NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_MONHOC SET (LOCK_ESCALATION = TABLE)
GO
IF EXISTS(SELECT * FROM dbo.MONHOC)
	 EXEC('INSERT INTO dbo.Tmp_MONHOC (MAMH, Ten)
		SELECT MAMH, Ten FROM dbo.MONHOC WITH (HOLDLOCK TABLOCKX)')
GO
DROP TABLE dbo.MONHOC
GO
EXECUTE sp_rename N'dbo.Tmp_MONHOC', N'MONHOC', 'OBJECT' 
GO
ALTER TABLE dbo.MONHOC ADD CONSTRAINT
	PK_MONHOC PRIMARY KEY CLUSTERED 
	(
	MAMH
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
COMMIT
select Has_Perms_By_Name(N'dbo.MONHOC', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.MONHOC', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.MONHOC', 'Object', 'CONTROL') as Contr_Per 