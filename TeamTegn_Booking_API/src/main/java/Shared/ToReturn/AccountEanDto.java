package Shared.ToReturn;

public class AccountEanDto {
    private int id;
    private String eanNumber;
    private int accountId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
