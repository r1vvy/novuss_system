import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import DashLayout from "./components/dash/DashLayout";
import Public from "./components/Public";
import Login from "./components/Login";

function App() {
  return (
    <Routes>
        <Route path="/*" element={<Layout />} >
            <Route index element={<Public />} />
            <Route path="login" element={<Login />} />
            <Route path="dash" element={<DashLayout />}>
            </Route>
        </Route>
    </Routes>
  );
}

export default App;
