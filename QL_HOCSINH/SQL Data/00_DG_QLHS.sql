/*
   Sunday, October 08, 20178:35:13 PM
   User: anhquoc
   Server: ANHQUOC-PC\ANHQUOCPC
   Database: QL_HOCSINH
   Application: 
*/

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
ALTER TABLE dbo.QUYDINH SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.KHOI ADD CONSTRAINT
	PK_KHOI PRIMARY KEY CLUSTERED 
	(
	MAKHOI
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.KHOI SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.KHOI', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.KHOI', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.KHOI', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.LOP ADD CONSTRAINT
	FK_LOP_KHOI FOREIGN KEY
	(
	MAKHOI
	) REFERENCES dbo.KHOI
	(
	MAKHOI
	) ON UPDATE  CASCADE 
	 ON DELETE  CASCADE 
	
GO
ALTER TABLE dbo.LOP SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.LOP', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.LOP', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.LOP', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.BANGDIEM ADD CONSTRAINT
	FK_BANGDIEM_QUYDINH FOREIGN KEY
	(
	MAQD
	) REFERENCES dbo.QUYDINH
	(
	MAQD
	) ON UPDATE  CASCADE 
	 ON DELETE  CASCADE 
	
GO
ALTER TABLE dbo.BANGDIEM SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.BANGDIEM', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.BANGDIEM', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.BANGDIEM', 'Object', 'CONTROL') as Contr_Per 