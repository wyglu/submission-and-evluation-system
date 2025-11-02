-- Insert sample users
INSERT INTO users (id, email, password, role, first_name, last_name, created_at) VALUES
(1, 'etudiant1@univ.ac.rw', 'password123', 'STUDENT', 'Jean', 'Student', CURRENT_TIMESTAMP),
(2, 'etudiant2@univ.ac.rw', 'password123', 'STUDENT', 'Marie', 'Etudiante', CURRENT_TIMESTAMP),
(3, 'professeur@univ.ac.rw', 'password123', 'TEACHER', 'Dr. Ignace', 'Hakizimana', CURRENT_TIMESTAMP);

-- Insert sample deadlines
INSERT INTO deadlines (id, title, description, deadline_date, academic_year, created_by, created_at) VALUES
(1, 'Soumission des projets finaux', 'Date limite pour soumettre les projets finaux', '2025-10-25 20:30:00', '2024-2025', 3, CURRENT_TIMESTAMP);