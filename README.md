# League-backend-challenge

A simple Spring Boot service that performs various matrix operations from a provided CSV file input.

## 🚀 Features

This application exposes multiple endpoints to perform the following operations on an `n x n` matrix provided via a CSV file:

1. **Echo** - Returns the matrix as it is.
2. **Invert** - Returns the transposed version of the matrix.
3. **Flatten** - Flattens the matrix into a single line of comma-separated values.
4. **Sum** - Computes the sum of all integers in the matrix.
5. **Multiply** - Computes the product of all integers in the matrix.

---

## 🛠 Technologies Used

- Java 21
- Spring Boot
- Maven
- Docker
- Render.com (for deployment)
- Postman (for API testing)

---

## 🔗 Postman Collection

You can test all the endpoints using this Postman collection:

👉 [Download Postman Collection](https://drive.google.com/file/d/1ULfwCqNaGDx04Mk53dh35fLztQ7IrGaV/view?usp=sharing)

> ⚠️ Don't forget to attach a CSV file in each request to test the endpoints properly.

---

---

## 🧪 Supported Endpoints

Each endpoint accepts a CSV file with an **n x n matrix** and performs the respective operation:

| Endpoint        | Description                                              | Example Output                |
|----------------|----------------------------------------------------------|-------------------------------|
| `/echo`        | Returns the matrix in original format                    | `1,2,3`<br>`4,5,6`<br>`7,8,9` |
| `/invert`      | Returns the transposed matrix                            | `1,4,7`<br>`2,5,8`<br>`3,6,9` |
| `/flatten`     | Flattens the matrix to a single comma-separated string   | `1,2,3,4,5,6,7,8,9`           |
| `/sum`         | Returns the sum of all matrix elements                   | `45`                          |
| `/multiply`    | Returns the product of all matrix elements               | `362880`                      |

---

## 📂 CSV Input Format

Make sure the CSV file represents an `n x n` matrix. For example:

---

## 🌍 Accessing the Application

The app is deployed on [Render.com](https://render.com). You can use the **[Postman Collection](https://drive.google.com/file/d/1ULfwCqNaGDx04Mk53dh35fLztQ7IrGaV/view?usp=sharing)** to test the endpoints on both:


- **Remote Render URL** (already configured in the Postman collection)

---

## 🐳 Running the Application with Docker

### Build and run the Docker Image
install docker on your machine if not there

```bash
docker build -t league-backend-challenge
docker run -p 8080:8080 league-backend-challenge