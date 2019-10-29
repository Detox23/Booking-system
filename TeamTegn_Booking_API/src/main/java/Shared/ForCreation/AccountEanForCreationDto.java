package Shared.ForCreation;
import java.util.List;

public class AccountEanForCreationDto {
    private String eanNumber;
    private String accountId;

    public AccountEanForCreationDto(){

    }

    public String getEanNumber() {
        return eanNumber;
    }

    public void setEanNumber(String eanNumber) {
        this.eanNumber = eanNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
