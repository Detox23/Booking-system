package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AccountTypeForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String accountType;

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

}
