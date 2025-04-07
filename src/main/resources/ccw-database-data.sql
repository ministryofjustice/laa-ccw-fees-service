INSERT INTO CCW.VAT_RATES(RATE_PERCENTAGE) VALUES(20.00);

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

INSERT INTO CCW.FIXED_FEES(AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION)
VALUES
    ( 86.00, 'FPL01', 'LVL1', 'LDN'),
    ( 86.00, 'FPL02', 'LVL1', 'LDN'),
    (230.00, 'FPL02', 'LVL2CH', 'LDN'),
    (138.00, 'FPL02', 'SFC', 'LDN'),
    ( 86.00, 'FPL04', 'LVL1', 'LDN'),
    (230.00, 'FPL04', 'LVL2CH', 'LDN'),
    ( 86.00, 'FPL08', 'LVL1', 'LDN'),
    (230.00, 'FPL08', 'LVL2CH', 'LDN'),
    (241.00, 'FPL08', 'LVL2FI', 'LDN'),
    (145.00, 'FPL08', 'SFF', 'LDN'),
    (146.00, 'FPL10', 'DPF', 'LDN'),
    (230.00, 'FPL11', 'LVL2CH', 'LDN'),
    (138.00, 'FPL11', 'SFC', 'LDN'),
    (230.00, 'FPL13', 'LVL2CH', 'LDN')
;

INSERT INTO CCW.FIXED_FEES(AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION)
VALUES
    ( 86.00, 'FPL01', 'LVL1', 'NLDN'),
    ( 86.00, 'FPL02', 'LVL1', 'NLDN'),
    (199.00, 'FPL02', 'LVL2CH', 'NLDN'),
    (119.00, 'FPL02', 'SFC', 'NLDN'),
    ( 86.00, 'FPL04', 'LVL1', 'NLDN'),
    (199.00, 'FPL04', 'LVL2CH', 'NLDN'),
    ( 86.00, 'FPL08', 'LVL1', 'NLDN'),
    (199.00, 'FPL08', 'LVL2CH', 'NLDN'),
    (208.00, 'FPL08', 'LVL2FI', 'NLDN'),
    (125.00, 'FPL08', 'SFF', 'NLDN'),
    (146.00, 'FPL10', 'DPF', 'NLDN'),
    (199.00, 'FPL11', 'LVL2CH', 'NLDN'),
    (119.00, 'FPL11', 'SFC', 'NLDN'),
    (199.00, 'FPL13', 'LVL2CH', 'NLDN')
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
    ('IMMSTAD', 'Immigration standard fee','A'),
    ('IMMBO02', 'HO interview fee','O'),
    ('IMMBO03', 'CMRH Oral','O'),
    ('IMMBO04', 'CMRH Telephone','O'),
    ('IMMBO05', 'Substantive hearing','O'),
    ('IMMBO06', 'Adjourned hearing','O'),
    ('IMMBO07', 'Detention travel and waiting','OM'),
    ('IMMBO08', 'JR form filling costs','OM'),
    ('IMMBO09', 'NRM advice','O')
;

INSERT INTO CCW.CASE_STAGES(CASE_STAGE_ID)
VALUES
    ('IMM01'),
    ('IMM02'),
    ('IMM03')
;

INSERT INTO CCW.CASE_STAGES_COMBINATIONS(MATTER_CODE_1,CASE_STAGES)
VALUES
    ('IALB', 'IMM01'),
    ('IACF', 'IMM02'),
    ('IMLB', 'IMM03')
;

INSERT INTO CCW.FIXED_FEES(AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION)
VALUES
    (  413.00, 'IMM01', 'IMMSTAD', 'NA'),
    (  266.00, 'IMM01', 'IMMBO02', 'NA'),
    (    0.00, 'IMM01', 'IMMBO07', 'NA'),
    (    0.00, 'IMM01', 'IMMBO08', 'NA'),
    (  150.00, 'IMM01', 'IMMBO09', 'NA'),
    ( 1009.00, 'IMM02', 'IMMSTAD', 'NA'),
    (  166.00, 'IMM02', 'IMMBO03', 'NA'),
    (   90.00, 'IMM02', 'IMMBO04', 'NA'),
    (  302.00, 'IMM02', 'IMMBO05', 'NA'),
    (  161.00, 'IMM02', 'IMMBO06', 'NA'),
    (  150.00, 'IMM02', 'IMMBO09', 'NA'),
    (  234.00, 'IMM03', 'IMMSTAD', 'NA'),
    (  266.00, 'IMM03', 'IMMBO02', 'NA'),
    (  150.00, 'IMM03', 'IMMBO09', 'NA')
;