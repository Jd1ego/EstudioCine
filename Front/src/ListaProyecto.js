import React, { useState, useEffect } from 'react';
import ExampleService from './Services/ExampleService';
import { Link } from 'react-router-dom';
import './ListEstudiante.css'; 

export const ListProyecto = () => {
    const [proyectos, setProyectos] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        ExampleService.getAllClientes()
            .then(response => {
                setProyectos(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const filteredProyectos = proyectos.filter(proyecto =>
        proyecto.titulo.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="list-proyecto-container">
            <aside className="sidebar">
            <h2>Menú</h2>
                <Link to='/add-proyecto' className='btn-primary'>Agregar Proyecto</Link>

            </aside>
            <main className="content">
                <header className="list-proyecto-header">
                    <h2>Lista de Proyectos en desarrollo</h2>
                    <hr />
                    <div className="search-container">
                        <label htmlFor="search-input">
                            Buscar por título:
                            <input
                                id="search-input"
                                type="text"
                                value={searchTerm}
                                onChange={handleSearchChange}
                                placeholder="Ingrese el título del proyecto"
                            />
                        </label>
                    </div>
                </header>

                <section className="table-container">
                    <table className="styled-table">
                        <thead>
                            <tr>
                                
                                <th>Título</th>
                                <th>Guion</th>
                                <th>Progreso</th>
                                <th>Presupuesto</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            {filteredProyectos.length > 0 ? (
                                filteredProyectos.map(proyecto => (
                                    <tr key={proyecto.id}>
                                        
                                        <td>{proyecto.titulo}</td>
                                        <td>{proyecto.guion}</td>
                                        <td>{proyecto.progreso}</td>
                                        <td>{proyecto.presupuesto}</td>
                                        
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="6">No se encontraron proyectos.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </section>
            </main>
        </div>
    );
};

export default ListProyecto;
