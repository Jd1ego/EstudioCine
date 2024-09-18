import React, { useState, useEffect } from "react";
import ConexionService from "./Servicios/ConexionService";
import './ListaProyecto.css'; 
import { Link } from "react-router-dom";

const ProyectoCard = ({ proyecto }) => {
    return (
        <div className="proyecto-card">
            <h2>{proyecto.titulo || "Título no disponible"}</h2>

            {proyecto.Autor ? (
                <p><strong>Autor:</strong> {proyecto.Autor}</p>
            ) : (
                <p><strong>Autor:</strong> No disponible</p>
            )}

            {proyecto.Presupuesto ? (
                <p><strong>Presupuesto:</strong> {proyecto.Presupuesto}</p>
            ) : (
                <p><strong>Presupuesto:</strong> No disponible</p>
            )}

            {proyecto.Progreso ? (
                <p><strong>Progreso:</strong> {proyecto.Progreso}%</p>
            ) : (
                <p><strong>Progreso:</strong> No disponible</p>
            )}
        </div>
    );
};


export const ListaProyectos = () => {
    const [proyectos, setProyectos] = useState([]);
    const [filtro, setFiltro] = useState("");

    useEffect(() => {
        ConexionService.getAllProyectos()
            .then(response => {
                console.log("Datos recibidos del backend:", response.data); 
                setProyectos(response.data);
            })
            .catch(error => console.error("Error al obtener proyectos:", error));
    }, []);

 
    const proyectosFiltrados = proyectos.filter(proyecto => 
        proyecto.titulo && proyecto.titulo.toLowerCase().includes(filtro.toLowerCase())
    );

    return (
        <div className="list-proyecto-container">
            <aside className="sidebar">
                <h2>Menú</h2>
                <Link to="/Listar" className='btn-primary'>Listar Proyectos</Link>
                <Link to='/add-proyecto' className='btn-primary'>Agregar Proyecto</Link>

            </aside>
            
            <main className="content">
                <div className="list-proyecto-header">
                    <h1>Lista de Proyectos</h1>
                </div>

                <div className="search-container">
                    <label htmlFor="buscar-proyecto">Buscar Proyecto:</label>
                    <input 
                        type="text" 
                        id="buscar-proyecto" 
                        placeholder="Ingrese el nombre del proyecto" 
                        value={filtro} // El valor del input está vinculado al estado "filtro"
                        onChange={(e) => setFiltro(e.target.value)} // Actualiza el estado cuando el usuario escribe
                    />
                </div>

                <hr />

                <div className="table-container">
                    <table className="styled-table">
                        <thead>
                            <tr>
                                <th>Título</th>
                                <th>Autor</th>
                                <th>Presupuesto</th>
                                <th>Progreso</th>
                            </tr>
                        </thead>
                        <tbody>
                            {proyectosFiltrados.length > 0 ? (
                                proyectosFiltrados.map((proyecto, index) => (
                                    <tr key={index}>
                                        <td>{proyecto.titulo || "Título no disponible"}</td>
                                        <td>{proyecto.Autor || "Autor no disponible"}</td>
                                        <td>{proyecto.Presupuesto ? `$${proyecto.Presupuesto}` : "Presupuesto no disponible"}</td>
                                        <td>{proyecto.Progreso ? `${proyecto.Progreso}%` : "Progreso no disponible"}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="4">No se encontraron proyectos.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </main>
        </div>
    );
};

export default ListaProyectos;
