document.addEventListener("DOMContentLoaded", function () {
  // Agregar Turno
  document
    .getElementById("addTurnoForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      const turno = {
        paciente: { id: document.getElementById("turnoPacienteId").value },
        odontologo: { id: document.getElementById("turnoOdontologoId").value },
        fecha: document.getElementById("turnoFecha").value,
      };

      fetch("/turnos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(turno),
      })
        .then((response) => response.json())
        .then((data) => {
          console.log("Turno agregado:", data);
          alert("Turno agregado exitosamente");
        })
        .catch((error) => console.error("Error:", error));
    });
});
