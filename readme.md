run `docker compose up` to start up db
run `gradlew bootRun` to run an app

To register a new supply call next route: `http://localhost:8080/supplies`

With a following body (as an example):

```json
{
"supplierName": "MacDac",
"prices":
    [{
    "startDate": "2020-10-01",
    "endDate": "2020-11-01",
    "price": 150,
    "weight": 48,
    "productName": "Red aple"
    }]
}
```

To request a report call next route: `http://localhost:8080/supplies/report`

With a following body (as an example):

```json
{
    "startDate": "2020-01-01",
    "endDate": "2022-12-01"
}
```
