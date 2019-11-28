package API.Repository.CityPostcodes;

import API.Database_Entities.CityPostcodesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityPostcodesDAO extends JpaRepository<CityPostcodesEntity, Integer> {
    Optional<CityPostcodesEntity> findFirstByPostcodeIs(String postcode);

    Optional<CityPostcodesEntity> findFirstByCityIs(String city);

}
