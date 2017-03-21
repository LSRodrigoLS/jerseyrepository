package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Empleado;

public class EmpleadoDAO {
	public int registrar(Empleado emp) {
		Connection con = Utils.Connection.getConnection();
		String sql = "insert into empleado values(NULL,?,?,?,?,?)";
		PreparedStatement pstm = null;
		int resultado = -1;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, emp.getDni());
			pstm.setString(2, emp.getNombre());
			pstm.setString(3, emp.getApellido());
			pstm.setString(4, emp.getCorreo());
			pstm.setString(5, emp.getEdad());
			resultado = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return resultado;
	}

	 public int actualizar(Empleado emp){
			 Connection con = Utils.Connection.getConnection();
			 String sql="update empleado set dni=?,nombre=?,apellido=?,correo=?,edad=? where idempleado=?";			 
			 PreparedStatement pstm=null;
			 int resultado = -1;
			 try {				 
				 pstm = con.prepareStatement(sql);
				 pstm.setString(1, emp.getDni());
				 pstm.setString(2, emp.getNombre());
				 pstm.setString(3, emp.getApellido());
				 pstm.setString(4, emp.getCorreo());
				 pstm.setString(5, emp.getEdad());
				 pstm.setInt(6, emp.getId());				 
				 resultado = pstm.executeUpdate();
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }finally{
				 try {
					 pstm.close();
					 con.close();
				 } catch (Exception e2) {
					 e2.printStackTrace();
				 }
				}
			 return resultado;
	 }
	public java.util.List<Empleado> listarEmpleados() {
		Connection con = Utils.Connection.getConnection();
		List<Empleado> emps = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		String sql = "select * from empleado";
		Empleado emp = null;
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				emp = new Empleado();
				emp.setId(rs.getInt(1));
				emp.setDni(rs.getString(2));
				emp.setNombre(rs.getString(3));
				emp.setApellido(rs.getString(4));
				emp.setCorreo(rs.getString(5));
				emp.setEdad(rs.getString(6));
				emps.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
				pstm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return emps;
	}
	 public int eliminar(int codigo) {
		 int resultado = -1;
		 PreparedStatement pstm = null;
		 Connection con = Utils.Connection.getConnection();
		 String sql = "delete from empleado where idempleado=?";
		 try {
			 pstm = con.prepareStatement(sql);
			 pstm.setInt(1, codigo);
			 resultado = pstm.executeUpdate();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally {
		 try {
			 con.close();
			 pstm.close();
		 } catch (Exception e2) {
			 e2.printStackTrace();
		 }
		 }
		 return resultado;
		 }
	
	// public AutoBean buscarAuto(int codigo) {
	// PreparedStatement pstm = null;
	// Connection con = Miconexion.getConexion();
	// ResultSet rs = null;
	// String sql = "select
	// a.id_auto,a.modelo,a.anio,a.color,a.precio,a.fecha_ingreso,a.id_categoria,a.id_marca
	// from auto a where a.id_auto = ?";
	// AutoBean auto = null;
	// try {
	// pstm = con.prepareStatement(sql);
	// pstm.setInt(1, codigo);
	// rs = pstm.executeQuery();
	// while(rs.next()){
	// auto = new AutoBean();
	// auto.setIdauto(rs.getInt(1));
	// auto.setModelo(rs.getString(2));
	// auto.setAnio(rs.getString(3));
	// auto.setColor(rs.getString(4));
	// auto.setPrecio(rs.getDouble(5));
	// auto.setFecingreso(rs.getDate(6));
	// auto.setIdmarca(rs.getInt(7));
	// auto.setIdcategoria(rs.getInt(8));
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally {
	// try {
	// con.close();
	// pstm.close();
	// rs.close();
	// } catch (Exception e2) {
	// e2.printStackTrace();
	// }
	// }
	// return auto;
	// }
	//
	// public List<MarcaBean> listarMarca() {
	// List<MarcaBean> autos = new ArrayList<>();
	// Connection con = Miconexion.getConexion();
	// PreparedStatement pstm = null;
	// ResultSet rs = null;
	// String sql = "select * from marca";
	// MarcaBean auto = null;
	// try {
	// pstm = con.prepareStatement(sql);
	// rs = pstm.executeQuery();
	// while(rs.next()){
	// auto = new MarcaBean();
	// auto.setIdmarca(rs.getInt(1));
	// auto.setDescripcion(rs.getString(2));
	// autos.add(auto);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally {
	// try {
	// con.close();
	// pstm.close();
	// rs.close();
	// } catch (Exception e2) {
	// e2.printStackTrace();
	// }
	// }
	// return autos;
	// }
	//
	//
	// public List<CategoriaBean> listarCategoria() {
	// List<CategoriaBean> autos = new ArrayList<>();
	// Connection con = Miconexion.getConexion();
	// PreparedStatement pstm = null;
	// ResultSet rs = null;
	// String sql = "select * from categoria";
	// CategoriaBean auto = null;
	// try {
	// pstm = con.prepareStatement(sql);
	// rs = pstm.executeQuery();
	// while(rs.next()){
	// auto = new CategoriaBean();
	// auto.setIdcategoria(rs.getInt(1));
	// auto.setDescripcion(rs.getString(2));
	// autos.add(auto);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally {
	// try {
	// con.close();
	// pstm.close();
	// rs.close();
	// } catch (Exception e2) {
	// e2.printStackTrace();
	// }
	// }
	// return autos;
	// }
	
}
