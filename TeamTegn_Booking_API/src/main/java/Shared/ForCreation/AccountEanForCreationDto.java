package Shared.ForCreation;
import java.util.List;

public class AccountEanForCreationDto {
    private String eanNumber;
    private int accountId;

    public AccountEanForCreationDto(){

    }

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
