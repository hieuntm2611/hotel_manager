

DECLARE @INDEX INT = 1;
WHILE(@INDEX <= 24)
BEGIN
	INSERT INTO [group_action]
           ([groupId]
           ,[actionId]) VALUES(2, @INDEX);
	SET @INDEX = @INDEX + 1;
END

