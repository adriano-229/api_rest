### Minimalistic RESTful API

This project is a simple implementation of a RESTful API using the client-server architecture. On the server side
there is both a web view and a RESTful API, the former is the same as the clients whereas the latter is the one the  
that allows clients to perform CRUD (Create, Read, Update, Delete)
operations
on brands and products.

#### Class Diagram

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

#### API Endpoints

To guarantee the proper functioning of the API, the following endpoints are provided:

For **Brands**
  - `GET /api/v1/brands`: Retrieve a list of all brands.
  - `GET /api/v1/brands/{id}`: Retrieve details of a specific brand by its ID.
  - `POST /api/v1/brands`: Create a new brand.
  - `PUT /api/v1/brands/{id}`: Update an existing brand by its ID.
  - `DELETE /api/v1/brands/{id}`: Delete a brand by its ID.
