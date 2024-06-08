window.addEventListener("load", function () {
  // Agregar Paciente
  document
    .getElementById("addPacienteForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();

      const paciente = {
        nombre: document.getElementById("pacienteNombre").value,
        apellido: document.getElementById("pacienteApellido").value,
        cedula: document.getElementById("pacienteCedula").value,
        fechaIngreso: document.getElementById("pacienteFechaIngreso").value,
        domicilio: parseDomicilio(document.getElementById("pacienteDomicilio").value),
        email: document.getElementById("pacienteEmail").value,
      };

      fetch("/pacientes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(paciente),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Error al agregar el Paciente");
          }
          return response.json();
        })
        .then((data) => {
          console.log("Paciente agregado:", data);
          showAlert("Paciente agregado exitosamente");
          // Aquí puedes agregar el código para actualizar la interfaz de usuario si es necesario
        })
        .catch((error) => {
          console.error("Error:", error);
          showAlert("Error al agregar el Paciente", "error");
        });
    });

  function parseDomicilio(domicilioStr) {
    const [calle, numero, localidad, provincia] = domicilioStr.split(",").map((s) => s.trim());
    return { calle, numero, localidad, provincia };
  }

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
      </div>`;

    document.body.appendChild(alertContainer);

    // Remover el alert después de 3 segundos
    setTimeout(() => {
      alertContainer.remove();
    }, 3000);
  }
});