# Mikroservis za upravljanje kolekcijom knjiga  
## Sprint 1 – Autentikacija i autorizacija (RBAC)

Ovaj projekt predstavlja rješenje prvog sprinta u kojem je fokus bio na implementaciji autentikacije i autorizacije u Spring Boot aplikaciji, uz testiranje putem Swagger sučelja.

---

## Cilj sprinta

Cilj prvog sprinta bio je:
- implementirati autentikacijski mehanizam u Javi
- definirati dvije korisničke uloge (administrator i obični korisnik)
- primijeniti role-based access control (RBAC) nad rutama
- jasno demonstrirati ograničenja pristupa putem Swagger UI-ja

---

## Korištene tehnologije

- Java 17
- Spring Boot
- Spring Security
- Swagger / OpenAPI
- Maven

---

## Korisničke uloge (in-memory)

U ovom sprintu **nije korištena baza podataka**.  
Korisnici su definirani *in-memory* pomoću Spring Security mehanizma, što je dovoljno za demonstraciju RBAC-a.

| Uloga | Korisničko ime | Lozinka |
|-----|---------------|--------|
| USER | user | user123 |
| ADMIN | admin | admin123 |

---

## Dostupne rute

- `GET /user` – dostupno korisnicima s ulogom **USER** i **ADMIN**
- `GET /admin` – dostupno isključivo korisnicima s ulogom **ADMIN**

---

## RBAC pravila (dokaz funkcionalnosti)

- Prijava kao **USER**:
  - `/user` → 200 OK
  - `/admin` → 403 Forbidden
- Prijava kao **ADMIN**:
  - `/admin` → 200 OK

Ovo jasno demonstrira ispravnu primjenu role-based access control mehanizma.

---

## Testiranje putem Swaggera

Swagger UI se koristi za testiranje REST endpointa.

URL: http://localhost:8080/swagger-ui/index.html
