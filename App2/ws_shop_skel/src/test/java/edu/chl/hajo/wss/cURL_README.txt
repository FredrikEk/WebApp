****************************
*
*  cURL testing of ProducCatalogueResource 
*
*****************************
(there's a nice REST Console as an extension to Chrome)

Testing the ProductCatalogueResource from command line.
This runs on GlassFish 4.0 

------------------------------------------------------------
# *** GET *** (read) NOTE: 8080 is for GlassFish
curl -v -H "Accept: application/json" http://localhost:8080/ws_shop/webresources/products  
# Possible need another id last!
curl -v -H "Accept: application/json" http://localhost:8080/ws_shop/webresources/products/1

# Query parameters. NOTE: Must quote URI. Will get list (with possible 0 elements)
curl -v -H "Accept: application/json" "http://localhost:8080/ws_shop/webresources/products/range?fst=0&max=2"

# Get primitive type
curl -v -H "Accept: application/json" http://localhost:8080/ws_shop/webresources/products/count

# *** POST (create) ***
curl -v -X POST http://localhost:8080/ws_shop/webresources/products --data "name=***POST***&price=99.99"

# *** PUT (update) ***
curl -v -X PUT http://localhost:8080/ws_shop/webresources/products/1 --data "name=***PUT***&price=999"

# *** DELETE (delete) ***
curl -v -X DELETE http://localhost:8080/ws_shop/webresources/products/1 



