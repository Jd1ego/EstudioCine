import React, { useState, useEffect } from 'react';
import ConexionService from "./Servicios/ConexionService";
import { Link } from 'react-router-dom';
import './ListaProyecto.css'; 

export const ListEquipo = () => {
    const [equipos, setEquipos] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        ConexionService.getAllEquipos()
            .then(response => {
                setEquipos(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const filteredEquipos = equipos.filter(equipo =>
        equipo.rol.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="list-proyecto-container">
            <aside className="sidebar">
                <h2>Menú</h2>
                <Link to="/Listar" className='btn-primary'>Listar Proyectos</Link>
                <Link to='/add-proyecto' className='btn-primary'>Agregar Proyecto</Link>
            </aside>
            <main className="content">
                <header className="list-proyecto-header">
                    <h2>Lista de Equipos</h2>
                    <hr />
                    <div className="search-container">
                        <label htmlFor="search-input">
                            Buscar por rol:
                            <input
                                id="search-input"
                                type="text"
                                value={searchTerm}
                                onChange={handleSearchChange}
                                placeholder="Ingrese el rol del equipo"
                            />
                        </label>
                    </div>
                </header>

                <section className="table-container">
                    <table className="styled-table">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Rol</th>
                                <th>Contacto</th>
                                <th>Títulos</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredEquipos.length > 0 ? (
                                filteredEquipos.map(equipo => (
                                    <tr key={equipo.id}>
                                        <td>{equipo.nombre}</td>
                                        <td>{equipo.rol}</td>
                                        <td>{equipo.contacto}</td>
                                        <td>
                                            {equipo.EquiposTitulos && equipo.EquiposTitulos.length > 0 ? (
                                                <ul>
                                                    {equipo.EquiposTitulos.map((titulo, index) => (
                                                        <li key={index}>{titulo}</li>
                                                    ))}
                                                </ul>
                                            ) : (
                                                <span>No hay títulos</span>
                                            )}
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="4">No se encontraron equipos.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </section>
            </main>
        </div>
    );
};

export default ListEquipo;
