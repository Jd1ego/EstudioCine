import React from 'react';
import { Link } from 'react-router-dom';
import { FaFilm, FaPlus, FaList, FaUsers } from 'react-icons/fa';
import './Inicio.css'; 

const Bienvenido = () => {
    return (
        <div className="home-page">
            <div className="overlay">
                <h1 className="welcome-title">Bienvenido al Estudio de Cine</h1>
                <h3 className='welcome-subtitle'>¿Qué deseas hacer?</h3>
                <div className="button-group">
                    <Link to='/Listar' className='btn-icon'><FaFilm /> Listar Proyectos</Link>
                    <Link to='/add-proyecto' className='btn-icon'><FaPlus /> Agregar Proyecto</Link>
                    <Link to='/Listar-equipos' className='btn-icon'><FaUsers /> Listar Equipos</Link>
                    <Link to='/add-equipo' className='btn-icon'><FaPlus /> Agregar Equipo</Link>
                </div>
            </div>
        </div>
    );
};

export default Bienvenido;

