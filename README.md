# Minimalistic RESTful API - Version 1.0

This project is an implementation of a simple RESTful API using the client-server architecture. The server module
contains the RESTful API, while the client module is responsible for consuming the API endpoints.

## Class Diagram

**Brand** contains the following attributes:

- id: Long - Unique identifier among all persisted entities
- name: String - Name of the brand

**Product** contains the following attributes:

- id: Long - Unique identifier among all persisted entities
- name: String - Name of the product
- brand: Brand - Brand associated with the product
- price: Double - Price of the product

On creation, a product must be associated with an existing brand. However, if the brand is deleted, the product remains
in the system with a null brand reference.

## API Endpoints

To guarantee the proper functioning of the API, the following endpoints are provided:

**Brands**
  - `GET /api/v1/brands`: Retrieve a list of all brands.
  - `GET /api/v1/brands/{id}`: Retrieve details of a specific brand by its ID.
  - `POST /api/v1/brands`: Create a new brand.
  - `PUT /api/v1/brands/{id}`: Update an existing brand by its ID.
  - `DELETE /api/v1/brands/{id}`: Delete a brand by its ID.

**Products**
  - `GET /api/v1/products`: Retrieve a list of all products.
  - `GET /api/v1/products/{id}`: Retrieve details of a specific product by its ID.
  - `POST /api/v1/products`: Create a new product.
  - `PUT /api/v1/products/{id}`: Update an existing product by its ID.
  - `DELETE /api/v1/products/{id}`: Delete a product by its ID.

## Technologies Used

- Programming Language: Java 21
- Framework: Spring Boot
- Database: MySQL
- Build Tool: Maven
- API Documentation: Swagger/OpenAPI

## Features and Version Roadmap

### Version 1.0

- Initial release with basic CRUD operations for Brands and Products.
- Server and Client modules implemented.

### Yet to be implemented features for future versions:

**Authentication & Authorization**
- JWT-based authentication system
- Role-based access control (Admin, Manager, User)
- API key management for third-party integrations

**Enhanced Product Management**
- Product categories and subcategories
- Product images and media management
- Inventory tracking with stock levels
- Product variants (size, color, etc.)
- Bulk operations for products

**Advanced Brand Features**
- Brand logos and media assets
- Brand descriptions and metadata
- Brand hierarchy (parent/child brands)

**Data & Analytics**
- Product sales analytics
- Brand performance metrics
- Export functionality (CSV, Excel, PDF)
- Advanced filtering and search capabilities

**API Enhancements**
- Pagination for large datasets
- Sorting and advanced filtering
- GraphQL support
- Webhook notifications
- Rate limiting and throttling

**User Interface**
- Advanced dashboard with charts and graphs
- Drag-and-drop file uploads
- Real-time notifications
- Mobile-responsive improvements
- Bulk editing capabilities

**Integration Features**
- Third-party e-commerce platform integrations
- External API connectors
- Data import/export from various formats
- Automated synchronization tools

**Performance & Scalability**
- Redis caching layer
- Database optimization and indexing
- Microservices architecture migration
- Container orchestration support

