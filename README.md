# ![Prosper-Engineering-Team](resources/Banner.png)
# Prosper Engineering Team Robot Template

[![Build Status](https://github.com/FRC5411/template-base/actions/workflows/Build-Push.yml/badge.svg?branch=Production)](https://github.com/FRC5411/template-base/actions/workflows/Build-Push.yml)
[![GitHub Contributors](https://img.shields.io/github/contributors/FRC5411/template-base.svg?branch=Production)](https://github.com/FRC5411/template-base/graphs/contributors)
[![GitHub Issues](https://img.shields.io/github/issues/FRC5411/template-base.svg?branch=Production)](https://github.com/FRC5411/template-base/graphs/issues)

## Installation & Setup

Below is a list of instructions to properly *build* the project, see [requirements](##Requirements)

1. Clone the repository with `git clone https://github.com/FRC5411/template-base.git`
2. Build the repository with `./gradlew build` or `./gradlew build` if you do not have a local gradle installation

## Requirements

- [JDK 17+](https://adoptium.net/temurin/releases/?version=16)
    - Windows: install the JDK 16 .msi from the link above
    - macOS: install the JDK 16 .pkg from the link above
    - Linux:
        - Arch-Based: run `sudo pacman -Syu jdk-openjdk`
        - Debian-Based: run `sudo apt install default-jre`
        - RHEL-Based: run `sudo dnf install java-latest-openjdk-devel.x86_64`
- [Gradle 7+ (Optional)](https://gradle.org/releases/)
    - Follow Gradle's installation [guide](https://gradle.org/install/#prerequisites)
- [WPI VSCode (Optional)](https://github.com/wpilibsuite/allwpilib/releases/tag/v2023.4.3)
    - Follow WPILib's [guide](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html)
- [AdvantageScope (Optional)](https://github.com/Mechanical-Advantage/AdvantageScope/releases)
    - Install the latest Release for your OS and Architecture
    - Execute the distribution to start the Install Wizard
      
## Structure & Organization

The project is organized based on WPILib's command-based control structure modified to fit our own needs and can be found [here](https://docs.google.com/document/d/1IrDxQFM6M3gsqsZLIETJGJXaWM8zJ7IgAcHsC6BfKdk/edit?usp=sharing).
