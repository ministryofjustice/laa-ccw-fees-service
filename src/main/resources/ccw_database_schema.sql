CREATE SCHEMA IF NOT EXISTS CCW;

CREATE TABLE IF NOT EXISTS CCW.ACTIVITIES
(
    ACTIVITY_ID         UUID         NOT NULL DEFAULT sys_guid(),
    DESCRIPTION         VARCHAR(150) NOT NULL,
    CONSTRAINT pk_activity_id PRIMARY KEY (activity_id)
);

CREATE TABLE IF NOT EXISTS CCW.FEE_SCHEMES
(
    FEE_SCHEME_ID   UUID         NOT NULL DEFAULT sys_guid(),
    NAME            VARCHAR(150) NOT NULL,
    START_DATE      DATE NULL,
    END_DATE        DATE NULL,
    CONSTRAINT pk_fee_scheme_id PRIMARY KEY (fee_scheme_id)
);

CREATE TABLE IF NOT EXISTS CCW.MATTER_CODES
(
    MATTER_ID           UUID         NOT NULL DEFAULT sys_guid(),
    DESCRIPTION         VARCHAR(150) NOT NULL,
    CONSTRAINT pk_matter_id PRIMARY KEY (matter_id)
);

CREATE TABLE IF NOT EXISTS CCW.FEE_SCHEME_MT
(
    MATTER_ID           UUID         NOT NULL,
    FEE_SCHEME_ID       UUID         NOT NULL,
    FEE_TYPE            SOMETHING    NOT NULL,
    CONSTRAINT fk_matter_codes_matter_id FOREIGN KEY (MATTER_ID) REFERENCES MATTER_CODES(MATTER_ID),
    CONSTRAINT fk_fee_schemes_fee_scheme_id FOREIGN KEY (FEE_SCHEME_ID) REFERENCES FEE_SCHEMES(FEE_SCHEME_ID)
);

CREATE TABLE IF NOT EXISTS CCW.PROVIDER_CATEGORIES
(
    PROVIDER_CATEGORY_ID    UUID            NOT NULL DEFAULT sys_guid(),
    DESCRIPTION             VARCHAR(150)    NOT NULL,
    PROVIDER_ROLE           SOMETHING       NOT NULL,
    CONSTRAINT pk_provider_category_id PRIMARY KEY (provider_category_id)
);

CREATE TABLE IF NOT EXISTS CCW.FIXED_FEES
(
    FEE_ID                  UUID        NOT NULL DEFAULT sys_guid(),
    PROVIDER_CATEGORY_ID    UUID        NOT NULL,
    UNIT                    SOMETHING   NULL,
    FIXED_VALUE             SOMETHING   NULL,
    TYPE                    SOMETHING   NULL,
    FIELD                   SOMETHING   NOT NULL,
    CONSTRAINT pk_fee_id PRIMARY KEY (fee_id),
    CONSTRAINT fk_provider_categories_provider_category_id FOREIGN KEY (PROVIDER_CATEGORY_ID) REFERENCES PROVIDER_CATEGORIES(PROVIDER_CATEGORY_ID)
);

CREATE TABLE IF NOT EXISTS CCW.FEE_SCHEME_FIXEDFEE
(
    FEE_SCHEME_ID   UUID    NOT NULL,
    FIXED_FEE_ID    UUID    NOT NULL,
    CONSTRAINT fk_fee_schemes_fee_scheme_id FOREIGN KEY (FEE_SCHEME_ID) REFERENCES FEE_SCHEMES(FEE_SCHEME_ID)
    CONSTRAINT fk_fixed_fees_fee_id FOREIGN KEY (FIXED_FEE_ID) REFERENCES FIXED_FEES(FEE_ID)
);

done: activity, matter codes, fee scheme, feescheme_mt, fee scheme_fixedfeee, fixed fees, provider categories
next: hourly fees -> feescheme_hourlyfee