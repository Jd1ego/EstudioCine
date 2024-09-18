import React, { useState, useEffect } from "react";
import ConexionService from "./Servicios/ConexionService";
import { Link } from "react-router-dom";

import { FaFilm, FaPlus } from 'react-icons/fa';

export const AddProyecto = () => {
    const [titulo, setTitulo] = useState('');
    const [equipoIds, setEquipoIds] = useState([]);
    const [equiposDisponibles, setEquiposDisponibles] = useState([]);
    const [guionAutor, setGuionAutor] = useState('');
    const [guionFechaCreacion, setGuionFechaCreacion] = useState('');
    const [presupuestoCantidad, setPresupuestoCantidad] = useState('');
    const [presupuestoMoneda, setPresupuestoMoneda] = useState('Dólares'); // Valor por defecto
    const [progresoEtapa, setProgresoEtapa] = useState('');
    const [progresoPorcentaje, setProgresoPorcentaje] = useState('');
    const [progresoFechaActualizacion, setProgresoFechaActualizacion] = useState('');

    useEffect(() => {
        ConexionService.getAllEquipos()
            .then(response => setEquiposDisponibles(response.data))
            .catch(error => console.error("Error al obtener equipos:", error));
    }, []);

    const saveProyecto = (e) => {
        e.preventDefault();

        if (titulo.trim() === '' || guionAutor.trim() === '' || 
            presupuestoCantidad.trim() === '' || progresoEtapa.trim() === '' || progresoPorcentaje.trim() === '') {
            alert('Por favor complete todos los campos.');
            return;
        }

        const proyecto = {
            titulo,
            equipoIds,
            guionDTO: {
                autor: guionAutor,
                fechaCreacion: guionFechaCreacion
            },
            presupuestoDTO: {
                cantidad: parseFloat(presupuestoCantidad),
                moneda: presupuestoMoneda
            },
            progresoDTO: {
                etapa: progresoEtapa,
                porcentajeCompletado: parseFloat(progresoPorcentaje),
                fechaActualizacion: progresoFechaActualizacion
            }
        };

        ConexionService.addProyecto(proyecto)
            .then(() => {
                console.log("Proyecto guardado exitosamente");
                setTitulo('');
                setGuionAutor('');
                setGuionFechaCreacion('');
                setPresupuestoCantidad('');
                setPresupuestoMoneda('Dólares'); 
                setProgresoEtapa('');
                setProgresoPorcentaje('');
                setProgresoFechaActualizacion('');
                setEquipoIds([]);
            })
            .catch((error) => {
                console.error("Error al guardar el Proyecto:", error);
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
                <h1 className="title">Registro de nuevos proyectos</h1>
                </div>
               
                <hr />
                <form onSubmit={saveProyecto}>
                    <div className="form-group">
                        <label>Título del Proyecto</label>
                        <input
                            type="text"
                            value={titulo}
                            onChange={(e) => setTitulo(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Autor del Guion</label>
                        <input
                            type="text"
                            value={guionAutor}
                            onChange={(e) => setGuionAutor(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Fecha de Creación del Guion</label>
                        <input
                            type="date"
                            value={guionFechaCreacion}
                            onChange={(e) => setGuionFechaCreacion(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Cantidad del Presupuesto</label>
                        <input
                            type="number"
                            value={presupuestoCantidad}
                            onChange={(e) => setPresupuestoCantidad(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Moneda:</label>
                        <select
                            value={presupuestoMoneda}
                            onChange={(e) => setPresupuestoMoneda(e.target.value)}
                        >
                            <option value="Dólares">Dólares</option>
                            <option value="Euros">Euros</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Etapa del Proyecto</label>
                        <input
                            type="text"
                            value={progresoEtapa}
                            onChange={(e) => setProgresoEtapa(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                     <label>Porcentaje Completado</label>
            <div className="slider-container">
                <input
                    type="range"
                    min="0"
                    max="100"
                    step="0.1"
                    value={progresoPorcentaje}
                    onChange={(e) => setProgresoPorcentaje(e.target.value)}
                    className="slider"
                />
                <span className="slider-value">{progresoPorcentaje}%</span>
            </div>
        </div>
                    <div className="form-group">
                        <label>Fecha de Actualización del Progreso</label>
                        <input
                            type="date"
                            value={progresoFechaActualizacion}
                            onChange={(e) => setProgresoFechaActualizacion(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
    <label>Equipos disponibles</label>
    <select
        multiple
        value={equipoIds}
        onChange={(e) => {
            const selectedId = parseInt(e.target.value, 10);
            if (equipoIds.includes(selectedId)) {
                // Si el equipo ya está seleccionado, lo quitamos
                setEquipoIds(equipoIds.filter(id => id !== selectedId));
            } else {
                // Si no está seleccionado, lo agregamos
                setEquipoIds([...equipoIds, selectedId]);
            }
        }}
    >
        {equiposDisponibles.map(equipo => (
            <option key={equipo.id} value={equipo.id}>
                {equipo.nombre}
            </option>
        ))}
    </select>
</div>


                    <button type="submit">Guardar Proyecto</button>
                </form>
            </main>
        </div>
    );
};

export default AddProyecto;
