package API.Repository.CityPostcodes;

import API.Models.Database_Entities.WiPostcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WI_PostcodeDAO extends JpaRepository<WiPostcodeEntity, Integer> {
    Optional<WiPostcodeEntity> findByPostcodeIs(String postcode);
}
