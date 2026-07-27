# Sanguosuo — Tam Quốc Sách

![Sanguosuo banner](comic_0007.png)

Sanguosuo is a Sanguosha (Tam Quốc Sát) wiki application created for the CS426 Mobile Device Application Development seminar. The repository contains:

- an Android client written in Kotlin with Jetpack Compose;
- an Express API written in TypeScript;
- a MySQL database with local schema and seed scripts; and
- the seminar report written in LaTeX.

The database diagram is available in the [project spreadsheet](https://docs.google.com/spreadsheets/d/1S3qWSGoPBhlopxmaQ8Wa3inQca98Cn2W9TnmwES8mj0/edit?usp=sharing).

## Repository layout

```text
.
├── client/          Android application
├── server/          Express/TypeScript API
├── db/
│   ├── schema.sql   Local database schema
│   └── seed.sql     Development sample data
├── report/          LaTeX seminar report
├── compose.yml      Local API and MySQL services
└── .env.example     Environment-variable template
```

## Prerequisites

Install the following before starting:

- [Git](https://git-scm.com/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) or Docker Engine with the Compose plugin
- [Android Studio](https://developer.android.com/studio), including:
  - a JDK supported by Gradle 9.5 (JDK 17 or newer);
  - Android SDK Platform 37.1, used as this project's compile SDK;
  - Android SDK Platform 36, used as the target SDK; and
  - an Android Virtual Device running API 24 or newer, or a physical Android 7.0+ device.

You do not need to install Node.js or MySQL on the host when using Docker Compose. The API image uses Node.js 22 and Compose supplies MySQL.

## Local setup

### 1. Clone the repository

```bash
git clone <repository-url>
cd cs426-seminar
```

Run all Docker commands below from this repository root, where `compose.yml` is located.

### 2. Create the local environment file

Copy the template:

```bash
cp .env.example .env
```

Set the local variables in `.env`. The API currently also requires `LOCAL_DB_NAME` and `JWT_SECRET`; add them if they are absent from the template:

```dotenv
BACKEND_PORT=8080

LOCAL_DB_HOST=db
LOCAL_DB_USER=root
LOCAL_DB_PASSWORD=choose-a-local-database-password
LOCAL_DB_NAME=Sanguosuo
LOCAL_DB_PORT=3306

JWT_SECRET=replace-with-a-long-random-development-secret
```

Generate a suitable development JWT secret with a password manager or, on systems with OpenSSL:

```bash
openssl rand -hex 32
```

Notes:

- Keep `.env` private. It is ignored by Git and must not be committed.
- `LOCAL_DB_PASSWORD` is used both to initialize the MySQL root account and by the API.
- The Compose service name `db` is the database hostname inside Docker's network. The current API implementation resolves the database as `db` regardless of `LOCAL_DB_HOST`.
- The `PROD_DB_*` variables in `.env.example` are not needed for local development.

### 3. Start the API and database

Build the API image and start both services:

```bash
docker compose up --build -d
```

Check their status:

```bash
docker compose ps
```

Both `sanguosuo-server` and `sanguosuo-db` should be running. The API may restart briefly while MySQL performs its first-time initialization. Follow the logs if necessary:

```bash
docker compose logs -f server db
```

Press `Ctrl+C` to stop following logs; the detached containers continue running.

### 4. Create and seed the local database

The SQL files are mounted read-only at `/sql` in the database container. Open the MySQL client:

```bash
docker compose exec db mysql -u root -p
```

Enter the value of `LOCAL_DB_PASSWORD`, then execute:

```sql
SOURCE /sql/schema.sql;
SOURCE /sql/seed.sql;
SHOW DATABASES;
USE Sanguosuo;
SHOW TABLES;
EXIT;
```

Run the schema and seed scripts only once for a new MySQL volume. `schema.sql` creates the `Sanguosuo` database and is not idempotent, so rerunning it against an initialized database produces errors.

The contents of `db/seed.sql` are development/demo data and must not be used as production data.

### 5. Verify the backend

From the host, request the health endpoint:

```bash
curl http://localhost:8080/health
```

The JSON response should report success and include `"dbActive": true`. You can also open <http://localhost:8080/> to verify the API root endpoint.

If the API is unavailable or reports `dbActive: false`, inspect its logs:

```bash
docker compose logs server
docker compose logs db
```

Confirm that the database name, user, password, and port in `.env` match the values above. After changing `.env`, recreate the services:

```bash
docker compose up --build -d --force-recreate
```

### 6. Open and run the Android client

The recommended workflow is Android Studio:

1. Open the `client/` directory as the project.
2. Let Android Studio install any requested SDK components and finish the Gradle sync.
3. In **Tools > Device Manager**, create or start an Android Virtual Device using API 24 or newer.
4. Select the `app` run configuration and the running emulator.
5. Click **Run**.

The client currently uses this API base URL in `client/app/src/main/java/com/example/sanguosuoclient/di/AppContainer.kt`:

```kotlin
private val baseUrl = "http://10.0.2.2:8080/"
```

`10.0.2.2` is the Android Emulator alias for the development machine's loopback interface. Therefore, the default configuration works with the standard Android Studio emulator while the API is exposed on host port 8080.

#### Running on a physical Android device

A physical device cannot use `10.0.2.2` to reach the host. Connect the phone and development machine to the same network, find the development machine's LAN address, and temporarily change `baseUrl`, for example:

```kotlin
private val baseUrl = "http://192.168.1.50:8080/"
```

Ensure that the host firewall permits inbound TCP traffic on port 8080. Do not expose this development HTTP service to an untrusted or public network. The debug client currently permits cleartext HTTP traffic for local development.

#### Building from the command line

Android Studio normally creates `client/local.properties` automatically. For a command-line build, create it yourself if needed:

```properties
sdk.dir=/absolute/path/to/Android/Sdk
```

Use an escaped path on Windows when required by Java properties syntax, or let Android Studio generate the file. Then run:

```bash
cd client
./gradlew assembleDebug
```

On Windows Command Prompt or PowerShell, use:

```powershell
cd client
.\gradlew.bat assembleDebug
```

The debug APK is written under `client/app/build/outputs/apk/debug/`. With an emulator or USB-debugging-enabled device connected, install it with:

```bash
./gradlew installDebug
```

You can stop Gradle daemons when finished:

```bash
./gradlew --stop
```

## Daily development workflow

Start the backend and database:

```bash
docker compose up -d
```

The server source directory is mounted into the API container and `nodemon` runs the application, so server changes should trigger a restart automatically. View the API logs with:

```bash
docker compose logs -f server
```

Run the Android app from Android Studio after the API health check reports an active database.

Stop the local services without deleting data:

```bash
docker compose down
```

The MySQL data remains in the named `mysql_data` Docker volume for the next run.

## Resetting the local database

If the local schema or seed state must be recreated, stop Compose and delete its volumes:

```bash
docker compose down -v
docker compose up --build -d
```

**Warning:** `docker compose down -v` permanently deletes this project's local MySQL data and the Compose-managed `node_modules` volume. Use it only when a full local reset is intended. Afterward, repeat the schema and seed commands from step 4.

## Tests and checks

Run the Android unit tests from `client/`:

```bash
./gradlew test
```

Run connected Android instrumentation tests with an emulator or device running:

```bash
./gradlew connectedAndroidTest
```

The backend's current `npm test` script is a placeholder and intentionally exits with an error; automated backend tests have not yet been implemented.

## Building the report

The report requires a TeX distribution containing the packages imported by `report/main.tex`, plus Perl and `latexmk`. TeX Live is suitable on Linux/macOS; MiKTeX and Strawberry Perl can be used on Windows.

Build once from the repository root:

```bash
cd report
latexmk -pdf main.tex
```

Continuously rebuild while editing:

```bash
latexmk -pvc -pdf main.tex
```

Generated auxiliary files are placed in `report/.aux`, and the PDF is emitted as `report/main.pdf`.

## Troubleshooting

### Port 8080 or 3306 is already in use

Stop the conflicting local service, or change the host-side port in `compose.yml`. If the API host port changes, update the Android client's `baseUrl` to the same port. Changing only `BACKEND_PORT` also requires updating the container port mapping in `compose.yml`.

### The API container keeps restarting

Run `docker compose logs server db`. On a first launch, MySQL may still be initializing. Persistent failures usually mean `.env` is missing a required value or the database credentials do not match the credentials stored in the existing MySQL volume.

If `LOCAL_DB_PASSWORD` was changed after MySQL's first initialization, changing `.env` does not update the existing database account. Restore the original password or intentionally reset the local volume as described above.

### The Android app cannot connect

- Confirm that `curl http://localhost:8080/health` works on the host and reports `dbActive: true`.
- Use `10.0.2.2`, not `localhost`, from the standard Android emulator.
- For a physical device, use the host's LAN IP and allow port 8080 through the firewall.
- Confirm that the URL ends with `/`, as required by Retrofit.

### Gradle cannot find the Android SDK

Open `client/` once in Android Studio or create `client/local.properties` with the correct absolute `sdk.dir`. Also verify that the SDK platforms requested by the project are installed in Android Studio's SDK Manager.

## License

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
