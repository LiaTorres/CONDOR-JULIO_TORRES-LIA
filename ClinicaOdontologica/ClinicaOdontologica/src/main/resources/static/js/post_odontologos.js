window.addEventListener("DOMContentLoaded", function () {
  // Agregar Odontólogo
  document.getElementById('addOdontologoForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const odontologo = {
        matricula: document.getElementById('odontologoMatricula').value,
        nombre: document.getElementById('odontologoNombre').value,
        apellido: document.getElementById('odontologoApellido').value
    };

    fetch('/odontologos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontologo)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Odontólogo agregado:', data);
        alert('Odontólogo agregado exitosamente');
    })
    .catch(error => console.error('Error:', error));
});

})