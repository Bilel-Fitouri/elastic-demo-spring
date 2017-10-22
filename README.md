# elastic-demo-spring

# Add a new Person

curl -X POST "http://localhost:8080/new" -d '{"name" : "toto}'

# Get All persons

curl -XGET "http://localhost:8080/all"

# Get Person by Id

curl -XGET "http://localhost:8080/byid/1"

# Get Person by name

curl -XGET "http://localhost:8080/byname?name=toto"
