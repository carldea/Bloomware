# 🛠️ Bloomware
Bloomware is a free minecraft anarchy utility mod for 1.16+

We have a discord server - [Discord](https://discord.gg/D4G7JN5d7m)

⚠️ Currently is in development, fixing bugs and other issues and adding new features. ⚠️

## How to install
1) Download latest mod .jar from repo's releases
2) Drag it into `.minecraft/mods` directory.
<br>Windows: C:\Users\<username>\AppData\Roaming\
<br>Linux: //home//<username>//

## Prepare dev environment
This is required only for contributors, not for normal users.
### Using gradle tools
1) Clone the repo using "git clone"
2) Open command line (terminal if you're on linux);
3) Type "gradlew genSources" to command line;
4) To generate project for IntelliJ IDEA, type "gradlew idea"
5) To generate project for Eclipse, type "gradlew eclipse"
### Using GUI (IntelliJ IDEA only)
1) Clone the repo using "git clone"
2) Import Project -> Bloomware direcory
3) Select the build.gradle in mod's directory file, and import the project
<br>Note: If gradle did it's tasks and project's configuration files aren't showing up, try to restart IDEA.
<br>Note: If the project's configuration files still don't show up, try reimporting the Gradle project.

## Developers
- **OffeeX** (*rockeZZergon*)
- **DiOnFire** (*dion*)

## Contributors
- **yoursleep** (Code cleanup, switching event system to google event system, helping with GUI and renderers)
- **cattyn** (Code cleanup)
- **Rikonardo** (FontRenderer, moral support, tips, etc.)
- **srgantmoomoo** (client base "bedroom")
- **ronmamo** (java lib - Reflections)
- **Fabric Contributors** (for fabric)

## To Do
- [X] ClickGUI
- [X] HudEditor
- [X] Commands
- [X] Alt Manager
- [ ] Capes
- [ ] Animations
- [X] Friends
- [X] JSON configs
- [X] Settings
  - [ ] using Commands
  - [X] using ClickGUI
