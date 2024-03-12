package backend.course.spring.uplasthackathon.service;

import backend.course.spring.uplasthackathon.dto.request.CatalogRequest;
import backend.course.spring.uplasthackathon.dto.response.CatalogResponse;
import backend.course.spring.uplasthackathon.entity.Catalog;
import backend.course.spring.uplasthackathon.entity.User;
import backend.course.spring.uplasthackathon.exception.CatalogAlreadyExistException;
import backend.course.spring.uplasthackathon.exception.NotFoundException;
import backend.course.spring.uplasthackathon.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;

    public List<Catalog> getAll() {
        return catalogRepository.findAll();
    }

    public String createCatalog(CatalogRequest request) {
        User user = getAuthUser();
        if (catalogRepository.findByCatalogName(request.getCatalogName()).isPresent()) {
            throw new CatalogAlreadyExistException("Такой каталог уже существует!");
        }


        Catalog catalog = Catalog.builder()
                .catalogName(request.getCatalogName())
                .price(request.getPrice())
                .description(request.getDescription())
                .features(request.getFeatures())
                .build();

        catalogRepository.save(catalog);

        return "Каталог успешно создан!";
    }

public CatalogResponse getCatalogById(Long id) {
    Catalog catalog = catalogRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Каталог не найден!"));

    return CatalogResponse.builder()
            .id(catalog.getId())
            .catalogName(catalog.getCatalogName())
            .description(catalog.getDescription())
            .features(catalog.getFeatures())
            .imageUrl(catalog.getImageUrl())
            .build();
}

public String updateCatalogById(Long id, CatalogRequest request) {
    Catalog catalog = catalogRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Каталог не найден!"));

    catalog.setCatalogName(request.getCatalogName());
    catalog.setDescription(request.getDescription());
    catalog.setPrice(request.getPrice());
    catalog.setDescription(request.getDescription());

    return "Данные успешно обновлены!";
}

public String deleteCatalogById(Long id) {
    Catalog catalog = catalogRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Каталог не найден!"));

    catalogRepository.save(catalog);

    return "Каталог успешно удален!";
}

private User getAuthUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
}

}
