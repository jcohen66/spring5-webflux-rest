package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReactiveMongoBootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public ReactiveMongoBootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    private void loadCategories() {


        categoryRepository.save(
                Category.builder()
                        .description("Fruits")
                        .build()
        ).block();

        categoryRepository.save(
                Category.builder()
                        .description("Nuts")
                        .build()
        ).block();

        categoryRepository.save(
                Category.builder()
                        .description("Breads")
                        .build()
        ).block();

        categoryRepository.save(
                Category.builder()
                        .description("Meats")
                        .build()
        ).block();

        categoryRepository.save(
                Category.builder()
                        .description("Eggs")
                        .build()
        );

        log.debug("Categories loaded: " + categoryRepository.count().block());
    }

    private void loadVendors() {
        vendorRepository.save(
        Vendor.builder()
                .firstname("Tom")
                .lastname("Hardy")
                .build()
        ).block();

        vendorRepository.save(
                Vendor.builder()
                        .firstname("Tommy")
                        .lastname("Shelby")
                        .build()
        ).block();

        log.debug("Vendors loaded: " + vendorRepository.count().block());
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Loading bootstrap data.");
        if(categoryRepository.count().block() == 0) {
            loadCategories();
        }

        if(vendorRepository.count().block() == 0) {
            loadVendors();
        }
        log.debug("Done Loading bootstrap data.");
    }
}
