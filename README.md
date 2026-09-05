# Sanguosuo — Tam Quốc Sách

**Sanguosuo** (Tam Quốc Sách) is an Android wiki application for [Sanguosha](https://en.wikipedia.org/wiki/Legends_of_the_Three_Kingdoms) (Tam Quốc Sát), a strategy card and board game set in the Three Kingdoms era of Chinese history. The application gives players a convenient reference on their Android devices, eliminating the need to search across scattered wikis or printed rulebooks during play. Users can look up heroes by name or identifier, view their faction, health points, epithet, skills, and skill tags, and browse hero–hero synergy combinations.

This project was developed as a seminar submission for **CS426 — Mobile Device Application Development** at the University of Science, VNU-HCM. Security considerations—including SQL injection prevention, password hashing, and denial-of-service awareness—were incorporated as a deliberate learning objective.

---

## Tech Stack

| Layer          | Technology                          |
|----------------|-------------------------------------|
| Android Client | Kotlin + Jetpack Compose            |
| Backend API    | Node.js + Express 5 (TypeScript)    |
| Database       | MySQL 8                             |
| Infrastructure | Docker Compose                      |
| Report         | LaTeX / latexmk                     |

The database visual schema is available [here](https://docs.google.com/spreadsheets/d/1S3qWSGoPBhlopxmaQ8Wa3inQca98Cn2W9TnmwES8mj0/edit?usp=sharing).

---

## Architecture Overview

Sanguosuo follows a three-tier client–server–database architecture. The Android client communicates with a REST API backend over HTTP; the backend reads and writes to a relational MySQL database. During local development, Docker Compose orchestrates the backend and database as isolated containers.

### API Endpoints

| Method | Path                          | Description                              |
|--------|-------------------------------|------------------------------------------|
| `POST` | `/api/auth/register`          | Create a new user account                |
| `POST` | `/api/auth/login`             | Authenticate and receive a session token |
| `GET`  | `/api/heroes/id/:heroId`      | Retrieve a hero by unique identifier     |
| `GET`  | `/api/heroes/name/:heroName`  | Retrieve a hero by name (partial match)  |
| `GET`  | `/health`                     | Health check (server and DB status)      |

---

## Prerequisites

Before setting up the project, ensure the following tools are installed:

- **Docker** and **Docker Compose** — for the backend and database
- **Android Studio** (recommended) or a standalone Android SDK installation
- **Git** — to clone the repository
- *(Optional)* **MikTeX** or **TeX Live** with `latexmk` and **Perl** — to compile the report

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Quach-Thien-Lac/cs426-seminar.git
cd cs426-seminar
```

### 2. Configure Environment Variables

Copy the example environment file and fill in the required values:

```bash
cp .env.example .env
```

| Variable            | Description                                                  |
|---------------------|--------------------------------------------------------------|
| `LOCAL_DB_PASSWORD` | Root password for the local MySQL container                  |
| `DB_HOST`           | Database hostname (`db` when running inside Docker Compose)  |
| `DB_PORT`           | MySQL port (default: `3306`)                                 |
| `DB_NAME`           | Database name (use `Sanguosuo`)                              |
| `DB_USER`           | MySQL username                                               |
| `DB_PASSWORD`       | MySQL password for the application user                      |
| `SERVER_PORT`       | Port the Express server listens on (default: `8080`)         |
| `JWT_SECRET`        | Secret used to sign JWT tokens                               |

---

## Backend & Database

### Starting the Containers

From the repository root:

```bash
docker compose up
```

This builds the Express server image from `server/Dockerfile` and pulls the `mysql:oraclelinux9` image. The API will be available at `http://localhost:8080`. To run in detached mode:

```bash
docker compose up -d
```

### Seeding the Database

The `db/` directory contains two SQL files: `schema.sql` (table definitions) and `seed.sql` (148 heroes, 268 skills, and related reference data). After the containers are running:

1. Enter the database container shell:
   ```bash
   docker compose exec db sh
   ```
2. Connect to MySQL:
   ```bash
   mysql -h 127.0.0.1 -u root -p
   ```
   Enter the value set for `LOCAL_DB_PASSWORD` when prompted.
3. Apply the schema:
   ```sql
   SOURCE /sql/schema.sql;
   ```
4. Seed sample data:
   ```sql
   SOURCE /sql/seed.sql;
   ```
5. Exit MySQL and the container shell:
   ```bash
   exit
   exit
   ```

> **Note:** The SQL files in `db/` are intended for local development only. Do not use them against a production database.

### Verifying the Backend

```bash
curl http://localhost:8080/health
```

A successful response returns `200 OK` with a JSON body indicating the server and database status.

### Stopping the Environment

```bash
docker compose down
```

To also remove the persistent database volume (destructive — deletes all seeded data):

```bash
docker compose down -v
```

---

## Android Client

### Using Android Studio (Recommended)

1. Open Android Studio and select **Open an existing project**. Navigate to the `client/` folder and open it.
2. Android Studio will detect the Gradle build file and sync dependencies automatically. Accept any SDK or Gradle plugin update prompts.
3. Create or start an Android Virtual Device (AVD) from the **Device Manager** panel. A device with API level 24 or higher is required (`minSdk = 24`).
4. Press **Run** (`Shift+F10`) to build and install the application on the emulator.

When testing with the emulator, the application connects to the backend at `http://10.0.2.2:8080`. The address `10.0.2.2` is the Android emulator's alias for the host machine's `localhost`.

### Building from the Command Line

1. Locate your Android SDK directory. On Linux it is typically `~/Android/Sdk`.
2. Inside `client/`, create `local.properties` with the following line (adjust the path for your system):
   ```
   sdk.dir=/home/<your-username>/Android/Sdk
   ```
3. Initialize Gradle (first time only):
   ```bash
   cd client
   ./gradlew
   ```
4. Build a debug APK:
   ```bash
   ./gradlew assembleDebug
   ```
   The APK is written to `client/app/build/outputs/apk/debug/`.
5. Install on a connected device or running emulator:
   ```bash
   ./gradlew installDebug
   ```
6. When finished, stop the Gradle daemon to free memory:
   ```bash
   ./gradlew --stop
   ```

---

## Report

The project report is typeset with LaTeX and compiled via `latexmk`.

1. Install **MikTeX** (Windows/macOS/Linux) or **TeX Live** (Linux/macOS) along with **Perl**.
2. Within MikTeX Console, install the `latexmk` package.
3. From the `report/` directory:
   ```bash
   latexmk -pvc -pdf main.tex
   ```
   The `-pvc` flag enables continuous preview mode: `latexmk` recompiles the PDF whenever a `.tex` file changes. The compiled PDF is written to `report/main.pdf`.

---

## Authors

- Nguyễn Minh Quân
- Quách Thiên Lạc
- Nguyễn Văn Tĩnh
- Phạm Tiến Đạt
- Trần Tôn Minh Kỳ

*University of Science, VNU-HCM — CS426 Mobile Device Application Development, September 2026*

---

## License

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.