package futurodev.acoes_futuro_api.repository;

import futurodev.acoes_futuro_api.model.entity.AcaoSustentavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {
}
