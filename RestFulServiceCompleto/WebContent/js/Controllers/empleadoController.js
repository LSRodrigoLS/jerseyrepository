var appController = angular.module("myApp.Controller",[]);

appController.controller("empleadoController",function($scope,$http){

	$scope.listadoEmpleado = [];
	
	var url_http = "restful/empleado/"

	var listar = function(){
		$http.get(url_http + "listar").then(function(respuesta){
			respuesta.data.forEach(function(index){
				$scope.listadoEmpleado.push(index);
			});
		});
	}
	listar();

	$scope.mantenerEmpleado = function(){
		if($scope.isModificable === true){
			$scope.modificarEmpleado();
		}
		else{
			$scope.registrarEmpleado();
		}
	}

	$scope.buscarEmpleado = function(empleadoSeleccionado){
		$scope.id = empleadoSeleccionado.id;
		$scope.nombre = empleadoSeleccionado.nombre;
		$scope.apellido= empleadoSeleccionado.apellido;
		$scope.dni = empleadoSeleccionado.dni;
		$scope.correo= empleadoSeleccionado.correo;
		$scope.edad = empleadoSeleccionado.edad;
		$scope.isModificable = true;		
	}

	$scope.registrarEmpleado = function(){
		var empleado = {
			id : 0,
			nombre: $scope.nombre,
			apellido : $scope.apellido,
			dni: $scope.dni,
			correo: $scope.correo,
			edad: $scope.edad
		}

		$http({
			url: url_http + "registrar",
			method:"POST",		
			data: JSON.stringify(empleado),
			headers: {'Content-Type': 'application/json'}
		}).then(function success( response){
				$scope.listadoEmpleado = [];
				listar();
		},function error(error){
			console.log(error);
		});
	}

	$scope.modificarEmpleado = function(){
		var emple = {
			id : $scope.id,
			nombre: $scope.nombre,
			apellido : $scope.apellido,
			dni: $scope.dni,
			correo: $scope.correo,
			edad: $scope.edad
		}

		$http({
			url: url_http + "actualizar",
			method:"POST",
			data: JSON.stringify(emple),
			headers: {'Content-Type': 'application/json'}
		}).then(function success( response){
				$scope.listadoEmpleado = [];	
				listar();
				limpiarCampos();
				$scope.isModificable = false;	
		},function error(error){
			console.log(error);
		});
	}

	$scope.eliminarEmpleado = function(empleado){
		$http({
			url:url_http + "eliminar",
			method:"POST",
			data: {'codigo' : empleado.id},
			headers: {'Content-Type': 'application/json'}			
		}).then(function success( response){
			$scope.listadoEmpleado = [];
			listar();
		},function error(error){
			console.log(error);
		});
	}
	var limpiarCampos = function(){
		$scope.id = "";
		$scope.nombre = "";
		$scope.apellido= "";
		$scope.dni = "";
		$scope.correo= "";
		$scope.edad = "";
	}

});