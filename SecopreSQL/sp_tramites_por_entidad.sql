use secopre;

DELIMITER $$
CREATE DEFINER=`secopre`@`%` PROCEDURE `prc_report_movs_x_entidad`(IN P_INITIAL_DATE VARCHAR(20),IN P_FINAL_DATE VARCHAR(20), IN P_USER VARCHAR(20))
BEGIN

    DECLARE V_INITIAL_DATE date;
    DECLARE V_FINAL_DATE date;
    DECLARE V_INITIAL_MONTH VARCHAR(20);
    DECLARE V_FINAL_MONTH VARCHAR(20);
    DECLARE V_YEAR VARCHAR(20);
    
   
    -- SET V_INITIAL_DATE =  STR_TO_DATE(P_INITIAL_DATE,'%d/%m/%Y');
    -- SET V_FINAL_DATE   =  STR_TO_DATE(P_FINAL_DATE,'%d/%m/%Y');
   
    -- SET V_INITIAL_MONTH =  DATE_FORMAT( V_INITIAL_DATE ,'%m')-1;
    -- SET V_FINAL_MONTH   =  DATE_FORMAT( V_FINAL_DATE ,'%m')-1;
    -- SET V_YEAR    =  DATE_FORMAT( V_FINAL_DATE ,'%Y');
    
		  
         SELECT TIPO_GENERAL,
	       ID,
	       TIPO_MOVIMIENTO,
	       ENTIDAD,
	       DESCRIPCION,
	       PARTIDA,
	       DESCRIPTION_PARTIDA,
	       MONTO_MENSUAL_ASIGNADO MONTO_MENSUAL_ASIGNADO,
	       ANNUAL_AMOUNT          ANNUAL_AMOUNT,
               INITIAL_MONTH,
               FINAL_MONTH,
               FN_GET_FECHA_ACTUAL() CURDATE
         FROM
         (
               SELECT CASE MT.ID  
                         WHEN 1 THEN 'AMPLICACIONES LIQUIDAS'
                         WHEN 2 THEN 'REDUCCIONES LIQUIDAS'
                         WHEN 3 THEN 'MOVIMIENTOS COMPENSADOS'
                         WHEN 4 THEN 'ADICION DE PARTIDAS'
                      ELSE MT.DESCRIPTION 
                      END AS  TIPO_GENERAL,
                      CASE MT.ID  
                         WHEN 1 THEN 34
                         WHEN 2 THEN 33
                         WHEN 3 THEN 
                            CASE SIGN(RD.MOVEMENT_TYPE_ID)  
                               WHEN -1 THEN 31
                               WHEN 1 THEN 32
                            END 
                      ELSE MT.DESCRIPTION 
                      END AS ID,
                      CASE MT.ID  
                         WHEN 1 THEN 'AMPLICACION LIQUIDA'
                         WHEN 2 THEN 'REDUCCION LIQUIDA'
                         WHEN 3 THEN 
                            CASE SIGN(RD.MOVEMENT_TYPE_ID)  
                               WHEN -1 THEN 'REDUCCION COMPENSADO' 
                               WHEN 1 THEN 'AMPLIACION COMPENSADO' 
                            END 
                      ELSE MT.DESCRIPTION 
                      END AS TIPO_MOVIMIENTO,
                      D.STATE_ID ENTIDAD,
                      D.ENTITY  DESCRIPCION,
                      E.CODE PARTIDA,
                      E.DESCRIPTION AS DESCRIPTION_PARTIDA,
                      RD.MONTH_AMOUNT MONTO_MENSUAL_ASIGNADO,
                      RD.TOTAL_AMOUNT ANNUAL_AMOUNT,
                      RD.INITIAL_MONTH +1 INITIAL_MONTH,
                      RD.FINAL_MONTH + 1 FINAL_MONTH
		 FROM secopre.REQUEST_DETAIL RD,	
		      secopre.REQUEST R,
		      secopre.MOVEMENT_TYPE MT,
		      secopre.ENTRY E,
		      secopre.DISTRICT D,
                      secopre.DISTRICT_USER UD,
                      secopre.REQUEST_HISTORY RH,
		      secopre.WORKFLOW_CONFIG WC,
		      secopre.STAGE_CONFIG SC
		WHERE R.ID = RD.REQUEST_ID
		  AND UD.DISTRICT_ID = D.ID
		  AND UD.USER_ID = P_USER
		  AND MT.ID = R.MOVEMENT_TYPE_ID
                  AND D.ID= R.DISTRICT_ID
		  AND E.STATUS = 'ACTIVE'
		  AND RD.ENTRY_ID = E.ID
		  AND RH.REQUEST_ID = R.ID
                  AND RH.ACTIVE = 1
		  AND RH.WORKFLOW_CONFIG_ID = WC.ID
	          AND WC.NEXT_STAGE_CONFIG = SC.ID
		  -- AND SC.IS_OPERATED = 1
		  AND SC.STAGE_ID IN (1,10,11)
 		  AND R.LAST_UPDATE BETWEEN STR_TO_DATE(CONCAT(P_INITIAL_DATE,' ','00:00:00') ,'%d/%m/%Y %H:%i:%s') AND STR_TO_DATE(CONCAT(P_FINAL_DATE,' ','23:59:59'),'%d/%m/%Y %H:%i:%s')
		  ) MOV
		  ORDER BY ID,ENTIDAD DESC;
		  
		  
		  
    END$$
    DELIMITER ;