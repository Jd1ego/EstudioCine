import React, { useState } from "react";
import ConexionService from "./Servicios/ConexionService";
import { Link } from "react-router-dom";
import './ListaProyecto.css'; 
import { FaFilm, FaPlus } from 'react-icons/fa';

export const AddEquipo = () => {
  const [nombre, setNombre] = useState('');
  const [rol, setRol] = useState('');
  const [contacto, setContacto] = useState('');


  const saveEquipo = (e) => {
    e.preventDefault();

    if (nombre.trim() === '' || rol.trim() === '' || contacto.trim() === '') {
      alert('Por favor complete todos los campos.');
      return;
    }

   
    const equipo = { nombre, rol, contacto };

    ConexionService.addEquipos(equipo)
      .then(() => {
        console.log("Equipo guardado exitosamente");
        setNombre('');
        setRol('');
        setContacto('');
        // Opcionalmente, puedes redirigir al usuario o mostrar un mensaje de éxito
      })
      .catch((error) => {
        console.error("Error al guardar el equipo:", error);
        
      });
  };

  return (
    <div className="list-proyecto-container">
      <aside className="sidebar">
        <h2 className="menu">Menú</h2>
        <Link to="/Listar" className='btn-primary'><FaFilm /> Listar Proyectos</Link>
        <Link to='/Listar-equipos' className='btn-primary'><FaFilm /> Listar Equipos</Link>
        <Link to='/add-proyecto' className='btn-primary'><FaPlus /> Agregar Proyecto</Link>
        <Link to='/add-equipo' className='btn-primary'><FaPlus /> Agregar Equipo</Link>
      </aside>

      <main className="form">
        <div className="title-container">
          <h1 className="title">Registro de nuevos equipos</h1>
        </div>
        <hr />
   

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
          <button type="submit">Guardar</button>
        </form>
      </main>
    </div>
  );
};

export default AddEquipo;
