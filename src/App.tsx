import React from 'react';

import './App.css';
import Footer from './layouts/Footer';
import Navbar from './layouts/Navbar';
import HomePage from './homepage/HomePage';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Register from './user/register';
import Login from './user/login';





function App() {
  return (
     <BrowserRouter>
      <div className='App'>
        <Navbar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
        </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;

