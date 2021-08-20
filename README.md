<h1 align="center">Bloomware</h1>
<div align="center">
  <img src="https://img.shields.io/github/contributors/TheFishDevs/Bloomware" alt="GitHub contributors"/> <img src="https://img.shields.io/github/languages/code-size/TheFishDevs/Bloomware" alt="GitHub code size in bytes"/> <img src="https://tokei.rs/b1/github/TheFishDevs/Bloomware" alt="GitHub lines of code"/> <img src="https://img.shields.io/github/last-commit/TheFishDevs/Bloomware" alt="GitHub last commit"/> <a href="https://www.codefactor.io/repository/github/thefishdevs/bloomware"><img src="https://www.codefactor.io/repository/github/thefishdevs/bloomware/badge" alt="CodeFactor" /></a>
</div>

# 🛠️ Bloomware
Bloomware is a free minecraft anarchy utility mod for 1.16+

We have a discord server - [Discord](https://discord.gg/D4G7JN5d7m)

⚠️ Currently is in development, fixing bugs and other issues and adding new features. ⚠️

## How to install
1) Download latest mod .jar from repo's releases.
2) Drag it into `.minecraft/mods` directory.
3) Install [Fabric](https://fabricmc.net/).
4) Install [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api/files?sort=-name&__cf_chl_jschl_tk__=pmd_247af5374ad38c84fef2e144d9361c1f357f929b-1628948630-0-gqNtZGzNAk2jcnBszQdi) and drag it into `.minecraft/mods` directory.

## Prepare dev environment
This is required only for contributors, not for normal users.
### Using gradle tools
1) Clone the repo using `git clone`
2) Open command line (terminal if you're on linux);
3) Type `gradlew genSources` to command line;
4) To generate project for IntelliJ IDEA, run `gradlew idea`
5) To generate project for Eclipse, run `gradlew eclipse`
### Using GUI (IntelliJ IDEA only)
1) Clone the repo using `git clone`
2) Import Project -> Bloomware directory
3) Select the build.gradle in mod's directory file, and import the project
<br>Note: If gradle did it's tasks and project's configuration files aren't showing up, try to restart IDEA.
<br>Note: If the project's configuration files still don't show up, try reimporting the Gradle project.

## Developers
- **OffeeX** (*rockeZZergon*)
- **DiOnFire** (*dion*)

## Contributors
- **yoursleep** (Code cleanup, switching event system to google event system, helping with GUI and renderers)
- **cattyn** (Code cleanup, normal settings)
- **Rikonardo** (FontRenderer, moral support, tips, etc.)
- **srgantmoomoo** (client base "bedroom")
- **ronmamo** (java lib - Reflections)
- **johnrengelman** (java lib - ShadowJar)
- **Fabric Contributors** (for fabric)

## FAQ
- Default keybind for ClickGUI is Rshift
- Default prefix for commands is "$"
- Recommended version for Bloomware is 1.16.5 (FABRIC ONLY)

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
