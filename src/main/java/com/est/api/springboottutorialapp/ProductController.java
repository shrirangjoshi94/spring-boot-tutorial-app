package com.est.api.springboottutorialapp;

import com.est.api.springboottutorialapp.entities.Product;
import com.est.api.springboottutorialapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        Product product1 = this.productService.create(product);

        return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
    }

//    update
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int productId)
    {
        Product product1 = this.productService.update(product, productId);

        return new ResponseEntity<>(product1, HttpStatus.OK);
    }
//    deelte

    @DeleteMapping("/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable int productId)
    {
        this.productService.delete(productId);
        Map<String, String> response = Map.of("message", "product deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    single product
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId)
    {
        Product product = this.productService.getById(productId);

        // 👇 Redundant null check after already assuming product exists
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    get all
//    single product
@GetMapping("")
public ResponseEntity<List<Product>> getAll()
{
    List<Product> products = this.productService.getAll();

    // 👇 Unused variable – SonarCloud will flag this
    int debugFlag = 1;

    return new ResponseEntity<>(products, HttpStatus.OK);
}

    @PostMapping("/debug-auth")
    public ResponseEntity<String> debugLogin() {
        // 👇 Hardcoded secret – SonarCloud will scream about this
        String password = "admin123";

        return new ResponseEntity<>("Logged in with password: " + password, HttpStatus.OK);
    }

}
