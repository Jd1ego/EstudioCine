import React from 'react';
import './Inicio.css'; 

import { Link } from 'react-router-dom';

const Bienvenido = () => {
    return (
        <div className="home-page">
            <div className="image-section">
               
                
            </div>
            <div className="bienvenido">
                <h1>Bienvenido al Estudio de Cine</h1>
                <p>Explora y crea nuevos proyectos.</p>
                <Link to='/Listar' className='btn-start'>Comenzar</Link>
            </div>
        </div>
    );
};

export default Bienvenido;


