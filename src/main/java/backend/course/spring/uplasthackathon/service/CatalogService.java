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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private final ImageUploadService uploadService;

    public List<Catalog> getAll() {
        return catalogRepository.findAll();
    }

    public String createCatalog(CatalogRequest request) {
        User user = getAuthUser();
        if (catalogRepository.findByCatalogName(request.getCatalogName()).isPresent()) {
            throw new CatalogAlreadyExistException("Такой каталог уже существует!");
        }


        Catalog catalog = Catalog.builder()
                .userId(user.getId())
                .catalogName(request.getCatalogName())
                .price(request.getPrice())
                .description(request.getDescription())
                .features(request.getFeatures())
                .build();

        catalogRepository.save(catalog);

        return "Каталог успешно создан!";
    }

    public CatalogResponse getCatalogById(Long id) {
        User user = getAuthUser();
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Каталог не найден!"));

        return CatalogResponse.builder()
                .id(catalog.getId())
                .userId(user.getId())
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
        catalog.setFeatures(request.getFeatures());
        catalogRepository.save(catalog);

        return "Данные успешно обновлены!";
    }

    public String deleteCatalogById(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Каталог не найден!"));

        catalogRepository.delete(catalog);

        return "Каталог успешно удален!";
    }

    public String imageUpload(MultipartFile multipartFile, Long catalogId) {
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new NotFoundException("Каталог не найден!"));
        catalog.setImageUrl(uploadService.saveImage(multipartFile));
        catalogRepository.save(catalog);

        return "Фотография успешно создана!";
    }

    private User getAuthUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
