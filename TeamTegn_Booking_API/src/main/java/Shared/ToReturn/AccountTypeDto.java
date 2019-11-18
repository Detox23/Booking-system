package Shared.ToReturn;

public class AccountTypeDto {
    private int id;
    private String accountType;
    private Boolean grantApplies;
    private Boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean getGrantApplies() {
        return grantApplies;
    }

    public void setGrantApplies(Boolean grantApplies) {
        this.grantApplies = grantApplies;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

}
