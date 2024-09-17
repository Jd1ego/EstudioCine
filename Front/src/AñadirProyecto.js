import React, { useState } from "react";
import ConexionService from "./Servicios/ConexionService";
import { Link } from "react-router-dom";
import './AñadirProyecto.css';

export const AddProyecto = () => {
  const [titulo, setTitulo] = useState('');
  const [guion, setGuion] = useState('');
  const [progreso, setProgreso] = useState('');
  const [presupuesto, setPresupuesto] = useState('');
  const [error, setError] = useState('');
  const [equiposProduccion, setEquiposProduccion] = useState([]);

  const saveProyecto = (e) => {
    e.preventDefault();

    if (titulo.trim() === '' || guion.trim() === '') {
      setError('Por favor complete todos los campos.');
      return;
    }

    setError('');
    const proyecto = { titulo, guion, progreso, presupuesto, equiposProduccion };

    ConexionService.addEstudiante(proyecto)
      .then(() => {
        console.log("Proyecto guardado exitosamente");
        setTitulo('');
        setGuion('');
        setProgreso('');
        setPresupuesto('');
        setEquiposProduccion([]);
      })
      .catch((error) => {
        console.error("Error al guardar el Proyecto:", error);
      });
  };

  return (
    <div className="add-estudiante-container">
      <aside className="sidebarr">
        <h2>Menú</h2>
        <ul>
          <Link to="/Listar" className='btn'>Listar Proyectos</Link>
        </ul>
      </aside>

      <main className="content">
        <h1>Registro de nuevos proyectos</h1>
        {error && <p className="error-message">{error}</p>} {/* Muestra el mensaje de error */}

        <form onSubmit={saveProyecto}>
          <div className="form-group">
            <label>
              Titulo:
              <input
                type="text"
                placeholder="Digite el título"
                value={titulo}
                onChange={(e) => setTitulo(e.target.value)}
              />
            </label>
          </div>
          <div className="form-group">
            <label>
              Guion:
              <input
                type="text"
                placeholder="Digite el guion"
                value={guion}
                onChange={(e) => setGuion(e.target.value)}
              />
            </label>
          </div>
          <div className="form-group">
            <label>
              Progreso:
              <input
                type="text"
                placeholder="Digite el progreso"
                value={progreso}
                onChange={(e) => setProgreso(e.target.value)}
              />
            </label>
          </div>
          <div className="form-group">
            <label>
              Presupuesto:
              <input
                type="text"
                placeholder="Digite el presupuesto"
                value={presupuesto}
                onChange={(e) => setPresupuesto(e.target.value)}
              />
            </label>
          </div>
          <div className="form-group">
            <label>
              Equipos de Producción:
              <select
                multiple
                value={equiposProduccion}
                onChange={(e) => {
                  const selectedOptions = Array.from(e.target.selectedOptions, option => option.value);
                  setEquiposProduccion(selectedOptions);
                }}
              >
                <option value="camara">Cámara</option>
                <option value="sonido">Sonido</option>
                <option value="iluminacion">Iluminación</option>
                <option value="edicion">Edición</option>
              </select>
            </label>
          </div>
          <button type="submit">Guardar</button>
        </form>
      </main>
    </div>
  );
};

export default AddProyecto;
