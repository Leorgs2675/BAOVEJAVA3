USE [PTPM_FINALLY_SOF203_PH55452]
GO

SELECT [id]
      ,[ma]
      ,[ten]
      ,[so_trang]
      ,[don_gia]
      ,[id_nxb]
      ,[trang_thai]
  FROM [dbo].[sach]

GO


SELECT ma,ten,so_trang,don_gia FROM sach
INSERT INTO sach(ma,ten,so_trang,don_gia)
VALUES ('PH55452','DUA EM VAO DOI',267,500)