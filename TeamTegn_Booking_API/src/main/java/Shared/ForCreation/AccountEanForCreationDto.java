package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AccountEanForCreationDto {
    @NotNull
    private String eanNumber;
    @NotNull
    private int accountId;

    public String getEanNumber() {
        return eanNumber;
    }

    public void setEanNumber(String eanNumber) {
        this.eanNumber = eanNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
