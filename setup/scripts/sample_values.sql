INSERT INTO user_test (userId,name,password) VALUES 
(1,'name1','12345')
,(2,'nameEdit','1qaz')
,(3,'string12','string12');

INSERT INTO shop_test (shopId,shopName,address) VALUES 
(1,'shop1','address1')
,(2,'string2','string2')
,(3,'string','string');

INSERT INTO user_shop (usershopId,userId,shopId) VALUES 
(1,1,1)
,(2,2,1)
,(3,2,1);