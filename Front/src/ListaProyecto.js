import React, { useState, useEffect } from "react";
import ConexionService from "./Servicios/ConexionService";
import './ListaProyecto.css'; 
import { Link } from "react-router-dom";
import { FaFilm, FaPlus, FaSearch } from 'react-icons/fa';

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
                <h2 className="menu">Menú</h2>
                <Link to="/Listar" className='btn-primary'><FaFilm /> Listar Proyectos</Link>
                <Link to='/Listar-equipos' className='btn-primary'><FaFilm /> Listar Equipos</Link>
                <Link to='/add-proyecto' className='btn-primary'><FaPlus /> Agregar Proyecto</Link>
                <Link to='/add-equipo' className='btn-primary'><FaPlus /> Agregar Equipo</Link>

                
            </aside>

            <main className="content">
                <div className="header">
                    <h1>Lista de Proyectos en desarrollo</h1>
                    
                </div>

                <div className="search-container">
                    <label htmlFor="buscar-proyecto"><FaSearch /> Buscar Proyecto:</label>
                    <input
                        type="text"
                        id="buscar-proyecto"
                        placeholder="Ingrese el nombre del proyecto"
                        value={filtro}
                        onChange={(e) => setFiltro(e.target.value)}
                    />
                </div>

                <hr />

                <div className="table-container">
                    <table className="styled-table">
                        <thead>
                            <tr>
                                <th>Título</th>
                                <th>Autor del guion</th>
                                <th>Presupuesto</th>
                                <th>Progreso</th>
                            </tr>
                        </thead>
                        <tbody>
                            {proyectosFiltrados.length > 0 ? (
                                proyectosFiltrados.map((proyecto, index) => (
                                    <tr key={index}>
                                        <td>{proyecto.titulo || "Título no definido"}</td>
                                        <td>{proyecto.Autor || "Autor no definido"}</td>
                                        <td>{proyecto.Presupuesto ? `$${proyecto.Presupuesto}` : "Presupuesto no definido"}</td>
                                        <td>{proyecto.Progreso ? `${proyecto.Progreso}%` : "Progreso no definido"}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="4">No se encontraron proyectos</td>
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
