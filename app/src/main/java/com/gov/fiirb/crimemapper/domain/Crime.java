package com.gov.fiirb.crimemapper.domain;

/**
 * Created by ble04 on 13/11/2015.
 */
public class Crime {

    private String category;

    private String location_type;

    private CrimeLocation location;

    private String context;

    private OutcomeStatus outcome_status;

    private String persistent_id;

    private Long id;

    private String location_subtype;

    private String month;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public CrimeLocation getLocation() {
        return location;
    }

    public void setLocation(CrimeLocation location) {
        this.location = location;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public OutcomeStatus getOutcome_status() {
        return outcome_status;
    }

    public void setOutcome_status(OutcomeStatus outcome_status) {
        this.outcome_status = outcome_status;
    }

    public String getPersistent_id() {
        return persistent_id;
    }

    public void setPersistent_id(String persistent_id) {
        this.persistent_id = persistent_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation_subtype() {
        return location_subtype;
    }

    public void setLocation_subtype(String location_subtype) {
        this.location_subtype = location_subtype;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
