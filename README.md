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
APK soon.

# Build
The frontend and the backend.
## Backend & Database
<code>docker compose up</code>. Fuck yeah easy as that, but this will create a persistent process in the current terminal. In order to use your terminal for other work (e.g., stopping compose, git, or whatever bullshit you are doing), you need to spawn a new terminal. If you don't like this, do <code>docker compose up -d</code>.

Within the <code>db/</code> folder are two SQL files&mdash;the schema definition and sample data. These files are used to seed your local database. **DO NOT USE THEM FOR PROD**.

To set up the database schema and sample data, compose up the container, then:
1. <code>docker compose exec db sh</code> - Enters database container terminal
2. <code>mysql -h 127.0.0.1 -u root -p</code> - Attempts to login the database
3. Enters the password. The real Minh Kỳ would know.
4. <code>SOURCE /sql/schema.sql</code> - builds schema
5. <code>SOURCE /sql/seed.sql</code> - seeds the database with sample data

The production database is cloud hosted on a certain website only the real Minh Kỳ would know. Ask him for connection host, username, and password.

## Frontend
If you are using Android Studio, it should handle the Gradle build automatically as well as emulators&mdash;in that case you can skip this section. If you wish to build the client on VSCode for whatever reason, you will need **a Gradle build of the app** and **an emulator**.

### Client build
You first need to locate where Android SDKs are installed. I haven't tested on Windows, but on Linux, it's usually <code>~/Android/Sdk</code>.

On first time build:
1. <code>cd client</code> - you know what this does
2. If there isn't already, create a file called <code>local.properties</code> with a single line <code>sdk.dir=/home/fungusdesu/Android/Sdk</code>. The value depends whether you are using Windows, Mac, or Linux (the value I mentioned assumes Linux)
3. <code>./gradlew</code> - boots the gradle daemon and installs whatever magic fuckery to make the client works
4. <code>./gradlew assembleDebug</code> - builds the app

On subsequent builds, just do step 4 and <code>./gradlew installDebug</code>. I will explain what this does shortly.

**REMEMBER TO STOP THE GRADLE DAEMON WHEN YOU ARE DONE**. Do <code>./gradlew --stop</code>.

### Emulator
If you don't have an Android phone, you can run an emulator. Assuming you installed Android Studio with emulators installed (don't use flatpak studio because god fucking forbids i know where the emulators are located and i had to spend FOUR FUCKING HOURS trying to troubleshoot why the emulator woudlnt' appear on vscode), install the [Android iOS Emulator](https://marketplace.visualstudio.com/items?itemName=DiemasMichiels.emulate) extension, add the location of the qemu emulator executable (as per what the extension guides), <code>Ctrl+Shift+P</code> -> Emulator -> View Android emulators -> choose whatever device and voilà you have an emulator running.

To install the built application into the emulator, do <code>./gradlew installDebug</code>.

# Report
You know I could Dockerize the report now, but god damn is it easier for everyone to just let Latex Workshop autocompile rot and actually learn how to use latexmk. You need to install [MikTeX](https://miktex.org/download) and [Perl](https://strawberryperl.com/) first, install the package <code>latexmk</code> within MikTeX, and you are good to go.

Within the report folder already has a <code>.latexmkrc</code>, which is a latexmk config file. To compile, just <code>cd report</code> and do <code>latexmk -pvc -pdf main.tex</code>. Yes, the output is PDF; yes, it will update itself when you edit the .tex file. Fuck slow ahh mf Latex Workshop and whatever bloats it wraps <code>latexmk</code> with.

# License
THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.