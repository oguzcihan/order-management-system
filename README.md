# Project Name: Order Management System

### Projenin Amacı
Bu proje, bir sipariş yönetim sistemi olarak tasarlanmıştır. Kullanıcılar, müşteri, ürün, sipariş ve sipariş öğeleri gibi temel varlıklarla işlem yapabilmektedir. Sistem, aşağıdaki işlevleri sunmaktadır:

* Müşteri Yönetimi: Müşterilerle ilgili işlemler (CRUD).
* Ürün Yönetimi: Ürünlerle ilgili işlemler (CRUD).
* Sipariş Yönetimi: Siparişlerle ilgili işlemler (CRUD).
* Sipariş Öğeleri Yönetimi: Siparişlerdeki ürünlerin yönetimi (CRUD).
* Bu proje, DDD(Domain Driven Design) temel alarak tasarlanmıştır. Proje, Java ve Spring Boot kullanılarak yapılmıştır. Veritabanı olarak PostgreSQL kullanılmıştır.


### Guides

### Gereksinimler
* JDK 11 veya daha yüksek bir sürüm
* PostgreSQL
* Maven 
* Docker

## How to Run

To run the Flight Service API locally, follow these steps:

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/
    ```
2. **Build and Run the Project:**

   Proje ilk kez yüklenmişse aşagıdaki docker komutu kullanılmalıdır:
     ```bash
    docker compose -f docker-compose.yml up --build -d 
     ```
   bu komut API build edip docker container'ı oluşturur ve SWAGGER: http://localhost:8080/swagger-ui/index.html adresinden ulaşılabilir hale getirir.
   
   Docker image oluşmuş ve API tekrar çalıştırılmak isteniyorsa aşagıdaki docker komutu kullanılmalıdır:
    ```bash
    docker compose -f docker-compose.yml up -d 
    ```
3. **Access Swagger UI:**

   Open your web browser and go to [SWAGGER URL](http://localhost:8080/swagger-ui/index.html) to explore the API documentation using Swagger.

   To open the Test Report URL, go to the test report url located in the swagger documentation: [TEST REPORT URL](http://localhost:63342/order-management-system/target/reports/surefire.html?_ijt=thlaaghe14283nl8v2h5ffu27h&_ij_reload=RELOAD_ON_SAVE)
4. **Development Environment:**

If you want to work on the project, you can use an integrated development environment (IDE) like Spring Tool Suite or IntelliJ IDEA.
