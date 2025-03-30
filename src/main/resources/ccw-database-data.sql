INSERT INTO CCW.MATTER_CODES_1(MATTER_CODE_ID)
VALUES
    ('FAMA'),
    ('FAMC'),
    ('FAMD')
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
    ('LDN', 'London'),
    ('NLDN', 'Non-London')
;

INSERT INTO CCW.LEVEL_CODES(LEVEL_CODE_ID,DESCRIPTION)
VALUES
    ('LVL1', 'Level 1'),
    ('LVL2CH', 'Level 2 children'),
    ('LVL2FI', 'Level 2 finance'),
    ('SFC', 'Settlement fee children'),
    ('SFF', 'settlement fee finance'),
    ('DPF', 'Div pet fee'),

    ('BLT1', 'Bolt on 1'),
    ('BLT2', 'Bolt on 2'),
    ('BLT3', 'Bolt on 3'),
    ('BLT4', 'Bolt on 4'),
    ('BLT5', 'Bolt on 5'),
    ('BLT6', 'Bolt on 6')
;

INSERT INTO CCW.CASE_STAGES(CASE_STAGE_ID)
VALUES
    ('NA'),
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