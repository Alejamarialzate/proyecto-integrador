const tableBody = document.querySelector("#odontologosTable tbody");
const apiURL = "http://localhost:8080";
function fetchOdontologos() {
  // listando los odontologos

  fetch(`${apiURL}/odontologo`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td><input id="idElemento" value= ${odontologo.id} readonly></input></td>
                <td><input id="nombre" value =${odontologo.nombre} > </input></td>
                <td><input id="apellido" value =${odontologo.apellido} ></input></td>
                <td><input id="matricula" value=${odontologo.nroMatricula}></input></td>
                <td>
                  <button class="btn btn-primary btn-sm" onclick="editOdontologo()">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
              `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });


 
 
}


//'${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.nroMatricula}'
  // modificar un odontologo

  function editOdontologo(){

    var idElemento = document.getElementById("idElemento").value;
    var nombre = document.getElementById("nombre").value;
    var apellido = document.getElementById("apellido").value;
    var nroMatricula = document.getElementById("matricula").value;
    

    fetch(`${apiURL}/odontologo`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({id: idElemento, nombre, apellido, nroMatricula: nroMatricula }),
    })
      .then((response) =>{
        if(response.ok){
          alert("Odontólogo modificando con éxito");
        }else{
      alert('Error modificando odontólogo');
        }  
        })
      .catch((error) => {
        console.error("Error modificando odontólogo:", error);
      });

  }; // cierra modificar



 // eliminar un odontologo
 function deleteOdontologo(elementoId){
    
  fetch(`${apiURL}/odontologo/`.concat(elementoId), {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => response.json())
    .then((data) => {
      alert("Odontólogo eliminando con exito");
    })
    .catch((error) => {
      console.error("Error eliminando odontólogo:", error);
    });



  }; // cierra delete



fetchOdontologos();
