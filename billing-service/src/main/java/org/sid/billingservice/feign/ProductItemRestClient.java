package org.sid.billingservice.feign;


import com.google.inject.internal.asm.$TypePath;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient (name="PRODUCT-SERVICE")
public interface ProductItemRestClient {
    @GetMapping (path = "/products")
    PagedModel<Product> pageProduct();

    //@RequestParam(name = "page") int page,
    //                                    @RequestParam(name = "size") int size

    @GetMapping (path = "/products/{id}")
    Product findProductItemById(@PathVariable("id") Long id);

}
