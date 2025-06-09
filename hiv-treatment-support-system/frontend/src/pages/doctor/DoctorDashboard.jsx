import React from 'react';
import { Routes, Route } from 'react-router-dom';
import DoctorHeader from '../../components/doctor/layout/DoctorHeader';
import DoctorSidebar from '../../components/doctor/layout/DoctorSidebar';
import '../../styles/doctor/layout.css';

const DoctorDashboard = () => {
  return (
    <div className="doctor-dashboard">
      <DoctorSidebar />
      <div className="main-content">
        <DoctorHeader />
        <div className="dashboard-content">
          {/* Dashboard content will go here */}
        </div>
      </div>
    </div>
  );
};

export default DoctorDashboard;
