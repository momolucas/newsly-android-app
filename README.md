# NewsApp 📰

NewsApp is an Android application designed to deliver news headlines from two flavors: **The Washington Post** and **BBC News**. The app uses biometric authentication for login; if biometric hardware is not available or not configured, the user is taken directly to the headlines list.

The project follows Clean Architecture principles and is modularized for better organization and scalability.

## 📱 Android Configuration

The project is configured with the following Android and Kotlin settings:

- `compileSdk`: **35**
- `minSdk`: **28**
- `targetSdk`: **35**
- `jvmTarget`: **17**

## 📂 Project Structure

The project is divided into the following modules:

- **app**: Main module containing the presentation layer, implemented with Jetpack Compose.
- **domain**: Pure Kotlin library containing business logic, use cases, and domain models.
- **data**: Android library responsible for data sources, repositories, and implementation details.

Inside each module, the typical directory structure includes:

- **commons**: Shared utilities and helpers used across the module.
- **di**: Dependency injection setup.
- **mappers**: Classes responsible for mapping data between layers or models.
- **models**: Data classes and domain models.

Additionally, depending on the module's responsibility, it contains:

- **ui**: UI components and Composables (mostly in the `app` module).
- **viewmodel**: ViewModel classes handling UI-related logic (mostly in the `app` module).
- **repositories**: Interfaces and implementations for data access (mostly in the `data` module).
- **usecases**: Business logic operations and domain use cases (mostly in the `domain` module).
- **providers**: Classes providing external dependencies or services (mostly in the `data` module).

## ⚙️ CI Pipeline Overview

This project uses **GitHub Actions** to automate quality checks and build tasks. The pipeline consists of two main jobs:

### 🧪 `code_quality`

This job ensures the codebase adheres to quality standards before any build occurs. It performs static analysis, code style checks, and unit tests.

**Steps:**

1. 📥 Checkout the repository
2. 🔧 Set up JDK 17
3. 🔓 Give execute permission to `gradlew`
4. 🔍️ Run **Lint**
5. 🔍️ Run **Detekt**
6. 🔍️ Run **Ktlint**
7. 🔬 Run **Unit Tests**

---

### 🚧 `build`

This job depends on `code_quality` and only runs if all quality checks pass. It builds the app and generates the APKs.

**Steps:**

1. 📥 Checkout the repository
2. 🔧 Set up JDK 17
3. 🔓 Give execute permission to `gradlew`
4. 📦 Set up Gradle with caching
5. 🧹 Clean the project
6. 🏗️ Build the project and assemble APKs


## 🚀 GitFlow and Commit Standards

This project follows an **atomic commit** pattern using **Gitmoji** to identify the purpose of each commit:

| Emoji            | Meaning                        |
|------------------|--------------------------------|
| ✨ `:sparkles:`    | Implementation of new features |
| 🐛 `:bug:`        | Bug fixes                      |
| 🚀 `:rocket:`     | New version releases           |
| 🔧 `:wrench:`     | Project configurations and Adjustments to external tools (e.g., CI/CD)    |
| 🧪 `:test_tube:`  | Creation or editing of tests   |
| 🎉 `:tada:`       | Project initialization         |

## 🛠️ Technologies Used

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Architecture**: Clean Architecture
- **State Management**: Flow + ViewModel
- **Networking**: Ktor
- **Image Loading**: Coil
- **Build System**: Gradle Kotlin DSL with Version Catalog
- **Testing**: Mockk, JUnit, Turbine
- **Code Quality**: Ktlint, Lint, Detekt

## 🔐 Biometric Authentication

The app uses biometric authentication for login to improve security and user experience. If biometric hardware is unavailable or the user hasn't configured it, the app falls back to directly showing the news headlines list.


## 📄 License

This project is licensed under the [Apache-2.0 License](LICENSE).
