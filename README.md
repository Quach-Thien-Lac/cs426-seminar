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
## Backend
Fuck Docker Desktop. Use CLI instead.
### First time build:
1. <code>cd server</code> - Enters server directory (duh)
2. <code>docker build -t sanguosuo-server .</code> - Builds Docker image
3. <code>docker run -p 8080:8080 --name sanguosuo sanguosuo-server</code> - Builds Docker container from image and runs it

This will create a persistent process in the current terminal. In order to use your terminal for other work (e.g., stopping the container, git, or whatever bullshit you are doing), you need to spawn a new terminal.

### Future runs:
1. <code>docker start sanguosuo</code> - Runs the container

### Stopping the container:
1. <code>docker stop sanguosuo</code> - Stops the container

## Frontend
i will figure this out later

## Database
Within the <code>db/</code> folder are two SQL files&mdash;the schema definition and sample data. These files are used to seed your local database. **DO NOT USE THEM FOR PROD**.

The local database is run using the MySQL container installed from the official website. On first run, configure them as you wish, and on the MySQL CLI:
1. <code>SOURCE \<absolute_path_to_db/schema.sql\></code> - builds schema
2. <code>SOURCE \<absolute_path_to_db/seed.sql\></code> - seeds the database with sample data

The production database is cloud hosted on a certain website only the real Minh Kỳ would know. Ask him for connection host, username, and password.

## Report
i will also figure this out later