import axios from "axios";

const PROYECTO_BASE = "http://localhost:8080/Proyectos";


class ConexionService{

    getAllProyectos(){
    return axios.get(PROYECTO_BASE);
    
    
    
    
    
    }

    
}
export default new ConexionService;
