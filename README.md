# URL Shortening Service API

A robust RESTful API built with **Spring Boot** that allows users to shorten long URLs, manage them through a full CRUD, and track access statistics. This project demonstrates clean code practices, layered architecture, and automated timestamp management.

## 🚀 Features

- **URL Shortening:** Generate unique, random 6-character codes for any long URL.
- **Full CRUD:** Create, Retrieve, Update, and Delete shortened URLs.
- **Error Handling**: The API returns standard HTTP status codes.
- **Access Statistics:** Track how many times a shortened link has been accessed.
- **Automatic Timestamps:** Managed via JPA Lifecycle Hooks (`@PrePersist` and `@PreUpdate`).
- **Data Integrity:** Unique constraints and existence checks to prevent collisions.

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.11**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Jakarta Bean Validation**

## 📖 API Endpoints
|**Method**|**Endpoint**|**Description**|
|---|---|---|
|`POST`|`/shorten`|Creates a new short URL|
|`GET`|`/shorten/{code}`|Retrieves the original URL and increments access count|
|`PUT`|`/shorten/{code}`|Updates the destination URL of an existing code|
|`DELETE`|`/shorten/{code}`|Removes a shortened URL from the system|
|`GET`|`/shorten/{code}/stats`|Returns detailed statistics (access count, timestamps)|

## 📡 API Usage Examples

#### 1. Create Short URL

**POST** `/shorten`

* **Request Body:**
```json
{
  "url": "https://www.github.com/gustcustodio"
}

```


* **Response (201 Created):**
```json
{
  "id": 1,
  "url": "https://www.github.com/gustcustodio",
  "shortCode": "fqdSS5",
  "createdAt": "2026-03-07T09:49:33.503168Z",
  "updatedAt": "2026-03-07T09:49:33.503168Z"
}

```



#### 2. Retrieve Original URL

**GET** `/shorten/{shortCode}`

* **Response (200 OK):**
```json
{
  "id": 1,
  "url": "https://www.github.com/gustcustodio",
  "shortCode": "fqdSS5",
  "createdAt": "2026-03-07T09:49:33.503168Z",
  "updatedAt": "2026-03-07T09:49:33.503168Z"
}

```


*Note: This request automatically increments the access counter.*

#### 3. Update Short URL

**PUT** `/shorten/{shortCode}`

* **Request Body:**
```json
{
  "url": "https://www.linkedin.com/in/gustcustodio"
}

```


* **Response (200 OK):**
```json
{
  "id": 1,
  "url": "https://www.linkedin.com/in/gustcustodio",
  "shortCode": "fqdSS5",
  "createdAt": "2026-03-07T09:49:33.503168Z",
  "updatedAt": "2026-03-07T09:55:12.842105Z"
}

```



#### 4. Get URL Statistics

**GET** `/shorten/{shortCode}/stats`

* **Response (200 OK):**
```json
{
  "id": 1,
  "url": "https://www.linkedin.com/in/gustcustodio",
  "shortCode": "fqdSS5",
  "createdAt": "2026-03-07T09:49:33.503168Z",
  "updatedAt": "2026-03-07T09:55:12.842105Z",
  "accessCount": 15
}

```



#### 5. Delete Short URL

**DELETE** `/shorten/{shortCode}`

* **Response:** `204 No Content`

---

## 🔗 Project Link
This project was developed as a solution to the challenge found at: [URL Shortening Service](https://roadmap.sh/projects/url-shortening-service).
