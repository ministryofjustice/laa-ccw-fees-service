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

INSERT INTO CCW.CASE_STAGES(CASE_STAGE_ID, DESCRIPTION)
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

INSERT INTO CCW.MATTER_CODES_1(MATTER_CODE_ID, LAW_TYPE, DESCRIPTION)
VALUES
    ('IALB', 'IMM', 'Asylum - Stage 1 (LH)'),
    ('IACF', 'IMM', 'Asylum - Stage 2e (CLR)'),
    ('IMXC', 'IMM', 'Immigration - CLR Work Not Subject to the Standard Fee Scheme'),
    ('IAXL', 'IMM', 'Asylum - LH Work Not Subject to the Standard Fee Scheme'),
    ('IMLB', 'IMM', 'Immigration - Stage 1 (LH)')
;

INSERT INTO CCW.MATTER_CODES_2(MATTER_CODE_ID, DESCRIPTION)
VALUES
    ('IASY', 'Asylum Application'),
    ('IBAI', 'Bail (Hourly Rates)'),
    ('IUAS', 'Unaccompanied Asylum-Seeking Children (Hourly Rates)'),
    ('IDOM', 'Domestic Violence')
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
    ('_IMHOI', 'HO interview fee','OU'),
    ('_IMORL', 'CMRH Oral','OU'),
    ('_IMTEL', 'CMRH Telephone','OU'),
    ('_IMSUH', 'Substantive hearing','O'),
    ('_IMADJ', 'Adjourned hearing','OU'),
    ('_IMDET', 'Detention travel and waiting','OF'),
    ('_IMJRF', 'JR form filling costs','OF'),
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
    (  166.00, '_IMM01', '_IMORL', 'NA',302),
    (   90.00, '_IMM01', '_IMTEL', 'NA',303),
    (  302.00, '_IMM01', '_IMSUH', 'NA',304),
    (  161.00, '_IMM01', '_IMADJ', 'NA',305),
    (    0.00, '_IMM01', '_IMDET', 'NA',306),
    (    0.00, '_IMM01', '_IMJRF', 'NA',307),
    (  150.00, '_IMM01', '_IMNRM', 'NA',308),

    ( 1009.00, '_IMM02', '_IMSTD', 'NA',320),
    (  266.00, '_IMM02', '_IMHOI', 'NA',321),
    (  166.00, '_IMM02', '_IMORL', 'NA',322),
    (   90.00, '_IMM02', '_IMTEL', 'NA',323),
    (  302.00, '_IMM02', '_IMSUH', 'NA',324),
    (  161.00, '_IMM02', '_IMADJ', 'NA',325),
    (    0.00, '_IMM02', '_IMDET', 'NA',326),
    (    0.00, '_IMM02', '_IMJRF', 'NA',327),
    (  150.00, '_IMM02', '_IMNRM', 'NA',328),

    (  234.00, '_IMM03', '_IMSTD', 'NA',340),
    (  266.00, '_IMM03', '_IMHOI', 'NA',341),
    (  166.00, '_IMM03', '_IMORL', 'NA',342),
    (   90.00, '_IMM03', '_IMTEL', 'NA',343),
    (  302.00, '_IMM03', '_IMSUH', 'NA',344),
    (  161.00, '_IMM03', '_IMADJ', 'NA',345),
    (    0.00, '_IMM03', '_IMDET', 'NA',346),
    (    0.00, '_IMM03', '_IMJRF', 'NA',347),
    (  150.00, '_IMM03', '_IMNRM', 'NA',348)
;

INSERT INTO CCW.TEXT_RESOURCES(RESOURCE_ID,TEXT)
VALUES
    ('_IMSTD_FQ', 'Immigration standard fee'),
    ('_IMHOI_FQ', 'Number of Home Office interviews'),
    ('_IMORL_FQ', 'Number of Oral CMR hearings'),
    ('_IMTEL_FQ', 'Number of telephone CMR hearings'),
    ('_IMSUH_FQ', 'Was there a substantive hearing?'),
    ('_IMADJ_FQ', 'Number of adjourned hearings'),
    ('_IMDET_FQ', 'Detention travel and waiting costs (optional)'),
    ('_IMJRF_FQ', 'Form filling costs (optional)'),
    ('_IMNRM_FQ', 'Was this NRM advice?')
;
