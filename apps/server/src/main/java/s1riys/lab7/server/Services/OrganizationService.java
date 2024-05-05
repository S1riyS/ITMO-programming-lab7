package s1riys.lab7.server.Services;

import s1riys.lab7.common.models.Organization;
import s1riys.lab7.server.dao.OrganizationDAO;
import s1riys.lab7.server.entities.OrganizationEntity;

public class OrganizationService implements Service {
    private final OrganizationDAO organizationDAO;

    public OrganizationService() {
        this.organizationDAO = new OrganizationDAO();
    }

    public OrganizationEntity add(Organization organization) {
        OrganizationEntity entity = new OrganizationEntity(organization);
        return organizationDAO.add(entity);
    }

    public OrganizationEntity findById(long id) {
        return organizationDAO.findById(id);
    }

    public OrganizationEntity findByObject(Organization organizationObject) {
        return organizationDAO.findByObject(organizationObject);
    }

    public void update(long id, Organization updatedOrganization) {
        OrganizationEntity entityToUpdate = organizationDAO.findById(id);
        entityToUpdate.update(updatedOrganization);
        organizationDAO.update(entityToUpdate);
    }

    @Override
    public String getName() {
        return "OrganizationService";
    }
}
