/*
   Sunday, October 08, 20171:15:38 PM
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
ALTER TABLE dbo.BANGDIEM
	DROP CONSTRAINT FK_BANGDIEM_HOCSINH
GO
ALTER TABLE dbo.HOCSINH SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.HOCSINH', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.HOCSINH', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.HOCSINH', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.QUYDINH SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.QUYDINH', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.MONHOC SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.MONHOC', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.MONHOC', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.MONHOC', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
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
ALTER TABLE dbo.HT_KIEMTRA SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.HT_KIEMTRA', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
CREATE TABLE dbo.Tmp_BANGDIEM
	(
	MAHS varchar(10) NOT NULL,
	MAMH varchar(10) NOT NULL,
	MAHK varchar(5) NOT NULL,
	MAKT varchar(5) NOT NULL,
	MAQD varchar(5) NULL,
	Diem float(53) NULL,
	KQ nvarchar(5) NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_BANGDIEM SET (LOCK_ESCALATION = TABLE)
GO
IF EXISTS(SELECT * FROM dbo.BANGDIEM)
	 EXEC('INSERT INTO dbo.Tmp_BANGDIEM (MAHS, MAMH, MAHK, MAKT, Diem)
		SELECT MAHS, MAMH, MAHK, MAKT, Diem FROM dbo.BANGDIEM WITH (HOLDLOCK TABLOCKX)')
GO
DROP TABLE dbo.BANGDIEM
GO
EXECUTE sp_rename N'dbo.Tmp_BANGDIEM', N'BANGDIEM', 'OBJECT' 
GO
ALTER TABLE dbo.BANGDIEM ADD CONSTRAINT
	FK_BANGDIEM_HOCSINH FOREIGN KEY
	(
	MAHS
	) REFERENCES dbo.HOCSINH
	(
	MAHS
	) ON UPDATE  CASCADE 
	 ON DELETE  CASCADE 
	
GO
ALTER TABLE dbo.BANGDIEM ADD CONSTRAINT
	FK_BANGDIEM_MONHOC FOREIGN KEY
	(
	MAMH
	) REFERENCES dbo.MONHOC
	(
	MAMH
	) ON UPDATE  CASCADE 
	 ON DELETE  CASCADE 
	
GO
ALTER TABLE dbo.BANGDIEM ADD CONSTRAINT
	FK_BANGDIEM_HT_KIEMTRA FOREIGN KEY
	(
	MAKT
	) REFERENCES dbo.HT_KIEMTRA
	(
	MAKT
	) ON UPDATE  CASCADE 
	 ON DELETE  CASCADE 
	
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
COMMIT
select Has_Perms_By_Name(N'dbo.BANGDIEM', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.BANGDIEM', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.BANGDIEM', 'Object', 'CONTROL') as Contr_Per 