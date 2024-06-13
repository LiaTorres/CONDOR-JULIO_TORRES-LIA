window.addEventListener("load", function () {
  (function () {
    //con fetch invocamos a la API de pacientes con el método GET
    //nos devolverá un JSON con una colección de pacientes
    const url = "/pacientes";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        for (let paciente of data) {
          console.log(paciente);

          var table = document.getElementById("pacientesTableBody"); // Asegúrate de que este ID exista en tu HTML
          var pacienteRow = table.insertRow();
          let tr_id = paciente.id;
          pacienteRow.id = tr_id;

          const deleteButton = createButton(
            `btn_delete_${paciente.id}`,
            "btn btn-circle btn-error",
            `deleteBy(${paciente.id})`,
            `<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="4" d="M6 18L18 6M6 6l12 12"/>
            </svg>`
          );

          const updateButton = createButton(
            `btn_id_${paciente.id}`,
            "btn btn-circle btn-success mr-4",
            `findBy(${paciente.id})`,
            `<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
              <path d="M20.71,7.04C21.1,6.65 21.1,6 20.71,5.63L18.37,3.29C18,2.9 17.35,2.9 16.96,3.29L15.12,5.12L18.87,8.87M3,17.25V21H6.75L17.81,9.93L14.06,6.18L3,17.25Z" />
            </svg>`
          );

          pacienteRow.innerHTML = `
          <td>${paciente.id}</td>
          <td class="nombre">${paciente.nombre}</td>
          <td class="apellido">${paciente.apellido}</td>
          <td class="cedula">${paciente.cedula}</td>
          <td class="domicilio">${paciente.domicilio.calle}, ${
            paciente.domicilio.numero
          }, ${paciente.domicilio.localidad}, ${
            paciente.domicilio.provincia
          }</td>
          <td class="email">${paciente.email}</td>
          <td>${updateButton}${deleteButton}</td>
        `;
        }
      })
      .catch((error) =>
        console.error("Error al obtener los pacientes:", error)
      );
  })();

  function createButton(id, className, onClick, innerHTML) {
    return `<button id="${id}" type="button" onclick="${onClick}" class="${className}">${innerHTML}</button>`;
  }

  // Manejo del modal de confirmación
  const deleteModal = document.getElementById("deleteModal");
  const confirmDeleteButton = document.getElementById("confirmDeleteButton");
  const cancelDeleteButton = document.getElementById("cancelDeleteButton");
  let pacienteIdToDelete = null; // Para almacenar el ID del paciente a eliminar

  cancelDeleteButton.addEventListener("click", () => {
    deleteModal.close();
  });

  confirmDeleteButton.addEventListener("click", () => {
    if (pacienteIdToDelete !== null) {
      deletePaciente(pacienteIdToDelete);
      deleteModal.close();
    }
  });

  // Función para mostrar el modal de confirmación
  function showDeleteModal(id) {
    pacienteIdToDelete = id;
    deleteModal.showModal();
  }

  // Función para eliminar el paciente
  function deletePaciente(id) {
    const url = `/pacientes/${id}`;
    const settings = {
      method: "DELETE",
    };

    fetch(url, settings).then((response) => {
      if (response.ok) {
        console.log(`Paciente con ID ${id} eliminado.`);
        document.getElementById(id).remove(); // Eliminar la fila de la tabla
        showAlert("Paciente eliminado exitosamente");
      } else {
        console.error(`Error al eliminar paciente con ID ${id}.`);
        showAlert("Error al eliminar el paciente", "error");
      }
    });
  }

  // Sobrescribir la función deleteBy para que muestre el modal de confirmación
  window.deleteBy = function (id) {
    showDeleteModal(id);
  };

  // Manejo del modal de confirmación
  const updateModal = document.getElementById("updateModal");
  const updateCancelButton = document.getElementById("updateCancelButton");

  // Función para mostrar el modal
  function showUpdateModal() {
    updateModal.showModal();
  }

  // Función para cerrar el modal
  function closeUpdateModal() {
    updateModal.close();
  }

  updateCancelButton.addEventListener("click", closeUpdateModal);

  // Función para obtener los datos del paciente y mostrar el formulario de actualización
  window.findBy = function (id) {
    const url = `/pacientes/${id}`;
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        // Aquí llenamos el formulario con los datos del paciente para actualizar
        document.getElementById("pacienteId").value = data.id;
        document.getElementById("pacienteNombre").value = data.nombre;
        document.getElementById("pacienteApellido").value = data.apellido;
        document.getElementById("pacienteCedula").value = data.cedula;
        document.getElementById("pacienteEmail").value = data.email;
        document.getElementById("pacienteDomicilioCalle").value =
          data.domicilio.calle;
        document.getElementById("pacienteDomicilioNumero").value =
          data.domicilio.numero;
        document.getElementById("pacienteDomicilioLocalidad").value =
          data.domicilio.localidad;
        document.getElementById("pacienteDomicilioProvincia").value =
          data.domicilio.provincia;
        showUpdateModal();
      })
      .catch((error) =>
        console.error(`Error al obtener paciente con ID ${id}:`, error)
      );
  };

  // Función para actualizar el paciente
  window.updatePaciente = function () {
    const id = document.getElementById("pacienteId").value
    const url = `/pacientes`;
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id: id , 
        nombre: document.getElementById("pacienteNombre").value,
        apellido: document.getElementById("pacienteApellido").value,
        cedula: document.getElementById("pacienteCedula").value,
        fechaIngreso: document.getElementById("pacienteFechaIngreso").value,
        domicilio: {
          id: id,
          calle: document.getElementById("pacienteDomicilioCalle").value,
          numero: document.getElementById("pacienteDomicilioNumero").value,
          localidad: document.getElementById("pacienteDomicilioLocalidad").value,
          provincia: document.getElementById("pacienteDomicilioProvincia").value,
        },
        email: document.getElementById("pacienteEmail").value,
      }),
    };

    fetch(url, settings)
      .then((response) => {
        if (response.ok) {
          console.log(`Paciente con ID ${id} actualizado.`);
          showAlert("Paciente Actualizado exitosamente");
          updateTableRow(id);
          closeUpdateModal();
        } else {
          console.error(`Error al actualizar paciente con ID ${id}.`);
          showAlert("Error al actualizar el Paciente", "error");
        }
      })
      .catch((error) => {
        console.error(`Error al actualizar paciente con ID ${id}:`, error);
        showAlert("Error al actualizar el Paciente", "error");
      });
  };

  // Función para actualizar la fila de la tabla
  function updateTableRow(id) {
    const row = document.getElementById(id);
    if (row) {
      row.querySelector(".nombre").textContent =
        document.getElementById("pacienteNombre").value;
      row.querySelector(".apellido").textContent =
        document.getElementById("pacienteApellido").value;
      row.querySelector(".cedula").textContent =
        document.getElementById("pacienteCedula").value;
      row.querySelector(".email").textContent =
        document.getElementById("pacienteEmail").value;
      row.querySelector(".domicilio").textContent = 
      `${document.getElementById("pacienteDomicilioCalle").value}, ${document.getElementById("pacienteDomicilioNumero").value}, ${document.getElementById("pacienteDomicilioLocalidad").value}, ${document.getElementById("pacienteDomicilioProvincia").value}`;
    }
  }

  // Añadir evento de submit al formulario para actualizar el paciente
  document
    .getElementById("updatePacienteForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      updatePaciente();
    });

  // Función para mostrar el alert
  function showAlert(message, type = "success") {
    const alertContainer = document.createElement("div");
    alertContainer.className = `toast toast-top toast-end`;
    alertContainer.innerHTML = `
    <div class="alert alert-${type}">
        <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current shrink-0 h-6 w-6" fill="none" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
        <span>${message}</span>
    <div/>`;

    document.body.appendChild(alertContainer);

    // Remover el alert después de 3 segundos
    setTimeout(() => {
      alertContainer.remove();
    }, 3000);
  }
});