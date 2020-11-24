package com.eparking.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eparking.springboot.app.entity.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("select u from Usuario u where u.codigo =:codigo")
	public Usuario findOne(@Param("codigo") Integer codigo);
	
	/*@Query("select u from Usuario u where u.username =:username and u.clave =:clave")
	public Usuario Login(@Param("username") String username, @Param("clave") String clave);*/
	
	@Query("select u from Usuario u where u.username =:username")
	public Usuario Login(@Param("username") String username);
	

}
