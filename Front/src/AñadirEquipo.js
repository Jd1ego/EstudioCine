import React, { useState, useEffect } from "react";
import ConexionService from "./Servicios/ConexionService";
import { Link } from "react-router-dom";
import './AñadirProyecto.css';

export const AddEquipo = () => {
  const [nombre, setNombre] = useState('');
  const [rol, setRol] = useState('');
  const [contacto, setContacto] = useState('');
 
  const [error, setError] = useState('');



  const saveEquipo = (e) => {
    e.preventDefault();

    if (nombre.trim() === '' || rol.trim() === '' ) {
      setError('Por favor complete todos los campos.');
      return;
    }

    setError('');
    const equipo = { nombre, rol, contacto }; 

    ConexionService.addEquipos(equipo)
      .then(() => {
        console.log("Equipo guardado exitosamente");
        setNombre('');
        setRol('');
        setContacto('');
   
      })
      .catch((error) => {
        console.error("Error al guardar el equipo:", error);
      });
  };



  return (
    <div className="add-estudiante-container">
      <aside className="sidebar">
        <h2>Menú</h2>
        <ul>
          <Link to="/Listar" className='btn-primary'>Listar Equipos</Link>
        </ul>
      </aside>

      <main className="content">
        <h1>Registro de nuevos equipos</h1>
        <hr />
        {error && <p className="error-message">{error}</p>} {/* Muestra el mensaje de error */}

        <form onSubmit={saveEquipo}>
          <div className="form-group">
            <label>
              Nombre:
              <input
                type="text"
                placeholder="Digite el nombre"
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
              />
            </label>
          </div>
          <div className="form-group">
            <label>
              Rol:
              <input
                type="text"
                placeholder="Digite el rol"
                value={rol}
                onChange={(e) => setRol(e.target.value)}
              />
            </label>
          </div>
          <div className="form-group">
            <label>
              Contacto:
              <input
                type="text"
                placeholder="Digite el contacto"
                value={contacto}
                onChange={(e) => setContacto(e.target.value)}
              />
            </label>
          </div>
          <div className="form-group">
        
          </div>
          <button type="submit">Guardar</button>
        </form>
      </main>
    </div>
  );
};

export default AddEquipo;
