/*
   Sunday, October 08, 201712:05:44 PM
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
ALTER TABLE dbo.LOP SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.LOP', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.LOP', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.LOP', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
CREATE TABLE dbo.Tmp_CTLOP
	(
	MAHS varchar(10) NOT NULL,
	MALOP varchar(5) NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_CTLOP SET (LOCK_ESCALATION = TABLE)
GO
IF EXISTS(SELECT * FROM dbo.CTLOP)
	 EXEC('INSERT INTO dbo.Tmp_CTLOP (MAHS, MALOP)
		SELECT MAHS, MALOP FROM dbo.CTLOP WITH (HOLDLOCK TABLOCKX)')
GO
DROP TABLE dbo.CTLOP
GO
EXECUTE sp_rename N'dbo.Tmp_CTLOP', N'CTLOP', 'OBJECT' 
GO
ALTER TABLE dbo.CTLOP ADD CONSTRAINT
	FK_CTLOP_LOP FOREIGN KEY
	(
	MALOP
	) REFERENCES dbo.LOP
	(
	MALOP
	) ON UPDATE  CASCADE 
	 ON DELETE  CASCADE 
	
GO
COMMIT
select Has_Perms_By_Name(N'dbo.CTLOP', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.CTLOP', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.CTLOP', 'Object', 'CONTROL') as Contr_Per 