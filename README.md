# BFHL API — Java Spring Boot

Acropolis Campus Hiring API round. **POST** `/bfhl` only.

**Project location:** `D:\cursor\bajaj-finserv-health-python\bajaj_sol`

## Your profile (already configured)

| Field | Value |
|-------|--------|
| user_id | `sayansh_pal_30102005` |
| email | `sayanshpal@gmail.com` |
| roll_number | `0827CI231121` |

## Stack

- Java 17, Spring Boot 3.4
- DTOs: `BfhlRequest`, `BfhlResponse`
- Service: `IBfhlService` → `BfhlServiceImpl`
- Tests: `BfhlServiceImplTest`, `BfhlControllerTest`

## Run locally

Needs [Java 17](https://adoptium.net/) and [Maven](https://maven.apache.org/download.cgi):

```powershell
cd D:\cursor\bajaj-finserv-health-python\bajaj_sol
mvn spring-boot:run
```

Test:

```powershell
curl.exe -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\":[\"a\",\"1\",\"334\",\"4\",\"R\",\"$\"]}"
```

```powershell
mvn test
```

## Deploy (GitHub + Render)

See **[DEPLOY.md](DEPLOY.md)** for step-by-step instructions.

Submit your live URL: `https://YOUR-SERVICE.onrender.com/bfhl`
