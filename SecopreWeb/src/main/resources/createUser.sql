/*Si el schema aun no esta creado ejecutar esta sentencia*/
create schema secopre;

INSERT INTO `role` VALUES (-1,1,'ROLE_USER');
INSERT INTO `user` VALUES (-1,1,'','jorge.canoc@gmail.com','','jorgekno','$2a$10$wvXkMdLjP3k.XFUeo9zHXORpU7DLCjeqgNGUuEIrcOsfEq5ZJmCvC','admin');
INSERT INTO `user_role` VALUES (-1,-1);

insert into menu values(1,1,'icon-home','Dashboard Home por Usuario',null, 'alert(\'al dashboar por usuario\');','Dashboard', 1,null,null);
insert into menu values(2,1,'icon-settings','Modulo De Administracion',null, 'javascript: void(0);','Administracion', 2,null,null);
insert into menu values(3,1,'icon-home','Modulo Configuracion de Usuarios',null, 'sendRequestJQ(\'auth/adm/usrList\',\'dashboard\',\'initUserAdmin()\');','Usuarios', 3,2,null);

insert into path values(1,1,0,'auth/adm/usrList');

insert into permission values(1,1,'USR_LIST',3);
insert into permission values(2,1,'CONF_USR',2);

insert into role_permission values(-1,1);
insert into role_permission values(-1,2);
