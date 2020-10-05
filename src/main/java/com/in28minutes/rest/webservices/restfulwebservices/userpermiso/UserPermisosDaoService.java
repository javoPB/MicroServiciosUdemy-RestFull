package com.in28minutes.rest.webservices.restfulwebservices.userpermiso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.rest.webservices.restfulwebservices.permiso.Permiso;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

@Component
public class UserPermisosDaoService {
	private static List<UserPermisos> userPermisos = new ArrayList<>();
	private static int userPermisosCounter = 0;
	
	static{
		
		List<Permiso> allPermisos = new ArrayList<Permiso>();
		allPermisos.add(new Permiso(1, "Administrador", "Con permisos de root. (CRUD)"));
		allPermisos.add(new Permiso(2, "User", "Con permisos de usuario restringido. (CRU)"));
		allPermisos.add(new Permiso(3, "Invitado", "Con permisos de solo lectura. (R)"));
		
		List<Permiso> adminUserPermisos = new ArrayList<Permiso>();
		adminUserPermisos.add(new Permiso(1, "Administrador", "Con permisos de root. (CRUD)"));
		adminUserPermisos.add(new Permiso(2, "User", "Con permisos de usuario restringido. (CRU)"));

		List<Permiso> invitadoPermisos = new ArrayList<Permiso>();
		invitadoPermisos.add(new Permiso(3, "Invitado", "Con permisos de solo lectura. (R)"));

		
		userPermisos.add(new UserPermisos(1, new User(1, "Juan", new Date()), allPermisos));
		userPermisos.add(new UserPermisos(2, new User(2, "Felipe", new Date()), adminUserPermisos));
		userPermisos.add(new UserPermisos(3, new User(3, "Mario", new Date()), invitadoPermisos));
		
		userPermisosCounter = userPermisos.size();
	}
	
	/**
	 * Obtiene todos los permisos de un User.
	 * @param idUser
	 * @return List<Permiso> 
	 */
	public List<Permiso> findAllPermisosPorUser(Integer idUser){
		for( UserPermisos userPermisos: userPermisos ) {
			if( userPermisos.getUser().getId() == idUser ) {
				return userPermisos.getPermisos();
			}
		}
		
		return null;
	}
	
	/**
	 * Agrega un nuevo permiso a un determinado User. (Regresa la nueva lista de permisos del User)
	 * @param idUser
	 * @param permiso
	 * @return List<Permiso>
 	 */
	public List<Permiso> savePermisosPorUser(Integer idUser, Permiso permiso) {
		for( UserPermisos userPermiso:userPermisos ) {
			if( userPermiso.getUser().getId() == idUser ) {
				List<Permiso> permisosDelUSer = userPermiso.getPermisos();
				
				if( permiso.getId() == null ) {
					permiso.setId(permisosDelUSer.size()+1);
				}
				
				permisosDelUSer.add(permiso);
				
				return permisosDelUSer;
			}
		}
		
		return null;
	}
	
	/**
	 * Obtiene un permiso de un user.
	 * @param idUSer
	 * @param idPermiso
	 * @return Permiso.
	 */
	public Permiso findPermisoDeUser(Integer idUser, Integer idPermiso) {
		for(UserPermisos userPermisos:userPermisos) {
			if( userPermisos.getUser().getId() == idUser ) {
				for( Permiso permiso:userPermisos.getPermisos() ) {
					if( permiso.getId() == idPermiso ) {
						return permiso;
					}
				}
			}
		}
		
		return null;
	}
	
}
