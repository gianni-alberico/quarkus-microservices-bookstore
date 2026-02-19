# Quarkus Microservices Bookstore

Projet de formation orienté microservices illustrant la mise en œuvre d'une architecture distribuée, la compilation native (GraalVM) et l'orchestration via Docker Compose

## Architecture du Système

Le projet est un monorepo Maven composé de deux microservices communicants :

* **rest-number** : Générateur de numéros ISBN (Port interne : 8701)
* **rest-book** : Gestion de la logique métier des livres (Port interne : 8702)

### Flux de communication
Le service `rest-book` utilise un **MicroProfile Rest Client** (`NumberProxy`) pour appeler `rest-number`. Le réseau Docker `microservices` permet la résolution de nom DNS entre les conteneurs.

---

## Stack Technique

* **Java** : 21
* **Framework** : Quarkus 3.31.3
* **Build** : Maven (Multi-module)
* **Profiles** : Gestion dynamique via `quarkus.profile`
* **Packaging** : Native Executables (GraalVM)

---

## Guide de Démarrage

### 1. Mode Développement (Local)
Pour lancer les services avec le rechargement à chaud (Hot Reload) :

```bash
# Terminal 1 (Service Number)
mvn -f rest-number/pom.xml quarkus:dev

# Terminal 2 (Service Book)
mvn -f rest-book/pom.xml quarkus:dev
```
*En mode dev, `rest-book` pointe sur `http://localhost:8701`.*

### 2. Build et Déploiement Natif (Docker)
Nous utilisons le profil Maven `native` pour générer des binaires optimisés

**Étape 1 : Compilation Native**
```bash
mvn clean package -Pnative
```
*Note : Le profil Maven `native` définit le profil quarkus sur `%native`*

**Étape 2 : Lancement de l'infrastructure**
```bash
docker-compose up --build
```

## Exemples de requêtes

### Mode Développement (Direct)
Pour récupérer un ISBN généré
```sh
curl http://localhost:8701/api/v1/numbers
```
Pour récupérer un livre généré (appel fait au microservice `rest-number`)
```sh
curl -X POST http://localhost:8702/api/v1/books -d "title=Quarkus&author=Gianni&year=2026&genre=IT"
```
### Mode Docker (Via ports exposés)
```sh
curl http://localhost:8000/api/v1/numbers
```
```sh
curl -X POST http://localhost:8001/api/v1/books -d "title=Quarkus&author=Gianni&year=2026&genre=IT"
```
---
*Projet réalisé par Gianni ALBERICO*