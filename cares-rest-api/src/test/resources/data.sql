INSERT INTO CWSINT.ADDRS_T (IDENTIFIER,CITY_NM,EMRG_TELNO,EMRG_EXTNO,FRG_ADRT_B,GVR_ENTC,MSG_TEL_NO,MSG_EXT_NO,HEADER_ADR,PRM_TEL_NO,PRM_EXT_NO,STATE_C,STREET_NM,STREET_NO,ZIP_NO,LST_UPD_ID,LST_UPD_TS,ADDR_DSC,ZIP_SFX_NO,POSTDIR_CD,PREDIR_CD,ST_SFX_C,UNT_DSGC,UNIT_NO,LAT,LNG) VALUES (
'test-ad-1','San Diego           ',0,0,'N',1104,0,0,'                                   ',4120006613,0,1828,'Bunker Hill Circle                      ','6666      ',92117,'0Nu','2001-12-18 17:20:30.057','Ms. Evans is homeless.                                                                                                  ',0,'  ','  ',0,0,'        ',NULL,NULL);
INSERT INTO CWSINT.ADDRS_T (IDENTIFIER,CITY_NM,EMRG_TELNO,EMRG_EXTNO,FRG_ADRT_B,GVR_ENTC,MSG_TEL_NO,MSG_EXT_NO,HEADER_ADR,PRM_TEL_NO,PRM_EXT_NO,STATE_C,STREET_NM,STREET_NO,ZIP_NO,LST_UPD_ID,LST_UPD_TS,ADDR_DSC,ZIP_SFX_NO,POSTDIR_CD,PREDIR_CD,ST_SFX_C,UNT_DSGC,UNIT_NO,LAT,LNG) VALUES (
'test-ad-2','Sacramento          ',0,0,'N',1126,0,0,'                                   ',9430007990,0,1828,'Columbus Terrace                        ','2435      ',95833,'0Ib','2000-10-20 13:49:49.907','                                                                                                                        ',0,'  ','  ',0,0,'        ',NULL,NULL);

INSERT INTO CWSINT.ADDRS_T (IDENTIFIER,CITY_NM,EMRG_TELNO,EMRG_EXTNO,FRG_ADRT_B,GVR_ENTC,MSG_TEL_NO,MSG_EXT_NO,HEADER_ADR,PRM_TEL_NO,PRM_EXT_NO,STATE_C,STREET_NM,STREET_NO,ZIP_NO,LST_UPD_ID,LST_UPD_TS,ADDR_DSC,ZIP_SFX_NO,POSTDIR_CD,PREDIR_CD,ST_SFX_C,UNT_DSGC,UNIT_NO,LAT,LNG) VALUES (
'test-ad-10','Salida              ',0,0,'N',1126,0,0,'                                   ',8420001795,0,1828,'Hoepker Terrace                         ','900       ',95368,'0MV','2002-09-03 09:14:54.718','                                                                                                                        ',0,'  ','  ',0,0,'        ',NULL,NULL);

INSERT INTO CWSINT.CL_ADDRT (IDENTIFIER,ADDR_TPC,BK_INMT_ID,EFF_END_DT,EFF_STRTDT,LST_UPD_ID,LST_UPD_TS,FKADDRS_T,FKCLIENT_T,HOMLES_IND,FKREFERL_T) VALUES (
'AaiE5jP0Bi',32,'          ',NULL,'1999-01-13','q48','2005-12-09 16:20:43.320','test-ad-1','test-cl-1','N','KHwuNPv0Bi');
INSERT INTO CWSINT.CL_ADDRT (IDENTIFIER,ADDR_TPC,BK_INMT_ID,EFF_END_DT,EFF_STRTDT,LST_UPD_ID,LST_UPD_TS,FKADDRS_T,FKCLIENT_T,HOMLES_IND,FKREFERL_T) VALUES (
'AaO68nK09t',32,'          ',NULL,'1998-06-08','q48','2005-12-09 16:20:43.320','test-ad-2','test-cl-1','N','QfQPZRK09t');

INSERT INTO CWSINT.CL_ADDRT (IDENTIFIER,ADDR_TPC,BK_INMT_ID,EFF_END_DT,EFF_STRTDT,LST_UPD_ID,LST_UPD_TS,FKADDRS_T,FKCLIENT_T,HOMLES_IND,FKREFERL_T) VALUES (
'AaV9KlC04O',32,'          ',NULL,'2002-09-01','q48','2005-12-09 16:20:43.320','test-ad-10','test-cl-10','N','1arsht804O');
