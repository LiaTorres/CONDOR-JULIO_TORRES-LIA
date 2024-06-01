document.addEventListener('DOMContentLoaded', function() {
  // Agregar Paciente
  document.getElementById('addPacienteForm').addEventListener('submit', function(event) {
      event.preventDefault();
      const paciente = {
          nombre: document.getElementById('pacienteNombre').value,
          apellido: document.getElementById('pacienteApellido').value,
          cedula: document.getElementById('pacienteCedula').value,
          fechaIngreso: document.getElementById('pacienteFechaIngreso').value,
          domicilio: parseDomicilio(document.getElementById('pacienteDomicilio').value),
          email: document.getElementById('pacienteEmail').value
      };

      fetch('/pacientes', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(paciente)
      })
      .then(response => response.json())
      .then(data => {
          console.log('Paciente agregado:', data);
          alert('Paciente agregado exitosamente');
      })
      .catch(error => console.error('Error:', error));
  });

  
  function parseDomicilio(domicilioStr) {
      const [calle, numero, localidad, provincia] = domicilioStr.split(',').map(s => s.trim());
      return { calle, numero, localidad, provincia };
  }
});