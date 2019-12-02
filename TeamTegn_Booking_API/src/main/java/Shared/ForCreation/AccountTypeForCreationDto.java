package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AccountTypeForCreationDto {
    @NotNull
    private String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
