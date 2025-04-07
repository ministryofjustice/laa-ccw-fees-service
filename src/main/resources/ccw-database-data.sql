INSERT INTO CCW.VAT_RATES(VAT_RATE_ID,RATE_PERCENTAGE) VALUES(1,20.00);

INSERT INTO CCW.LAW_TYPES(LAW_TYPE_ID,DESCRIPTION)
VALUES
    ('FAM','Family')
;

------------------ family law -----------------------
INSERT INTO CCW.MATTER_CODES_1(MATTER_CODE_ID, LAW_TYPE, DESCRIPTION)
VALUES
    ('FAMA','FAM', 'Divorce/Judicial Seperation/Nullity'),
    ('FAMC','FAM', 'Domestic Abuse'),
    ('FAMD','FAM', 'Private Law Children only')
;

INSERT INTO CCW.MATTER_CODES_2(MATTER_CODE_ID, DESCRIPTION)
VALUES
    ('FPET', 'Client is the petitioner in Divorce/Judicial Separation/Nullity/Dissolution of Civil Partnership'),
    ('FRES', 'Client is the respondent in Divorce/Judicial Separation/Nullity/Dissolution of Civil Partnership'),
    ('FADV', 'Client is seeking advice only'),
    ('FAPP', 'Client is or would be the applicant where proceedings issued'),
    ('FREP', 'Client is or would be the respondent where proceedings issued'),
    ('FCHG', 'Client is a child with a guardian'),
    ('FCHS', 'Client is a child instructing solicitor directly'),
    ('FOTH', 'Other')
;

INSERT INTO CCW.MATTER_CODES_COMBINATIONS(MATTER_CODE_1,MATTER_CODE_2)
VALUES
    ('FAMA', 'FPET'),
    ('FAMA', 'FRES'),
    ('FAMA', 'FADV'),
    ('FAMA', 'FAPP'),
    ('FAMA', 'FREP'),
    ('FAMA', 'FCHG'),
    ('FAMA', 'FCHS'),
    ('FAMA', 'FOTH'),
    ('FAMC', 'FPET'),
    ('FAMC', 'FRES'),
    ('FAMC', 'FADV'),
    ('FAMC', 'FAPP'),
    ('FAMC', 'FREP'),
    ('FAMC', 'FCHG'),
    ('FAMC', 'FCHS'),
    ('FAMC', 'FOTH'),
    ('FAMD', 'FPET'),
    ('FAMD', 'FRES'),
    ('FAMD', 'FADV'),
    ('FAMD', 'FAPP'),
    ('FAMD', 'FREP'),
    ('FAMD', 'FCHG'),
    ('FAMD', 'FCHS'),
    ('FAMD', 'FOTH')
;

INSERT INTO CCW.PROVIDER_LOCATIONS(PROVIDER_LOCATION_ID,DESCRIPTION)
VALUES
    ('NA', 'Not applicable'),
    ('LDN', 'London'),
    ('NLDN', 'Non-London')
;

INSERT INTO CCW.LEVEL_CODES(LEVEL_CODE_ID,DESCRIPTION,TYPE)
VALUES
    ('LVL1', 'Level 1','A'),
    ('LVL2CH', 'Level 2 children','A'),
    ('LVL2FI', 'Level 2 finance','A'),
    ('SFC', 'Settlement fee children','A'),
    ('SFF', 'settlement fee finance','A'),
    ('DPF', 'Div pet fee','A')
;

INSERT INTO CCW.CASE_STAGES(CASE_STAGE_ID)
VALUES
    ('FPL01', 'Level 1'),
    ('FPL02', 'Level 1 + Level 2 children + Settlement fee children'),
    ('FPL04', 'Level 1 + Level 2 children'),
    ('FPL08', 'Level 1 + Level 2 children + Level 2 finance'),
    ('FPL10', 'Div pet fee'),
    ('FPL11', 'Level 2 children + Settlement fee children'),
    ('FPL13', 'Level 2 children')
;

INSERT INTO CCW.CASE_STAGES_COMBINATIONS(MATTER_CODE_1,CASE_STAGES)
VALUES
    ('FAMA', 'FPL01'),
    ('FAMA', 'FPL10'),
    ('FAMC', 'FPL01'),
    ('FAMD', 'FPL01'),
    ('FAMD', 'FPL02'),
    ('FAMD', 'FPL04'),
    ('FAMD', 'FPL08'),
    ('FAMD', 'FPL11'),
    ('FAMD', 'FPL13')
;

INSERT INTO CCW.FIXED_FEES(AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION,FEE_ID)
VALUES
    ( 86.00, 'FPL01', 'LVL1', 'LDN', 100),
    ( 86.00, 'FPL02', 'LVL1', 'LDN', 101),
    (230.00, 'FPL02', 'LVL2CH', 'LDN',102),
    (138.00, 'FPL02', 'SFC', 'LDN',103),
    ( 86.00, 'FPL04', 'LVL1', 'LDN',104),
    (230.00, 'FPL04', 'LVL2CH', 'LDN',105),
    ( 86.00, 'FPL08', 'LVL1', 'LDN',106),
    (230.00, 'FPL08', 'LVL2CH', 'LDN',107),
    (241.00, 'FPL08', 'LVL2FI', 'LDN',108),
    (145.00, 'FPL08', 'SFF', 'LDN',109),
    (146.00, 'FPL10', 'DPF', 'LDN',110),
    (230.00, 'FPL11', 'LVL2CH', 'LDN',111),
    (138.00, 'FPL11', 'SFC', 'LDN',112),
    (230.00, 'FPL13', 'LVL2CH', 'LDN',113)
;

INSERT INTO CCW.FIXED_FEES(AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION,FEE_ID)
VALUES
    ( 86.00, 'FPL01', 'LVL1', 'NLDN',200),
    ( 86.00, 'FPL02', 'LVL1', 'NLDN',201),
    (199.00, 'FPL02', 'LVL2CH', 'NLDN',202),
    (119.00, 'FPL02', 'SFC', 'NLDN',203),
    ( 86.00, 'FPL04', 'LVL1', 'NLDN',204),
    (199.00, 'FPL04', 'LVL2CH', 'NLDN',205),
    ( 86.00, 'FPL08', 'LVL1', 'NLDN',206),
    (199.00, 'FPL08', 'LVL2CH', 'NLDN',207),
    (208.00, 'FPL08', 'LVL2FI', 'NLDN',208),
    (125.00, 'FPL08', 'SFF', 'NLDN',209),
    (146.00, 'FPL10', 'DPF', 'NLDN',210),
    (199.00, 'FPL11', 'LVL2CH', 'NLDN',211),
    (119.00, 'FPL11', 'SFC', 'NLDN',212),
    (199.00, 'FPL13', 'LVL2CH', 'NLDN',213)
;
---------------- immigration law --------------------
INSERT INTO CCW.LAW_TYPES(LAW_TYPE_ID,DESCRIPTION)
VALUES
    ('IMM', 'Immigration')
;

INSERT INTO CCW.MATTER_CODES_1(MATTER_CODE_ID, LAW_TYPE)
VALUES
    ('IALB', 'IMM'),
    ('IACF', 'IMM'),
    ('IMXC', 'IMM'),
    ('IAXL', 'IMM'),
    ('IMLB', 'IMM')
;

INSERT INTO CCW.MATTER_CODES_2(MATTER_CODE_ID)
VALUES
    ('IASY'),
    ('IBAI'),
    ('IUAS'),
    ('IDOM')
;

INSERT INTO CCW.MATTER_CODES_COMBINATIONS(MATTER_CODE_1,MATTER_CODE_2)
VALUES
    ('IALB', 'IASY'),
    ('IACF', 'IASY'),
    ('IMXC', 'IBAI'),
    ('IAXL', 'IUAS'),
    ('IMLB', 'IDOM')
;

INSERT INTO CCW.LEVEL_CODES(LEVEL_CODE_ID,DESCRIPTION,TYPE)
VALUES
    ('_IMSTD', 'Immigration standard fee','A'),
    ('_IMHOI', 'HO interview fee','O'),
    ('_IMORL', 'CMRH Oral','O'),
    ('_IMTEL', 'CMRH Telephone','O'),
    ('_IMSUH', 'Substantive hearing','O'),
    ('_IMADJ', 'Adjourned hearing','O'),
    ('_IMDET', 'Detention travel and waiting','OM'),
    ('_IMJRF', 'JR form filling costs','OM'),
    ('_IMNRM', 'NRM advice','O')
;

INSERT INTO CCW.CASE_STAGES(CASE_STAGE_ID)
VALUES
    ('_IMM01'),
    ('_IMM02'),
    ('_IMM03')
;

INSERT INTO CCW.CASE_STAGES_COMBINATIONS(MATTER_CODE_1,CASE_STAGES)
VALUES
    ('IALB', '_IMM01'),
    ('IACF', '_IMM02'),
    ('IMLB', '_IMM03')
;

INSERT INTO CCW.FIXED_FEES(AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION,FEE_ID)
VALUES
    (  413.00, '_IMM01', '_IMSTD', 'NA',300),
    (  266.00, '_IMM01', '_IMHOI', 'NA',301),
    (    0.00, '_IMM01', '_IMJRF', 'NA',302),
    (  150.00, '_IMM01', '_IMNRM', 'NA',303),
    ( 1009.00, '_IMM02', '_IMSTD', 'NA',304),
    (  166.00, '_IMM02', '_IMORL', 'NA',305),
    (   90.00, '_IMM02', '_IMTEL', 'NA',306),
    (  302.00, '_IMM02', '_IMSUH', 'NA',307),
    (  161.00, '_IMM02', '_IMADJ', 'NA',308),
    (    0.00, '_IMM02', '_IMJRF', 'NA',309),
    (  150.00, '_IMM02', '_IMNRM', 'NA',310),
    (  234.00, '_IMM03', '_IMSTD', 'NA',311),
    (  266.00, '_IMM03', '_IMHOI', 'NA',312),
    (    0.00, '_IMM03', '_IMJRF', 'NA',313),
    (  150.00, '_IMM03', '_IMNRM', 'NA',314)
;