-- liquibase formatted sql

-- changeset s.danilcovich:1
CREATE INDEX student_name_idx1 ON student (name);

-- changeset s.danilcovich:2
CREATE INDEX faculty_name_color_idx ON faculty (name, color);