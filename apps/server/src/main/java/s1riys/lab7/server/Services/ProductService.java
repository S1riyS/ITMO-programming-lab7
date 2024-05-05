package s1riys.lab7.server.Services;

import s1riys.lab7.common.models.Organization;
import s1riys.lab7.common.models.Product;
import s1riys.lab7.common.models.User;
import s1riys.lab7.server.Services.utils.ServiceLocator;
import s1riys.lab7.server.dao.ProductDAO;
import s1riys.lab7.server.entities.OrganizationEntity;
import s1riys.lab7.server.entities.ProductEntity;
import s1riys.lab7.server.entities.UserEntity;

import java.util.List;

public class ProductService implements Service {
    private final ProductDAO productDAO;
    private final UserService userService = (UserService) ServiceLocator.getService("UserService");
    private final OrganizationService organizationService = (OrganizationService) ServiceLocator.getService("OrganizationService");

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public long add(Product product, User user) {
        ProductEntity productEntity = new ProductEntity(product);

        UserEntity owner = userService.findById(user.getId());
        System.out.println(owner);
        productEntity.setCreator(owner);

        OrganizationEntity manufacturer = null;
        if (product.getManufacturer() != null) {
            OrganizationEntity existingOrganization = organizationService.findByObject(product.getManufacturer());

            if (existingOrganization == null) manufacturer = organizationService.add(product.getManufacturer());
            else manufacturer = existingOrganization;
        }
        productEntity.setManufacturer(manufacturer);

        long newId = productDAO.add(productEntity);
        return newId;
    }

    public ProductEntity findById(long id) {
        return productDAO.findById(id);
    }

    public List<ProductEntity> findAll() {
        return productDAO.findAll();
    }

    public void update(long id, Product updatedProduct) {
        ProductEntity entityToUpdate = productDAO.findById(id);

        if (updatedProduct.getManufacturer() == null) entityToUpdate.setManufacturer(null);
        else {
            OrganizationEntity existingOrganization = organizationService.findByObject(updatedProduct.getManufacturer());
            if (existingOrganization != null) entityToUpdate.setManufacturer(existingOrganization);
            else {
                Organization updatedManufacturer = updatedProduct.getManufacturer();
                organizationService.update(updatedManufacturer.getId(), updatedManufacturer);
            }
        }
        entityToUpdate.update(updatedProduct);
        productDAO.update(entityToUpdate);
    }

    public int removeById(long id) {
        return productDAO.removeById(id);
    }

    @Override
    public String getName() {
        return "ProductService";
    }
}
