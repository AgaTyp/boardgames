INSERT INTO
    application_user (username, first_name, last_name, password)
VALUES
    -- Aga_admin / hard
    ('Aga_admin', 'Aga', 'Typ' ,'{bcrypt}$2a$10$Ruu5GtmSVkfLeuGfz/wHUuzflCcMbwJHSBo/.Wui0EM0KIM52Gs2S'),
    -- Aga_user / dog.8
    ('Aga_user', 'Aga', 'T',  '{MD5}{AlZCLSQMMNLBS5mEO0kSem9V3mxplC6cTjWy9Kj/Gxs=}d9007147eb3a5f727b2665d647d36e35');

INSERT INTO
    user_role (name, description)
VALUES
    ('ADMIN', 'Ma dostęp do wszystkiego'),
    ('USER', 'Dostęp tylko do odczytu');

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2);