# Sanguosuo - Tam Quốc Sách
![mionir](comic_0007.png)

Seminar for CS426 (Mobile Device Application Development) course, where we  build a Sanguosha (Tam Quốc Sát) board game wiki mobile app. The application is inherently vulnerable to attacks to also demonstrate Android security best practices (to be fair all apps are vulnerable to attacks, but this one is deliberate).

This project uses the following tech stack:
- Frontend: Kotlin with Jetpack Compose
- Backend: express server using TypeScript
- Database: MySQL
- Report: Latex

The database visual schema is given [here](https://docs.google.com/spreadsheets/d/1S3qWSGoPBhlopxmaQ8Wa3inQca98Cn2W9TnmwES8mj0/edit?usp=sharing).

# Installation
Lorem ipsum dolor sit amet

# Build
<code>docker compose up</code>. Fuck yeah easy as that, but this will create a persistent process in the current terminal. In order to use your terminal for other work (e.g., stopping compose, git, or whatever bullshit you are doing), you need to spawn a new terminal. If you don't like this, do <code>docker compose up -d</code>.

Within the <code>db/</code> folder are two SQL files&mdash;the schema definition and sample data. These files are used to seed your local database. **DO NOT USE THEM FOR PROD**.

To set up the database schema and sample data, compose up the container, then:
1. <code>docker compose exec db sh</code> - Enters database container terminal
2. <code>mysql -h 127.0.0.1 -u root -p</code> - Attempts to login the database
3. Enters the password. The real Minh Kỳ would know.
4. <code>SOURCE /sql/schema.sql</code> - builds schema
5. <code>SOURCE /sql/seed.sql</code> - seeds the database with sample data

The production database is cloud hosted on a certain website only the real Minh Kỳ would know. Ask him for connection host, username, and password.