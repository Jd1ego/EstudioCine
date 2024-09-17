import axios from "axios";

const PROYECTO_BASE = "http://localhost:8080/Proyectos";
const EQUIPO_BASE = "http://localhost:8080/Equipos/Proyectos"


class ConexionService{

    getAllProyectos(){
    return axios.get(PROYECTO_BASE);
    
    }

    getAllEquipos(){
        return axios.get(EQUIPO_BASE);
    }

    
}
export default new ConexionService;
