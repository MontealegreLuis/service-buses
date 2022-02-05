# Service buses

[![CI workflow](https://github.com/montealegreluis/service-buses/actions/workflows/ci.yml/badge.svg)](https://github.com/montealegreluis/service-buses/actions/workflows/ci.yml)
[![Release workflow](https://github.com/montealegreluis/service-buses/actions/workflows/release.yml/badge.svg)](https://github.com/montealegreluis/services-buses/actions/workflows/release.yml)
[![semantic-release: conventional-commits](https://img.shields.io/badge/semantic--release-conventionalcommits-e10079?logo=semantic-release)](https://github.com/semantic-release/semantic-release)


This Maven package provides buses for commands and queries as defined by CQRS (Command-Query Responsibility Segregation)

In CQRS, services are split into two separate types, a _read-side_ (**Query**) and a _write-side_ (**Command**). 

- _Commands_ change information. 
- _Queries_ return information.

## Usage

- [Command bus](https://github.com/MontealegreLuis/service-buses/blob/main/docs/command-bus/index.md)
- [Query bus](https://github.com/MontealegreLuis/service-buses/blob/main/docs/query-bus/index.md)

## Installation

1. [Authenticating to GitHub Packages](https://github.com/MontealegreLuis/service-buses/blob/main/docs/installation/authentication.md)
2. [Maven](https://github.com/MontealegreLuis/service-buses/blob/main/docs/installation/maven.md)
3. [Gradle](https://github.com/MontealegreLuis/service-buses/blob/main/docs/installation/gradle.md)

## Contribute

Please refer to [CONTRIBUTING](https://github.com/MontealegreLuis/service-buses/blob/main/CONTRIBUTING.md) for information on how to contribute to Service Buses.

## License

Released under the [BSD-3-Clause](https://github.com/MontealegreLuis/service-buses/blob/main/LICENSE).
