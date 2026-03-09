# Whydah-TypeLib

## Purpose
Core type library containing the key data structures used throughout the Whydah IAM system. Defines the canonical representations of users, applications, roles, organizations, and tokens that are serialized and transmitted between Whydah components.

## Tech Stack
- Language: Java 8+
- Framework: None (pure library)
- Build: Maven
- Key dependencies: SLF4J

## Architecture
Foundational library with zero runtime dependencies beyond logging. Defines POJOs, XML/JSON serialization, and XPath helpers for all Whydah data structures. Every other Whydah component depends on this library for consistent data handling.

## Key Entry Points
- Whydah data structure classes (User, Application, Role, UserToken, ApplicationToken)
- `pom.xml` - Maven coordinates: `net.whydah.sso:Whydah-TypeLib`
- Documentation: https://wiki.cantara.no/display/whydah/Key+Whydah+Data+Structures

## Development
```bash
# Build
mvn clean install

# Test
mvn test
```

## Domain Context
Whydah IAM data model. The single source of truth for all data structures in the Whydah ecosystem. Changes here affect every Whydah component, so modifications must be made carefully and versioned appropriately.
