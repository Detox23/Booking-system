package Shared.ToReturn;

public class AssignmentInterpretationTypeDto {
    private Integer id;
    private String interpretationTypeName;
    private Boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterpretationTypeName() {
        return interpretationTypeName;
    }

    public void setInterpretationTypeName(String interpretationTypeName) {
        this.interpretationTypeName = interpretationTypeName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
