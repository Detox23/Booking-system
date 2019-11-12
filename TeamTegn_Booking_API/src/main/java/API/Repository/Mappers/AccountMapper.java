package API.Repository.Mappers;

import API.Database_Entities.AccountEanEntity;
import API.Database_Entities.AccountEntity;
import API.Database_Entities.AccountTypeEntity;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.AccountTypeDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AccountMapper {

    public static AccountDto mapAccountEntityToAccountDto(@NotNull AccountEntity accountEntity, List<String> eans){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(accountEntity.getId());
        accountDto.setAccountName(accountEntity.getAccountName());
        accountDto.setAccountTypeID(accountEntity.getAccountTypeByAccountTypeId().getId());
        accountDto.setParentID(accountEntity.getParentId());
        accountDto.setPrimaryContactID(accountEntity.getPrimaryContactId());
        accountDto.setDepartmentID(accountEntity.getDepartmentId());
        accountDto.setEan(eans);
        accountDto.setTelephoneCode(accountEntity.getTelephoneCode());
        accountDto.setTelephoneNumber(accountEntity.getTelephoneNumber());
        accountDto.setFaxCode(accountEntity.getFaxCode());
        accountDto.setFaxNumber(accountEntity.getFaxNumber());
        accountDto.setWebsite(accountEntity.getWebsite());
        accountDto.setCvrNumber(accountEntity.getCvrNumber());
        accountDto.setStreet(accountEntity.getStreet());
        accountDto.setPostcode(accountEntity.getPostcode());
        accountDto.setCity(accountEntity.getCity());
        accountDto.setStateRegion(accountEntity.getStateRegion());
        accountDto.setCountry(accountEntity.getCountry());
        accountDto.setCreatedBy(accountEntity.getCreatedBy());
        accountDto.setEmail(accountEntity.getEmail());
        accountDto.setContactName(accountEntity.getContactName());
        accountDto.setContactEmail(accountEntity.getContactEmail());
        accountDto.setContactTelephone(accountEntity.getContactTelephone());
        accountDto.setAccountType(mapAccountTypeEntityToAccountTypeDto(accountEntity.getAccountTypeByAccountTypeId()));//new Mapper
        accountDto.setDeleted(accountEntity.isDeleted());
        accountDto.setCreatedDate(accountEntity.getCreatedDate());
        accountDto.setLastModified(accountEntity.getLastModified());
        accountDto.setLastModifiedBy(accountEntity.getLastModifiedBy());
        return accountDto;

    }

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


    public static AccountEntity mapAccountForCreationToAccountEntity(@NotNull AccountForCreationDto accountForCreationDto,AccountTypeEntity accountTypeEntity) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountTypeByAccountTypeId(accountTypeEntity);
        accountEntity.setAccountName(accountForCreationDto.getAccountName());
        accountEntity.setParentId(accountForCreationDto.getParentId());
        accountEntity.setPrimaryContactId(accountForCreationDto.getPrimaryContactId());
        accountEntity.setDepartmentId(accountForCreationDto.getDepartmentId());
        accountEntity.setTelephoneCode(accountForCreationDto.getTelephoneCode());
        accountEntity.setTelephoneNumber(accountForCreationDto.getTelephoneNumber());
        accountEntity.setFaxCode(accountForCreationDto.getFaxCode());
        accountEntity.setFaxNumber(accountForCreationDto.getFaxNumber());
        accountEntity.setWebsite(accountForCreationDto.getWebsite());
        accountEntity.setCvrNumber(accountForCreationDto.getCvrNumber());
        accountEntity.setStreet(accountForCreationDto.getStreet());
        accountEntity.setPostcode(accountForCreationDto.getPostcode());
        accountEntity.setCity(accountForCreationDto.getCity());
        accountEntity.setStateRegion(accountForCreationDto.getStateRegion());
        accountEntity.setCountry(accountForCreationDto.getCountry());
        accountEntity.setCreatedBy(accountForCreationDto.getCreatedBy());
        accountEntity.setEmail(accountForCreationDto.getEmail());
        accountEntity.setContactName(accountForCreationDto.getContactName());
        accountEntity.setContactEmail(accountForCreationDto.getContactEmail());
        accountEntity.setContactTelephone(accountForCreationDto.getContactTelephone());
        return accountEntity;
    }

    public static AccountEanEntity mapAccountEanForCreationToAccountEanEntity(@NotNull AccountEanForCreationDto accountEanForCreationDto){
        AccountEanEntity accountEanEntity = new AccountEanEntity();
        accountEanEntity.setEanNumber(accountEanForCreationDto.getEanNumber());
        accountEanEntity.setAccountId(accountEanForCreationDto.getAccountId());
        return accountEanEntity;
    }

    public static AccountTypeDto mapAccountTypeEntityToAccountTypeDto(@NotNull AccountTypeEntity accountTypeEntity){
        AccountTypeDto accountTypeDto = new AccountTypeDto();
        accountTypeDto.setId(accountTypeEntity.getId());
        accountTypeDto.setAccountType(accountTypeEntity.getAccountType());
        accountTypeDto.setGrantApplies(accountTypeEntity.getGrantApplies());
        accountTypeDto.setDeleted(accountTypeEntity.getDeleted());
        return accountTypeDto;
    }
}
