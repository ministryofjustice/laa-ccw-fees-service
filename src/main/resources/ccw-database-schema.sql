DROP SCHEMA IF EXISTS CCW CASCADE;

CREATE SCHEMA CCW;

CREATE TABLE CCW.VAT_RATES
(
    VAT_RATE_ID      INTEGER      NOT NULL,
    RATE_PERCENTAGE  NUMERIC(7,2) NOT NULL,
    CONSTRAINT pk_vat_rate_id PRIMARY KEY (vat_rate_id)

);

CREATE TABLE CCW.LAW_TYPES
(
    LAW_TYPE_ID         VARCHAR(10) NOT NULL,
    DESCRIPTION         VARCHAR(150) NULL,
    CONSTRAINT pk_law_type_id PRIMARY KEY (law_type_id)
);

CREATE TABLE CCW.MATTER_CODES_1
(
    MATTER_CODE_ID      VARCHAR(10)  NOT NULL,
    DESCRIPTION         VARCHAR(150) NULL,
    LAW_TYPE            VARCHAR(10)  NOT NULL,
    CONSTRAINT pk_matter_codes_1_id PRIMARY KEY (matter_code_id),
    CONSTRAINT fk_law_types_law_type_id FOREIGN KEY (LAW_TYPE) REFERENCES CCW.LAW_TYPES(LAW_TYPE_ID)
);

CREATE TABLE CCW.MATTER_CODES_2
(
    MATTER_CODE_ID      VARCHAR(10)  NOT NULL,
    DESCRIPTION         VARCHAR(150) NULL,
    CONSTRAINT pk_matter_codes_2_id PRIMARY KEY (matter_code_id)
);

CREATE TABLE CCW.CASE_STAGES
(
    CASE_STAGE_ID       VARCHAR(10)  NOT NULL,
    DESCRIPTION         VARCHAR(150) NULL,
    CONSTRAINT pk_case_stages_id PRIMARY KEY (case_stage_id)
);

CREATE TABLE CCW.PROVIDER_LOCATIONS
(
    PROVIDER_LOCATION_ID    VARCHAR(10)  NOT NULL,
    DESCRIPTION             VARCHAR(150) NOT NULL,
    CONSTRAINT pk_provider_locations_id PRIMARY KEY (provider_location_id)
);

CREATE TABLE CCW.LEVEL_CODES
(
    LEVEL_CODE_ID       VARCHAR(10)  NOT NULL,
    DESCRIPTION         VARCHAR(150) NOT NULL,
    TYPE                VARCHAR(2)   NOT NULL,
    CONSTRAINT pk_level_codes_id PRIMARY KEY (level_code_id)
);

CREATE TABLE CCW.MATTER_CODES_COMBINATIONS
(
    MATTER_CODE_1   VARCHAR(10)   NOT NULL,
    MATTER_CODE_2   VARCHAR(10)   NOT NULL,
    CONSTRAINT fk_mcc_matter_codes_1_matter_code_1 FOREIGN KEY (MATTER_CODE_1) REFERENCES CCW.MATTER_CODES_1(MATTER_CODE_ID),
    CONSTRAINT fk_mcc_matter_codes_2_matter_code_2 FOREIGN KEY (MATTER_CODE_2) REFERENCES CCW.MATTER_CODES_2(MATTER_CODE_ID)
);

CREATE TABLE CCW.CASE_STAGES_COMBINATIONS
(
    MATTER_CODE_1   VARCHAR(10)   NOT NULL,
    CASE_STAGES     VARCHAR(10)   NOT NULL,
    CONSTRAINT fk_csc_matter_codes_1_matter_code_1 FOREIGN KEY (MATTER_CODE_1) REFERENCES CCW.MATTER_CODES_1(MATTER_CODE_ID),
    CONSTRAINT fk_csc_case_stages_case_stages FOREIGN KEY (CASE_STAGES) REFERENCES CCW.CASE_STAGES(CASE_STAGE_ID)
);

CREATE TABLE CCW.FIXED_FEES
(
    FEE_ID             INTEGER      NOT NULL,
    AMOUNT             NUMERIC(8,2) NOT NULL,
    CASE_STAGE         VARCHAR(10)  NOT NULL,
    LEVEL_CODE         VARCHAR(10)  NOT NULL,
    PROVIDER_LOCATION  VARCHAR(10)  NOT NULL,
    CONSTRAINT pk_fee_id PRIMARY KEY (fee_id),
    CONSTRAINT fk_fix_case_stages_case_stage FOREIGN KEY (CASE_STAGE) REFERENCES CCW.CASE_STAGES(CASE_STAGE_ID),
    CONSTRAINT fk_fix_level_codes_level_code FOREIGN KEY (LEVEL_CODE) REFERENCES CCW.LEVEL_CODES(LEVEL_CODE_ID),
    CONSTRAINT fk_fix_provider_locations_provider_location FOREIGN KEY (PROVIDER_LOCATION) REFERENCES CCW.PROVIDER_LOCATIONS(PROVIDER_LOCATION_ID)
);

CREATE TABLE CCW.INVOICES
(
    INVOICE_ID          CHAR (36)       NOT NULL,
    FEE_AMOUNT          NUMERIC(8,2)    NOT NULL,
    PROVIDER_ID         VARCHAR(20)     NOT NULL,
    OFFICE_ID           VARCHAR(20)     NOT NULL,
    INVOICE_NUMBER      INTEGER         NOT NULL,
    INVOICE_DATE        DATE            NOT NULL,
    CONSTRAINT pk_invoice_id PRIMARY KEY (invoice_id)
);

CREATE TABLE CCW.TEXT_RESOURCES
(
    RESOURCE_ID         VARCHAR(50)     NOT NULL,
    TEXT                VARCHAR(300)    NOT NULL,
    CONSTRAINT pk_resource_id PRIMARY KEY (resource_id)
);

CREATE VIEW CCW.VW_LEVEL_CODE_FEES AS
    SELECT F.FEE_ID, F.AMOUNT, F.LEVEL_CODE, F.PROVIDER_LOCATION, F.CASE_STAGE,
        L.TYPE, L.DESCRIPTION FROM
        CCW.FIXED_FEES F, CCW.LEVEL_CODES L WHERE
        F.LEVEL_CODE = L.LEVEL_CODE_ID;

CREATE VIEW CCW.VW_MATTER_CODE_COMBINATIONS AS
    SELECT M2.MATTER_CODE_ID, M2.DESCRIPTION, MCC.MATTER_CODE_1 FROM
        CCW.MATTER_CODES_2 M2, CCW.MATTER_CODES_COMBINATIONS MCC WHERE
        M2.MATTER_CODE_ID = MCC.MATTER_CODE_2;

CREATE VIEW CCW.VW_CASE_STAGE_COMBINATIONS AS
SELECT CS.CASE_STAGE_ID, CS.DESCRIPTION, CSC.MATTER_CODE_1 FROM
        CCW.CASE_STAGES CS, CCW.CASE_STAGES_COMBINATIONS CSC WHERE
        CS.CASE_STAGE_ID = CSC.CASE_STAGES;