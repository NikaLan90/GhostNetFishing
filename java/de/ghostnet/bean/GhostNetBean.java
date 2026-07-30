package de.ghostnet.bean;

import java.io.Serializable;
import java.util.List;

import de.ghostnet.dao.GhostNetDAO;
import de.ghostnet.model.GhostNet;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class GhostNetBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String latitude;
    private String longitude;
    private String estimatedSize;
    private String reporterName;
    private String reporterPhone;

    private Long selectedGhostNetId;
    private String rescuerName;
    private String rescuerPhone;

    private transient GhostNetDAO ghostNetDAO = new GhostNetDAO();

    private GhostNetDAO getGhostNetDAO() {
        if (ghostNetDAO == null) {
            ghostNetDAO = new GhostNetDAO();
        }
        return ghostNetDAO;
    }

    // Speichert ein neu gemeldetes Geisternetz in der Datenbank.
    public String saveGhostNet() {
        GhostNet ghostNet = new GhostNet(
                latitude,
                longitude,
                estimatedSize,
                "GEMELDET",
                reporterName,
                reporterPhone
        );

        getGhostNetDAO().save(ghostNet);

        latitude = "";
        longitude = "";
        estimatedSize = "";
        reporterName = "";
        reporterPhone = "";

        return "offeneNetze?faces-redirect=true";
    }

    // Merkt sich, für welches Geisternetz eine Bergung übernommen werden soll.
    public String prepareRescue(Long ghostNetId) {
        selectedGhostNetId = ghostNetId;
        rescuerName = "";
        rescuerPhone = "";
        return "bergungUebernehmen?faces-redirect=true";
    }

    // Trägt eine bergende Person ein und ändert den Status.
    public String assignRescuer() {
        GhostNet ghostNet = getGhostNetDAO().findById(selectedGhostNetId);

        if (ghostNet != null) {
            ghostNet.setRescuerName(rescuerName);
            ghostNet.setRescuerPhone(rescuerPhone);
            ghostNet.setStatus("BERGUNG_BEVORSTEHEND");
            getGhostNetDAO().update(ghostNet);
        }

        return "offeneNetze?faces-redirect=true";
    }

    // Markiert ein Geisternetz als geborgen.
    public String markRecovered(Long ghostNetId) {
        GhostNet ghostNet = getGhostNetDAO().findById(ghostNetId);

        if (ghostNet != null) {
            ghostNet.setStatus("GEBORGEN");
            getGhostNetDAO().update(ghostNet);
        }

        return null;
    }

    // Markiert ein Geisternetz als verschollen.
    public String markMissing(Long ghostNetId) {
        GhostNet ghostNet = getGhostNetDAO().findById(ghostNetId);

        if (ghostNet != null) {
            ghostNet.setStatus("VERSCHOLLEN");
            getGhostNetDAO().update(ghostNet);
        }

        return null;
    }

    public List<GhostNet> getGhostNets() {
        return getGhostNetDAO().findAll();
    }

    public Long getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(String estimatedSize) {
        this.estimatedSize = estimatedSize;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone;
    }

    public String getRescuerName() {
        return rescuerName;
    }

    public void setRescuerName(String rescuerName) {
        this.rescuerName = rescuerName;
    }

    public String getRescuerPhone() {
        return rescuerPhone;
    }

    public void setRescuerPhone(String rescuerPhone) {
        this.rescuerPhone = rescuerPhone;
    }
}