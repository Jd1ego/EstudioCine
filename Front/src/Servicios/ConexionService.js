import axios from "axios";

const PROYECTO_BASE = "http://localhost:8080/Proyectos";
const EQUIPO_BASE = "http://localhost:8080/Equipos/Proyectos"
const AÑADE_EQUIPO ="http://localhost:8080/Equipo"
const AÑADE_PROYECTO ="http://localhost:8080/Proyecto"

class ConexionService{

    getAllProyectos(){
    return axios.get(PROYECTO_BASE);
    
    }


    


    getAllEquipos(){
        return axios.get(EQUIPO_BASE);
    }

    addEquipos(equipo){
        return axios.post(AÑADE_EQUIPO,equipo);
    }

    addProyecto(proyecto){
        return axios.post(AÑADE_PROYECTO,proyecto);
    }

    
}
export default new ConexionService;
