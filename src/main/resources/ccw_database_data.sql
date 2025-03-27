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
    ('BLT1', 'Bolt on 1'),
    ('BLT2', 'Bolt on 2'),
    ('BLT3', 'Bolt on 3'),
    ('BLT4', 'Bolt on 4'),
    ('BLT5', 'Bolt on 5'),
    ('BLT6', 'Bolt on 6'),
    ('LVL1', 'Level 1'),
    ('LVL2CH', 'Level 2 children'),
    ('LVL2FI', 'Level 2 finance'),
    ('SFC', 'Settlement fee children'),
    ('SFF', 'settlement fee finance'),
    ('DPF', 'Div pet fee')
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

INSERT INTO CCW.FIXED_FEES(FIXED_AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION,FIXED_FEE_ID)
VALUES
    ( 86.00, 'FPL01', 'LVL1', 'LDN', '5736e7c9-ca48-4a5a-857f-00000001'),
    ( 86.00, 'FPL02', 'LVL1', 'LDN', '5736e7c9-ca48-4a5a-857f-00000002'),
    (230.00, 'FPL02', 'LVL2CH', 'LDN', '5736e7c9-ca48-4a5a-857f-00000003'),
    (138.00, 'FPL02', 'SFC', 'LDN', '5736e7c9-ca48-4a5a-857f-00000004'),
    ( 86.00, 'FPL04', 'LVL1', 'LDN', '5736e7c9-ca48-4a5a-857f-00000005'),
    (230.00, 'FPL04', 'LVL2CH', 'LDN', '5736e7c9-ca48-4a5a-857f-00000006'),
    ( 86.00, 'FPL08', 'LVL1', 'LDN', '5736e7c9-ca48-4a5a-857f-00000007'),
    (230.00, 'FPL08', 'LVL2CH', 'LDN', '5736e7c9-ca48-4a5a-857f-00000008'),
    (241.00, 'FPL08', 'LVL2FI', 'LDN', '5736e7c9-ca48-4a5a-857f-00000009'),
    (145.00, 'FPL08', 'SFF', 'LDN', '5736e7c9-ca48-4a5a-857f-00000010'),
    (146.00, 'FPL10', 'DPF', 'LDN', '5736e7c9-ca48-4a5a-857f-00000011'),
    (230.00, 'FPL11', 'LVL2CH', 'LDN', '5736e7c9-ca48-4a5a-857f-00000012'),
    (138.00, 'FPL11', 'SFC', 'LDN', '5736e7c9-ca48-4a5a-857f-00000013'),
    (230.00, 'FPL13', 'LVL2CH', 'LDN', '5736e7c9-ca48-4a5a-857f-00000014')
;

INSERT INTO CCW.FIXED_FEES(FIXED_AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION,FIXED_FEE_ID)
VALUES
    ( 86.00, 'FPL01', 'LVL1', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000001'),
    ( 86.00, 'FPL02', 'LVL1', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000002'),
    (199.00, 'FPL02', 'LVL2CH', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000003'),
    (119.00, 'FPL02', 'SFC', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000004'),
    ( 86.00, 'FPL04', 'LVL1', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000005'),
    (199.00, 'FPL04', 'LVL2CH', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000006'),
    ( 86.00, 'FPL08', 'LVL1', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000007'),
    (199.00, 'FPL08', 'LVL2CH', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000008'),
    (208.00, 'FPL08', 'LVL2FI', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000009'),
    (125.00, 'FPL08', 'SFF', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000010'),
    (146.00, 'FPL10', 'DPF', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000011'),
    (199.00, 'FPL11', 'LVL2CH', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000012'),
    (119.00, 'FPL11', 'SFC', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000013'),
    (199.00, 'FPL13', 'LVL2CH', 'NLDN', '5736e7c9-ca48-4a5a-857e-00000014')
;