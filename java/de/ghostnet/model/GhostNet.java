package de.ghostnet.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ghost_net")
public class GhostNet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String latitude;
    private String longitude;

    @Column(name = "estimated_size")
    private String estimatedSize;

    private String status;

    @Column(name = "reporter_name")
    private String reporterName;

    @Column(name = "reporter_phone")
    private String reporterPhone;

    @Column(name = "rescuer_name")
    private String rescuerName;

    @Column(name = "rescuer_phone")
    private String rescuerPhone;

    public GhostNet() {
    }

    public GhostNet(String latitude, String longitude, String estimatedSize,
                    String status, String reporterName, String reporterPhone) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.estimatedSize = estimatedSize;
        this.status = status;
        this.reporterName = reporterName;
        this.reporterPhone = reporterPhone;
    }

    public Long getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getEstimatedSize() {
        return estimatedSize;
    }

    public String getStatus() {
        return status;
    }

    public String getReporterName() {
        return reporterName;
    }

    public String getReporterPhone() {
        return reporterPhone;
    }

    public String getRescuerName() {
        return rescuerName;
    }

    public String getRescuerPhone() {
        return rescuerPhone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRescuerName(String rescuerName) {
        this.rescuerName = rescuerName;
    }

    public void setRescuerPhone(String rescuerPhone) {
        this.rescuerPhone = rescuerPhone;
    }
}