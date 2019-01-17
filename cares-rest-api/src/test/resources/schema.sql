CREATE TABLE CWSINT.ADDRS_T (
	IDENTIFIER CHAR(10) NOT NULL,
	CITY_NM CHAR(20) DEFAULT ' ' NOT NULL,
	EMRG_TELNO DECIMAL(10,0) DEFAULT 0.0 NOT NULL,
	EMRG_EXTNO INTEGER DEFAULT 0 NOT NULL,
	FRG_ADRT_B CHAR(1) NOT NULL,
	GVR_ENTC SMALLINT DEFAULT 0 NOT NULL,
	MSG_TEL_NO DECIMAL(10,0) DEFAULT 0.0 NOT NULL,
	MSG_EXT_NO INTEGER DEFAULT 0 NOT NULL,
	HEADER_ADR CHAR(35) DEFAULT ' ' NOT NULL,
	PRM_TEL_NO DECIMAL(10,0) DEFAULT 0.0 NOT NULL,
	PRM_EXT_NO INTEGER DEFAULT 0 NOT NULL,
	STATE_C SMALLINT DEFAULT 0 NOT NULL,
	STREET_NM CHAR(40) DEFAULT ' ' NOT NULL,
	STREET_NO CHAR(10) DEFAULT ' ' NOT NULL,
	ZIP_NO INTEGER DEFAULT 0 NOT NULL,
	LST_UPD_ID CHAR(3) NOT NULL,
	LST_UPD_TS TIMESTAMP NOT NULL,
	ADDR_DSC CHAR(120) DEFAULT ' ' NOT NULL,
	ZIP_SFX_NO SMALLINT DEFAULT 0 NOT NULL,
	POSTDIR_CD CHAR(2) DEFAULT ' ' NOT NULL,
	PREDIR_CD CHAR(2) DEFAULT ' ' NOT NULL,
	ST_SFX_C SMALLINT DEFAULT 0 NOT NULL,
	UNT_DSGC SMALLINT DEFAULT 0 NOT NULL,
	UNIT_NO CHAR(8) DEFAULT ' ' NOT NULL,
	LAT DECIMAL(10,6) DEFAULT NULL,
	LNG DECIMAL(10,6) DEFAULT NULL,
	CONSTRAINT IDENTIFIER_ADDRS_T PRIMARY KEY (IDENTIFIER)
) ;
CREATE INDEX ADDRS1IX ON CWSINT.ADDRS_T (PRM_TEL_NO) ;
CREATE INDEX ADDRS2IX ON CWSINT.ADDRS_T (STREET_NM,ZIP_NO,CITY_NM,GVR_ENTC) ;
CREATE INDEX ADDRS3IX ON CWSINT.ADDRS_T (STREET_NM) ;
CREATE INDEX ADDRS4X ON CWSINT.ADDRS_T (STREET_NO) ;
CREATE INDEX ADDRS5X ON CWSINT.ADDRS_T (CITY_NM) ;
CREATE INDEX ADDRS7X ON CWSINT.ADDRS_T (ZIP_NO,ZIP_SFX_NO) ;
CREATE UNIQUE INDEX SQL170915144738000 ON CWSINT.ADDRS_T (IDENTIFIER) ;


CREATE TABLE CWSINT.CL_ADDRT (
	IDENTIFIER CHAR(10) NOT NULL,
	ADDR_TPC SMALLINT NOT NULL,
	BK_INMT_ID CHAR(10) DEFAULT ' ' NOT NULL,
	EFF_END_DT DATE DEFAULT NULL,
	EFF_STRTDT DATE DEFAULT NULL,
	LST_UPD_ID CHAR(3) NOT NULL,
	LST_UPD_TS TIMESTAMP NOT NULL,
	FKADDRS_T CHAR(10) NOT NULL,
	FKCLIENT_T CHAR(10) NOT NULL,
	HOMLES_IND CHAR(1) DEFAULT 'N' NOT NULL,
	FKREFERL_T CHAR(10) DEFAULT NULL,
	CONSTRAINT IDENTIFIER_CL_ADDRT PRIMARY KEY (IDENTIFIER)
) ;
CREATE INDEX CLADDR2 ON CWSINT.CL_ADDRT (FKCLIENT_T,FKADDRS_T) ;
CREATE INDEX CLADDR3 ON CWSINT.CL_ADDRT (FKADDRS_T) ;
CREATE INDEX CLADDR4X ON CWSINT.CL_ADDRT (BK_INMT_ID) ;
CREATE INDEX CLADDR5X ON CWSINT.CL_ADDRT (EFF_END_DT,ADDR_TPC) ;
CREATE INDEX CLADDR6X ON CWSINT.CL_ADDRT (FKREFERL_T) ;
CREATE UNIQUE INDEX SQL170915144845250 ON CWSINT.CL_ADDRT (IDENTIFIER) ;

