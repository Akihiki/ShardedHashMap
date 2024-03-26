# Sharded HashMap Project
This project, named Sharded HashMap, is a Java application designed to mimic the functionality of a HashMap using an API and a sharded database schema. The project utilizes two databases to simulate distribution across multiple sites, enabling efficient storage and retrieval of key-value pairs.

## Getting Started
To run the project, follow these steps:

### Clone the repository:
 
```
git clone https://github.com/Akihiki/ShardedHashMap.git
```
### Database Setup
The project requires two PostgreSQL servers to mimic distribution across multiple sites. By default, these servers should be running on ports 5431 and 5432, respectively.

The database schema consists of a single table named **hashmaptable** within the **mapDB** database. The table structure is as follows:

. key: Primary key column for storing keys.
. value: Column for storing corresponding values.
## Sharding Algorithm
The sharding algorithm I implemented in this project is based on the hash code of keys. This algorithm ensures fast and efficient distribution of data across multiple databases.

. Distribution Logic: The hash code of the key determines which database the data will be stored in. In this implementation, if the hash code is even, the data is stored in one server; otherwise, it is stored in the other.

. Fetching Operation: Similarly, the hash code of the key allows for determining the database from which the data should be fetched. This eliminates the need to search through all databases, resulting in faster retrieval times.

## Exception Handling
The project includes comprehensive exception handling to ensure smooth operation even in unexpected scenarios.
