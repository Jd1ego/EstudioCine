
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListaProyecto from './ListaProyecto';
import AñadirProyecto from './AñadirProyecto';

import Inicio from './Inicio';
function App() {
  return (
   <div>
     <Router>
      <Routes>

        <Route path="/" element={<Inicio />} />
        <Route path="/Listar" element={<ListaProyecto />} />
   
        <Route path="/add-proyecto" element={<AñadirProyecto />} />
        

      </Routes>
    </Router>
   </div>
  );
}

export default App;
