-- QUERY PARA OBTENER LOS TRAMITES DISPONIBLES POR USUARIO

SELECT DISTINCT (T.ID) AS FORMALITY_ID, 
	T.F_DESC AS DESCRIPTION, 
	T.WORKFLOW_ID, 
	T.AUTHORIZATION_ID, 
	T.CODE, 
	T.CREATE_VALIDATION, 
	T.PROCESS_AFTER_CREATION FROM (
	SELECT F.ID,
		   F.DESCRIPTION F_DESC,
		   F.WORKFLOW_ID,
		   F.AUTHORIZATION_ID,
		   F.CODE,
		   F.CREATE_VALIDATION,
		   F.PROCESS_AFTER_CREATION
	  FROM secopre.FORMALITY F,
		   secopre.FORMALITY_ROLE FR,
		   secopre.ROLE R,
		   secopre.USER_ROLE UR
	 WHERE F.ID = FR.FORMALITY_ID
	   AND FR.ROLE_ID = R.ID
	   AND R.ID = UR.ROLE_ID
	   AND UR.USER_ID = :userId
	) T
							
-- QUERY QUE DICE QUE ROLES PUEDEN INICIAR UN TRAMITE

select F.ID, F.DESCRIPTION, R.ID, R.DESCRIPTION
  from secopre.FORMALITY F,
  	   secopre.formality_role FR,
  	   secopre.role R
 where F.ID = FR.FORMALITY_ID
   and FR.ROLE_ID = R.ID
   
-- QUERY PARA OBTENER LOS ROLES DE USUARIO

select U.ID, U.NICKNAME, U.USERNAME, R.ID, R.ROLENAME, R.DESCRIPTION
  from secopre.user U,
       secopre.user_role UR,
       secopre.role R
 where U.USERNAME LIKE '%angelazul%'
   and U.ID = UR.USER_ID
   and UR.ROLE_ID = R.ID
   
-- QUERY PARA SABER EL GRUPO DE AUTORIZACIONES CORRESPONDIENTE AL TRAMITE
select f.id, 
	   f.description, 
	   f.WORKFLOW_ID, 
	   f.AUTHORIZATION_ID, 
	   a.DESCRIPTION, 
	   a.SUPER_USER_ROLE 
from secopre.formality f,
	 secopre.authorization a
where f.AUTHORIZATION_ID = a.id

-- QUERY PARA SABER EL DETALLE DE LA MATRIZ DE AUTORIZACIONES
-- PUESTOS QUE PUEDEN AUTORIZAR

select A.ID, 
	   A.DESCRIPTION GRP_AUTORIZACION, 
	   AC.STAGE_CONFIG_ID, 
	   SC.STAGE_ID, 
	   S.DESCRIPTION ETAPA, 
	   AC.POSITION_ID, 
	   P.DESCRIPTION PUESTO_AC, 
	   AC.level, 
	   AC.AUTH_AUT_CODE,
	   ACP.POSITION_ID,
	   ACPP.DESCRIPTION
  from secopre.AUTHORIZATION_CONFIG AC,
  	   secopre.authorization A,
  	   secopre.position P,
  	   secopre.stage_config SC,
  	   secopre.stage S,
  	   secopre.authorization_config_position ACP,
  	   secopre.position ACPP
 where A.ID = AC.AUTHORIZATION_ID
   and AC.POSITION_ID = P.ID
   and AC.STAGE_CONFIG_ID = SC.ID
   and SC.STAGE_ID = S.ID
   and AC.ID = ACP.AUTHORIZATION_CONFIG_ID
   and acp.POSITION_ID = acpp.id

-- QUERY PARA PREGUNTAR SI UN USUARIO PUEDE AUTORIZAR

SELECT COUNT(*) AS CAN_AUTHORIZE 
  FROM secopre.AUTHORIZATION_CONFIG AC,
	   secopre.USER U,
	   secopre.AUTHORIZATION_CONFIG_POSITION ACP
 WHERE AC.AUTHORIZATION_ID = :authorizationId
   AND AC.STAGE_CONFIG_ID = :stageConfigId
   AND AC.ID = ACP.AUTHORIZATION_CONFIG_ID
   AND ACP.POSITION_ID = U.POSITION_ID
   AND U.ID = :userId
   
-- QUERY PARA PREGUNTAR SI UN USUARIO ES SUPERUSUARIO DE UN TRAMITE

SELECT COUNT(*) AS IS_SUPER_USER
  FROM secopre.AUTHORIZATION A,
	   secopre.USER_ROLE UR
 WHERE A.ID = :authorizationId
   AND A.SUPER_USER_ROLE = UR.ROLE_ID
   AND UR.USER_ID = :userId	
   
-- QUERY PARA SABER SI UN USUARIO PUEDE CANCELAR UN TRAMITE EN CAPTURA
select COUNT(*) as TOTAL
  from secopre.FORMALITY_CANCEL_ROLE FCR,
	   secopre.USER_ROLE UR
 where UR.ROLE_ID = FCR.ROLE_ID
   and UR.USER_ID = :userId
   and FCR.FORMALITY_ID = :formalityId
   
-- QUERY PARA SABER QUE ROLES PUEDEN CANCELAR EN CAPTURA 

select F.ID, F.DESCRIPTION, R.ID, R.ROLENAME, R.DESCRIPTION
  from secopre.formality_cancel_role FCR,
  	   secopre.formality F,
  	   secopre.role R
 where FCR.FORMALITY_ID = F.ID
   and FCR.ROLE_ID = R.ID

   
-- EN LA MATRIZ DE AUTORIZACIONES. UN USUARIO PUEDE CANCELAR TRAMITE, REGRESAR A CAPTURA SI PUEDE AUTORIZAR O ES SUPERUSUARIO
-- SI PUEDE AUTORIZAR Y NO ES SUPERUSUARIO, AUTORIZA, AVANZA A SIGUIENTE FIRMA
-- SI ES SUPERUSUARIO, OPERA

-- EN LA CAPTURA, EL BOTON DE CANCELAR TRAMITE SE MUESTRA SOLAMENTE SI SE CUMPLE LO SIGUIENTE:
	-- SI ES UN GASTO
	-- SI SE CREO EN EL MES ACTUAL
	-- SI PUEDE EL USUARIO CANCELAR EL TRAMITE