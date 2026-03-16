# SportsMania

> Android sports news application built with Java and Android SDK.

## Architecture

**Stack**: Android (Java) + Gradle + Android SDK

```
app/src/main/java/com/nurul/sportmania/
  MainActivity.java          → Single-activity architecture
  Fragments/                 → UI fragments
    HomeFragment.java        → Home feed
    SearchFragment.java      → Search functionality
    CategoryFragment.java   → Category browser
    FavoriteFragment.java   → Saved favorites
    ProfileFragment.java   → User profile
    LoginFragment.java     → Authentication
    AboutFragment.java     → App info
    FragmentInterface.java → Fragment contract interface
  Models/                    → Data models
    News.java              → News article model
    Category.java          → Category model
    Details.java           → Detail model
    Login.java             → Auth model
    Emojies.java           → Emoji data
  Utils/                     → Utility classes
    NotificationUtils.java → Push notification helpers
```

### Navigation
Single-activity with fragment-based navigation. `FragmentInterface` defines the contract for fragment communication.

## Developer Workflows

```bash
./gradlew assembleDebug     # Build debug APK
./gradlew assembleRelease   # Build release APK
./gradlew test              # Run unit tests
```

Or use Android Studio: **Build > Make Project** / **Run > Run 'app'**

## Conventions

- **Architecture**: Single Activity + multiple Fragments
- **Fragment communication**: Via `FragmentInterface` — all fragments implement this contract
- **Models**: Plain Java objects (POJOs) in `Models/` package
- **Notifications**: Handled via `NotificationUtils` in `Utils/`
- **Layout**: XML layouts in `app/src/main/res/layout/`
- **Naming**: Java conventions — PascalCase classes, camelCase methods

## Gotchas

1. **Java, not Kotlin** — this project uses Java throughout
2. **Single Activity pattern** — don't create new Activities, use Fragments
3. **Gradle sync** required after modifying `build.gradle` files
4. **Min SDK**: Check `app/build.gradle` for minimum Android version support
