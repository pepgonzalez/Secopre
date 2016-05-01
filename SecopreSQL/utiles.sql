SELECT SC1.ID, S1.DESCRIPTION, WC.WF_CFG_CODE, SC2.ID, S2.DESCRIPTION, S.DESCRIPTION
  FROM secopre.WORKFLOW_CONFIG WC,
 	   secopre.STAGE_CONFIG SC1,
 	   secopre.STAGE_CONFIG SC2,
 	   secopre.STAGE S1,
 	   secopre.STAGE S2,
 	   secopre.STATUS S
 WHERE WC.WORKFLOW_ID = 2
   AND WC.STAGE_CONFIG_ID = SC1.ID
   AND WC.NEXT_STAGE_CONFIG = SC2.ID
   AND SC1.STAGE_ID = S1.ID
   AND SC2.STAGE_ID = S2.ID
   AND WC.STATUS_ID = S.ID
   
-- roles de un usuario

select UR.USER_ID, U.USERNAME, UR.ROLE_ID, R.ROLENAME
FROM secopre.USER_ROLE UR,
	 secopre.user U,
	 secopre.role R
where UR.USER_ID = U.ID
  and UR.ROLE_ID = R.ID
  and U.ID = 36;