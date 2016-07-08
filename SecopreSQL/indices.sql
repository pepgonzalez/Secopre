SET FOREIGN_KEY_CHECKS = 0;

ALTER TABLE secopre.REQUEST_HISTORY
ADD CONSTRAINT FK_RH_R
FOREIGN KEY (REQUEST_ID)
REFERENCES secopre.REQUEST(ID);

ALTER TABLE secopre.REQUEST_CONFIG
ADD CONSTRAINT FK_RC_R
FOREIGN KEY (REQUEST_ID)
REFERENCES secopre.REQUEST(ID);

ALTER TABLE secopre.FORMALITY 
ADD PRIMARY KEY(ID);

ALTER TABLE secopre.REQUEST_CONFIG
ADD CONSTRAINT FK_RC_FLTY
FOREIGN KEY (FORMALITY_ID)
REFERENCES secopre.FORMALITY(ID);

ALTER TABLE secopre.WORKFLOW_CONFIG
ADD CONSTRAINT FK_WC_S
FOREIGN KEY (STATUS_ID)
REFERENCES secopre.STATUS(ID);


ALTER TABLE secopre.REQUEST_DETAIL
ADD CONSTRAINT FK_RD_R
FOREIGN KEY (REQUEST_ID)
REFERENCES secopre.REQUEST(ID);

SET FOREIGN_KEY_CHECKS = 1;