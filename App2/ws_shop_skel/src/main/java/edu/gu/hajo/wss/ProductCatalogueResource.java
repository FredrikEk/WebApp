package edu.gu.hajo.wss;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import edu.gu.hajo.wss.core.SingletonShop;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service for the product catalogue (an Adapter) We always use
 * Response as return value (probably easier to modify)
 *
 *
 * @author hajo
 */
@Path("products") // Leading trailing slash doesn't matter, see web.xml
public class ProductCatalogueResource {

    private final IShop shop = SingletonShop.INSTANCE.getShop();
	private static final Logger LOG = Logger.getLogger(ProductCatalogueResource.class.getName());
	
	
	@GET
	@Path(value = "{id}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Response find(@PathParam("id") final long id) {
		Product p = shop.getProductCatalogue().find(id);
		if(p != null) {
			return Response.ok(new ProductWrapper(p)).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	@GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response findAll() {
        LOG.log(Level.INFO, "{0}:findAll", this);
		List<Product> unwrappedProducts = shop.getProductCatalogue().findAll();
		List<ProductWrapper> wrappedProducts = new ArrayList<ProductWrapper>();
		for(Product p : unwrappedProducts) {
			wrappedProducts.add(new ProductWrapper(p));
		}
		GenericEntity<Collection<ProductWrapper>> genericProducts = new GenericEntity<Collection<ProductWrapper>>(wrappedProducts){
		};
        return Response.ok(genericProducts).build();
    }
    
    @GET
    @Path("range")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("fst") int fst, @QueryParam("count") int count) {
        LOG.log(Level.INFO, "{0}:findRange {1} {2}", new Object[]{this, fst, count});
		List<Product> unwrappedProducts = shop.getProductCatalogue().findRange(fst, count);
		List<ProductWrapper> wrappedProducts = new ArrayList<ProductWrapper>();
		for(Product p : unwrappedProducts) {
			wrappedProducts.add(new ProductWrapper(p));
		}
        GenericEntity<Collection<ProductWrapper>> genericProducts = new GenericEntity<Collection<ProductWrapper>>(wrappedProducts){
		};
        return Response.ok(genericProducts).build();
    }

    @GET
    @Path(value = "count")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response count() {
        LOG.log(Level.INFO, "{0}: count", this);
        int c = shop.getProductCatalogue().count();
        // Can't return primitive types, create object
        JsonObject value = Json.createObjectBuilder().add("value", c).build();
        return Response.ok(value).build();
    }

    @DELETE
    @Path(value = "{id}")
    public Response delete(@PathParam("id") final Long id) {
        LOG.log(Level.INFO, "{0}:delete{1}", new Object[]{this, id});
        try{
			shop.getProductCatalogue().delete(id);
			return Response.ok().build();
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
    }

    @PUT
    @Path(value = "{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final Long id,
            JsonObject product) {
        LOG.log(Level.INFO, "{0}:update{1}" + id + " " + product.getString("name") + " " + product.getInt("price"), new Object[]{this, id});
		try{
			Product old = shop.getProductCatalogue().find(id);
			shop.getProductCatalogue().update(new Product(id, product.getString("name"), product.getInt("price")));
			LOG.log(Level.INFO, "works");
			return Response.ok().build();
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
    }

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response create(JsonObject product) {
		try {
			Product p = new Product(product.getString("name"), product.getInt("price"));
			shop.getProductCatalogue().create(p);
			return Response.ok().build();
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
