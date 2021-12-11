package kitchenpos.ui;

import kitchenpos.application.ProductService;
import kitchenpos.domain.product.Product;
import kitchenpos.dto.ProductDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductDto> create(@RequestBody final ProductDto product) {
        final Product created = productService.create(product);
        final URI uri = URI.create("/api/products/" + created.getId());

        return ResponseEntity.created(uri).body(ProductDto.of(created));
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDto>> list() {
        return ResponseEntity.ok().body(productService.list().stream()
                                                        .map(ProductDto::of)
                                                        .collect(Collectors.toList())
                                        );
    }
}
