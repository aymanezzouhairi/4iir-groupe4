import React from 'react';

import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './component/Homepage';
import AboutPage from './component/Aboutpage';

function App() {
  return (
    <Router>
      <div className="App">
      
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/component" element={<AboutPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
