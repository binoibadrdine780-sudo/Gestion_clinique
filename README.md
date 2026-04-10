# Clinical Consultations - Spring Boot

Simple student project to manage:
- Patients
- Doctors
- Consultations

## Technologies
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)

## Run the project
```bash
mvn spring-boot:run
```

H2 console:
- http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:clinicdb`
- User: `sa`
- Password: (empty)

## REST Endpoints

### Patients
- `GET /api/patients`
- `GET /api/patients/{id}`
- `POST /api/patients`
- `PUT /api/patients/{id}`
- `DELETE /api/patients/{id}`

### Doctors
- `GET /api/doctors`
- `GET /api/doctors/{id}`
- `POST /api/doctors`
- `PUT /api/doctors/{id}`
- `DELETE /api/doctors/{id}`

### Consultations
- `GET /api/consultations`
- `GET /api/consultations/{id}`
- `POST /api/consultations`
- `PUT /api/consultations/{id}`
- `DELETE /api/consultations/{id}`

For consultation POST/PUT, send patient and doctor IDs like this:

```json
{
  "date": "2026-04-10T09:30:00.000+00:00",
  "patient": { "id": 1 },
  "doctor": { "id": 1 }
}
```
