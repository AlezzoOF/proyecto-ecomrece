package com.idos.hlg.apk.clientes.facturacion.models.entities;

import com.idos.hlg.apk.clientes.facturacion.models.entities.auxiliar.Rol;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "usuario_id")
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String pwd;
    private boolean enable;
    @Temporal(TemporalType.DATE)
    @Column(name = "delete_date")
    private Date deleteDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)//Relacion con factura
    private List<Factura> facturas;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {
        enable = true;
        facturas = new ArrayList<>();
    }

    public Usuario(String nombre, String apellido, String email, String pwd, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pwd = pwd;
        this.rol = rol;
        enable = true;
        facturas = new ArrayList<>();
    }

    public void addFactura(Factura factura) {
        facturas.add(factura);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rol.name());

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
