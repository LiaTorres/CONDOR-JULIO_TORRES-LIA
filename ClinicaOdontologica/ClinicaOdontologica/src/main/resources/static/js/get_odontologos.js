window.addEventListener("load", function () {
  (function  () {
    const url = "/odontologos";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        for (let odontologo of data) {
          var table = document.getElementById("odontologoTableBody"); // Asegúrate de que este ID exista en tu HTML
          var odontologoRow = table.insertRow();
          let tr_id = odontologo.id;
          odontologoRow.id = tr_id;

          const deleteButton = createButton(
            `btn_delete_${odontologo.id}`,
            "btn btn-circle btn-error",
            `deleteBy(${odontologo.id})`,
            `<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="4" d="M6 18L18 6M6 6l12 12"/>
            </svg>`
          );

          const updateButton = createButton(
            `btn_id_${odontologo.id}`,
            "btn btn-circle btn-success mr-4",
            `findBy(${odontologo.id})`,
            `<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
              <path d="M20.71,7.04C21.1,6.65 21.1,6 20.71,5.63L18.37,3.29C18,2.9 17.35,2.9 16.96,3.29L15.12,5.12L18.87,8.87M3,17.25V21H6.75L17.81,9.93L14.06,6.18L3,17.25Z" />
            </svg>`
          );
          odontologoRow.innerHTML =
            "<td>" +
            odontologo.id +
            "</td>" +
            '<td class="matricula">' +
            odontologo.matricula.toUpperCase() +
            "</td>" +
            '<td class="nombre">' +
            odontologo.nombre.toUpperCase() +
            "</td>" +
            '<td class="apellido">' +
            odontologo.apellido.toUpperCase() +
            "</td>" +
            "<td>" +
            updateButton +
            deleteButton +
            "</td>";
        }
      })
      .catch((error) => console.error('Error al obtener los odontólogos:', error));
  })();

  function createButton(id, className, onClick, innerHTML) {
    return `<button id="${id}" type="button" onclick="${onClick}" class="${className}">${innerHTML}</button>`;
  }

  // Manejo del modal de confirmación
  const confirmDeleteModal = document.getElementById('confirmDeleteModal');
  const confirmDeleteButton = document.getElementById('confirmDeleteButton');
  const cancelDeleteButton = document.getElementById('cancelDeleteButton');
  let odontologoIdToDelete = null; // Para almacenar el ID del odontólogo a eliminar

  cancelDeleteButton.addEventListener('click', () => {
    confirmDeleteModal.close();
  });

  confirmDeleteButton.addEventListener('click', () => {
    if (odontologoIdToDelete !== null) {
      deleteOdontologo(odontologoIdToDelete);
      confirmDeleteModal.close();
    }
  });

  // Función para mostrar el modal de confirmación
  function showDeleteModal(id) {
    odontologoIdToDelete = id;
    confirmDeleteModal.showModal();
  }

  // Función para eliminar el odontólogo
  function deleteOdontologo(id) {
    const url = `/odontologos/${id}`;
    const settings = {
      method: "DELETE",
    };

    fetch(url, settings).then((response) => {
      if (response.ok) {
        console.log(`Odontólogo con ID ${id} eliminado.`);
        document.getElementById(id).remove(); // Eliminar la fila de la tabla
        showAlert('Odontólogo eliminado exitosamente');
      } else {
        console.error(`Error al eliminar odontólogo con ID ${id}.`);
        showAlert('Error al eliminar el odontólogo', 'error');
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
    const url = `/odontologos/${id}`;
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        // Aquí llenamos el formulario con los datos del paciente para actualizar
        document.getElementById("odontologoId").value = data.id;
        document.getElementById("odontologoMatricula").value = data.matricula;
        document.getElementById("odontologoNombre").value = data.nombre;
        document.getElementById("odontologoApellido").value = data.apellido;
        showUpdateModal();
      })
      .catch((error) =>
        console.error(`Error al obtener turno con ID ${id}:`, error)
      );
  };

  // Función para actualizar el paciente
  function updateOdontologo () {
    const id = document.getElementById("odontologoId").value;
    const url = `/odontologos`;
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id: id,
        matricula: document.getElementById("odontologoMatricula").value,
        nombre: document.getElementById("odontologoNombre").value,
        apellido: document.getElementById("odontologoApellido").value,
      }),
    };

    fetch(url, settings)
      .then((response) => {
        if (response.ok) {
          console.log(`Odontologo con ID ${id} actualizado.`);
          showAlert("Odontologo Actualizado exitosamente");
          updateTableRow(id)
          closeUpdateModal();
        } else {
          console.error(`Error al actualizar Odontologo con ID ${id}.`);
          showAlert("Error al actualizar el Odontologo", "error");
        }
      })
      .catch((error) => {
        console.error(`Error al actualizar Odontologo con ID ${id}:`, error);
        showAlert("Error al actualizar el Odontologo", "error");
      });
  };

  // Función para actualizar la fila de la tabla
  function updateTableRow(id) {
    const row = document.getElementById(id);
    if (row) {
      row.querySelector(".nombre").textContent =
        document.getElementById("odontologoNombre").value;
      row.querySelector(".apellido").textContent =
        document.getElementById("odontologoApellido").value;
      row.querySelector(".matricula").textContent =
        document.getElementById("odontologoMatricula").value;
    }
  }

  // Añadir evento de submit al formulario para actualizar el turno
  document
    .getElementById("updateOdontologoForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      updateOdontologo();
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