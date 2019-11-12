package API.Repository.Mappers;
import API.Database_Entities.AccountEntity;
import API.Database_Entities.AccountTypeEntity;
import Shared.ToReturn.AccountDto;
import org.jetbrains.annotations.NotNull;

public class AccountMapper {

    public static AccountEntity mapAccountDtoToAccountEntity(@NotNull AccountDto accountDto, @NotNull AccountTypeEntity accountTypeEntity){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountDto.getId());
        accountEntity.setAccountName(accountDto.getAccountName());
        accountEntity.setAccountTypeByAccountTypeId(accountTypeEntity);
        accountEntity.setParentId(accountDto.getParentID());
        accountEntity.setPrimaryContactId(accountDto.getPrimaryContactID());
        accountEntity.setDepartmentId(accountDto.getPrimaryContactID());
        accountEntity.setEan(null);
        accountEntity.setTelephoneCode(accountDto.getTelephoneCode());
        accountEntity.setTelephoneNumber(accountDto.getTelephoneNumber());
        accountEntity.setFaxCode(accountDto.getFaxCode());
        accountEntity.setFaxNumber(accountDto.getFaxNumber());
        accountEntity.setWebsite(accountDto.getWebsite());
        accountEntity.setCvrNumber(accountDto.getCvrNumber());
        accountEntity.setStreet(accountDto.getStreet());
        accountEntity.setPostcode(accountDto.getPostcode());
        accountEntity.setCity(accountDto.getCity());
        accountEntity.setStateRegion(accountDto.getStateRegion());
        accountEntity.setCountry(accountDto.getCountry());
        accountEntity.setCreatedBy(accountEntity.getCreatedBy());
        accountEntity.setCreatedDate(accountEntity.getCreatedDate());
        accountEntity.setLastModified(accountDto.getLastModified());
        accountEntity.setLastModifiedBy(accountDto.getLastModifiedBy());
        accountEntity.setDeleted(accountDto.isDeleted());
        accountEntity.setEmail(accountDto.getEmail());
        accountEntity.setContactName(accountDto.getContactName());
        accountEntity.setContactEmail(accountDto.getContactEmail());
        accountEntity.setContactTelephone(accountDto.getContactTelephone());
        return accountEntity;
    }
}
