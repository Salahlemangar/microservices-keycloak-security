package org.sid.billingservice.security;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Date;

@Controller
class BillingController {
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    @GetMapping("/suppliers")
    @ResponseBody
    public Collection<Bill> billings() {
        ResponseEntity<PagedModel<Bill>> response =
                keycloakRestTemplate.exchange("http://localhost:8083/bills",
                        HttpMethod.GET, null, new ParameterizedTypeReference<PagedModel<Bill>>() {
                        });
        return response.getBody().getContent();
    }

    @GetMapping("/listSuppliers")
    public String listSuppliers(Model model) {
        ResponseEntity<PagedModel<Bill>> response =
                keycloakRestTemplate.exchange("http://localhost:8083/bills",
                        HttpMethod.GET, null, new ParameterizedTypeReference<PagedModel<Bill>>() {
                        });
        model.addAttribute("suppliers", response.getBody());
        return "listSuppliers";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Model model) {
        model.addAttribute("errorMessage", "Not Authorized");
        return "errors";
    }
}
    @Data
    class Bill{
        private Long id;
        private Date billingDate ;
        private Collection<ProductItem> productItems;
        private Long customerID;
        private Customer customer;
    }

