# KREX

<p align="center">
<img alt="Logo" src="images/logo.png" width="100%">

<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3.10-A831F5">
<img alt="Static Badge" src="https://img.shields.io/badge/API-24+-39ad31">
</p>

> 🌐 **Languages:**  
> [English](README-EN.md) | [Русский](../README.md)

## 🔎 What is KREX?

KREX (Kotlin Reactive EXtensions) is a Kotlin Multiplatform library
that extends the standard features of Coroutines and Kotlin Flow.

## 🌍 Supported platforms

Coming Soon...

## 🚀 Installation

**settings.gradle.kts**

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven ("https://jitpack.io")
    }
}
```

**build.gradle.kts**

```
dependencies {
      implementation("com.github.RavenZIP.krex:krex-core:$version") 
}
```

If you are using libs.versions.toml

**libs.versions.toml**

```
[versions]
ravenzip-krex = "$version"

[libraries]
ravenzip-krex-core = { module = "com.github.RavenZIP.krex:krex-core", version.ref = "ravenzip-krex" }
```

**build.gradle.kts**

```
dependencies {
      implementation(libs.ravenzip.krex.core)
}
```

## 🚬 Using

Coming Soon...

## 📜 License

This library is licensed under the Apache 2.0 License. See the [LICENSE](../LICENSE) file for details.

## 👾 Developer

**Alexander Chernykh**

- [Telegram](https://t.me/RavenZIP)