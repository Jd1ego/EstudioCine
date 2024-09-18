import React, { useState, useEffect } from "react";
import ConexionService from "./Servicios/ConexionService";
import { Link } from "react-router-dom";

export const AddProyecto = () => {
    const [titulo, setTitulo] = useState('');
    const [equipoIds, setEquipoIds] = useState([]);
    const [equiposDisponibles, setEquiposDisponibles] = useState([]);
    const [guionAutor, setGuionAutor] = useState('');
    const [guionFechaCreacion, setGuionFechaCreacion] = useState('');
    const [presupuestoCantidad, setPresupuestoCantidad] = useState('');
    const [presupuestoMoneda, setPresupuestoMoneda] = useState('');
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

        if (titulo.trim() === '' ||  guionAutor.trim() === '' || 
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
                setPresupuestoMoneda('');
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
        <div className="add-estudiante-container">
            <aside className="sidebar">
                <h2>Menú</h2>
                <ul>
                    <Link to="/Listar-equipos" className='btn-primary'>Listar Equipos</Link>
                    <Link to="/Listar" className='btn-primary'>Listar Proyectos</Link>
                    <Link to='/add-equipo' className='btn-primary'>Agregar Equipo</Link>
                </ul>
            </aside>
            <main className="content">
                <h1>Registro de nuevos proyectos</h1>
                <hr></hr>
                <form onSubmit={saveProyecto}>
                    <div className="form-group">
                        <label>Título del Proyecto:</label>
                        <input
                            type="text"
                            value={titulo}
                            onChange={(e) => setTitulo(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Autor del Guion:</label>
                        <input
                            type="text"
                            value={guionAutor}
                            onChange={(e) => setGuionAutor(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Fecha de Creación del Guion:</label>
                        <input
                            type="date"
                            value={guionFechaCreacion}
                            onChange={(e) => setGuionFechaCreacion(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Cantidad del Presupuesto:</label>
                        <input
                            type="number"
                            value={presupuestoCantidad}
                            onChange={(e) => setPresupuestoCantidad(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Moneda:</label>
                        <input
                            type="text"
                            value={presupuestoMoneda}
                            onChange={(e) => setPresupuestoMoneda(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Etapa del Proyecto:</label>
                        <input
                            type="text"
                            value={progresoEtapa}
                            onChange={(e) => setProgresoEtapa(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Porcentaje Completado:</label>
                        <input
                            type="number"
                            step="0.1"
                            value={progresoPorcentaje}
                            onChange={(e) => setProgresoPorcentaje(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Fecha de Actualización del Progreso:</label>
                        <input
                            type="date"
                            value={progresoFechaActualizacion}
                            onChange={(e) => setProgresoFechaActualizacion(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Equipos:</label>
                        <select
                            multiple
                            value={equipoIds}
                            onChange={(e) => {
                                const selectedOptions = Array.from(e.target.selectedOptions, option => option.value);
                                setEquipoIds(selectedOptions);
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
