package Utils;
import java.util.ArrayList;
import com.google.gson.Gson;
import dao.EmpleadoDAO;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Gson json = new Gson();
			String resul = json.toJson(new EmpleadoDAO().listarEmpleados(),ArrayList.class);
			System.out.println(resul);
	}

}
