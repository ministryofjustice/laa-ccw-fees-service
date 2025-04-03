INSERT INTO CCW.VAT_RATES(RATE_PERCENTAGE) VALUES(20.00);

INSERT INTO CCW.LAW_TYPES(LAW_TYPE_ID,DESCRIPTION)
VALUES
    ('FAM','Family')
;

------------------ family law -----------------------
INSERT INTO CCW.MATTER_CODES_1(MATTER_CODE_ID, LAW_TYPE)
VALUES
    ('FAMA','FAM'),
    ('FAMC','FAM'),
    ('FAMD','FAM')
;

INSERT INTO CCW.MATTER_CODES_2(MATTER_CODE_ID)
VALUES
    ('FPET'),
    ('FRES'),
    ('FADV'),
    ('FAPP'),
    ('FREP'),
    ('FCHG'),
    ('FCHS'),
    ('FOTH')
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
    ('FPL01'),
    ('FPL02'),
    ('FPL04'),
    ('FPL08'),
    ('FPL10'),
    ('FPL11'),
    ('FPL13')
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

INSERT INTO CCW.FIXED_FEES(AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION)
VALUES
    (  413.00, '_IMM01', '_IMSTD', 'NA'),
    (  266.00, '_IMM01', '_IMHOI', 'NA'),
    (    0.00, '_IMM01', '_IMJRF', 'NA'),
    (  150.00, '_IMM01', '_IMNRM', 'NA'),
    ( 1009.00, '_IMM02', '_IMSTD', 'NA'),
    (  166.00, '_IMM02', '_IMORL', 'NA'),
    (   90.00, '_IMM02', '_IMTEL', 'NA'),
    (  302.00, '_IMM02', '_IMSUH', 'NA'),
    (  161.00, '_IMM02', '_IMADJ', 'NA'),
    (    0.00, '_IMM02', '_IMJRF', 'NA'),
    (  150.00, '_IMM02', '_IMNRM', 'NA'),
    (  234.00, '_IMM03', '_IMSTD', 'NA'),
    (  266.00, '_IMM03', '_IMHOI', 'NA'),
    (    0.00, '_IMM03', '_IMJRF', 'NA'),
    (  150.00, '_IMM03', '_IMNRM', 'NA')
;