
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListaProyecto from './ListaProyecto';
import AñadirProyecto from './AñadirProyecto';
import ListarEquipos from './ListarEquipos';
import AñadirEquipo from './AñadirEquipo';
import Inicio from './Inicio';
function App() {
  return (
   <div>
     <Router>
      <Routes>

        <Route path="/" element={<Inicio />} />
        <Route path="/Listar" element={<ListaProyecto />} />
        <Route path="/Listar-equipos" element={<ListarEquipos />} />
        <Route path="/add-proyecto" element={<AñadirProyecto />} />
        <Route path="/add-equipo" element={<AñadirEquipo />} />
        

      </Routes>
    </Router>
   </div>
  );
}

export default App;
