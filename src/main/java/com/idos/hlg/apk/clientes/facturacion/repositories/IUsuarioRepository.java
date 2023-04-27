package com.idos.hlg.apk.clientes.facturacion.repositories;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByEnable(boolean enable);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario p SET p.enable = false WHERE p.id = ?1")
    void disableUsuario(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario p SET p.enable = true WHERE p.id = ?1")
    void enableUsuario(Long id);
}
